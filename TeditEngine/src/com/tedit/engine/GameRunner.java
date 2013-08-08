package com.tedit.engine;

import java.util.ArrayList;

import tv.ouya.console.api.OuyaController;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Log;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.tedit.engine.graphics.EngineRenderer;
import com.tedit.engine.graphics.RenderView;
import com.tedit.engine.graphics.Renderer;

public class GameRunner extends Activity implements Game
{
    private RenderView renderView;
    private Renderer graphics;
    private Screen screen;
    public ArrayList<Integer> fpsList = new ArrayList<Integer>();
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        OuyaController.init(this);
        
        int frameBufferW = 1920;
        int frameBufferH = 1080;
        
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferW, frameBufferH, Config.RGB_565);
        
        RenderView renderView = new RenderView(this, frameBuffer);
        graphics = new EngineRenderer(frameBuffer);
        screen = new TestScreen(this,this.getExternalFilesDir(null).toString());
        
        setContentView(renderView);
        
    }

    /**
     * Input Handling
     * As we want to have to available no matter the view/state of our app we forward the presses to the ouyaController Class
     */
    //Redirects the keydown event
    @Override
    public boolean onKeyDown(final int keyCode, KeyEvent event){

        boolean handled = OuyaController.onKeyDown(keyCode, event);
        return handled || super.onKeyDown(keyCode, event);
    }
    
    //redirects the key up event
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event)
    {
        boolean handled = OuyaController.onKeyUp(keyCode, event);
        return handled || super.onKeyUp(keyCode, event);
    }
    //Generic like analog or touchpad
    @Override
    public boolean onGenericMotionEvent(final MotionEvent event) {
        boolean handled = OuyaController.onGenericMotionEvent(event);
        return handled || super.onGenericMotionEvent(event);
    }
    //~input events
    @Override
    protected void onDestroy()
    {
        for(Integer f:fpsList)
        {
            Log.d("FPS","Fps:"+ f);
        }
        super.onDestroy();
    }
    @Override
    public Renderer getRenderer()
    {
        return this.graphics;
    }

    @Override
    public void setScreen(Screen screen)
    {
        if(screen == null)
        {
            throw new IllegalArgumentException("Recived Screen was null");
        }
       this.screen=screen;
    }

    @Override
    public Screen getScreen()
    {
       return screen;
    }

    @Override
    public Screen getInitScreen()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
