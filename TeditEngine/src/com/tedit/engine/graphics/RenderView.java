package com.tedit.engine.graphics;

import com.tedit.engine.GameRunner;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class RenderView extends SurfaceView implements Runnable {
    
    GameRunner game;
    Bitmap framebuffer;
    Thread renderThread = null;
    SurfaceHolder holder;
    volatile boolean running = false;
    float second = 0;
    int frames = 0;
    
    public RenderView(GameRunner game, Bitmap framebuffer) {
        super(game);
        this.game = game;
        this.framebuffer = framebuffer;
        this.holder = getHolder();
        Log.d("RenderView","Start constructor");
        running = true;
        renderThread = new Thread(this);
        renderThread.start();   

    }

    public void resume() { 

    }      
    
    public void run() {
        Rect dstRect = new Rect();
        long startTime = System.nanoTime();
        while(running) {  
            if(!holder.getSurface().isValid())
                continue;           
            

            float deltaTime = (System.nanoTime() - startTime) / 10000000.000f;
            startTime = System.nanoTime();
            //Log.d("FPS", "Deltatime: "+deltaTime);
            if (deltaTime > 3.15){
                deltaTime = (float) 3.15;
           }
           second += deltaTime;
           frames += 1;
           if(second>=100)
           {
               game.fpsList.add(frames);
               second=0;
               frames=0;
           }

            game.getScreen().update(deltaTime);
            game.getScreen().drawFrame(deltaTime);
          
            
            
            Canvas canvas = holder.lockCanvas();
            canvas.getClipBounds(dstRect);
            canvas.drawBitmap(framebuffer, null, dstRect, null);                           
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {                        
        running = false;                        
        while(true) {
            try {
                renderThread.join();
                break;
            } catch (InterruptedException e) {
                // retry
            }
            
        }
    }     
}
