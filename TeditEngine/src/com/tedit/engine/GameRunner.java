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

import com.tedit.engine.entity.EntityManager;
import com.tedit.engine.events.EventHandler;
import com.tedit.engine.graphics.EngineRenderer;
import com.tedit.engine.graphics.Renderer;
import com.tedit.engine.parser.GameParser;

public class GameRunner extends Activity implements Game
{
    private GameView renderView;
    private Renderer graphics;
    private Screen screen;
    private EventHandler eventsHandler;
    private EntityManager entitiesManager;
    
    //TODO: encapsulate
    public String externalPath;
    
    //public ArrayList<Integer> fpsList = new ArrayList<Integer>();
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        OuyaController.init(this);
        
        int frameBufferW = 1920;
        int frameBufferH = 1080;
        GameParser gameParser = new GameParser(this.getExternalFilesDir(null).toString()+"/game.json");
        gameParser.Parse();
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferW, frameBufferH, Config.RGB_565);
        
        GameView renderView = new GameView(this, frameBuffer);
        graphics = new EngineRenderer(frameBuffer);
        externalPath = this.getExternalFilesDir(null).toString();
        eventsHandler = new EventHandler();
        entitiesManager = new EntityManager(this);
        screen = new TestScreen(this,this.getExternalFilesDir(null).toString());
        screen = new LoadingScreen(this);
        setContentView(renderView);
        
    }
    /**
     * Input Handling
     * As we want to have to available no matter the view/state of our app we forward the presses to the ouyaController Class
     */
    //Redirects the keydown event
    @Override
    public boolean onKeyDown(final int keyCode, KeyEvent event)
    {
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
    @Override
    public EventHandler getEventHandler()
    {
        return eventsHandler;
    }
    @Override
    public EntityManager getEntityManager()
    {
        return entitiesManager;
    }
}
