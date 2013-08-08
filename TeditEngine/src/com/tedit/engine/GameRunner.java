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

    @Override
    public boolean onKeyDown(final int keyCode, KeyEvent event){
        //Get the player #

        int player = OuyaController.getPlayerNumByDeviceId(event.getDeviceId());       
        boolean handled = false;

        //Handle the input
        switch(keyCode){
            case OuyaController.BUTTON_O:
                //You now have the key pressed and the player # that pressed it
                //doSomethingWithKey();
                handled = true;
                break;
        }
        return handled || super.onKeyDown(keyCode, event);
    }
    ///Input events
    @Override
    public boolean onGenericMotionEvent(final MotionEvent event) {
        //Get the player #
        int player = OuyaController.getPlayerNumByDeviceId(event.getDeviceId());    

        //Joystick
        if((event.getSource() & InputDevice.SOURCE_CLASS_JOYSTICK) != 0) {
            float LS_X = event.getAxisValue(OuyaController.AXIS_LS_X);            
            //do other things with joystick
            Log.i("Joystick",""+LS_X);
        }

        //Touchpad
        if((event.getSource() & InputDevice.SOURCE_CLASS_POINTER) != 0) {
            //Print the pixel coordinates of the cursor
            Log.i("Touchpad", "Cursor X: " + event.getX() + "Cursor Y: " + event.getY());
        }

        return true;
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
