package com.tedit.engine;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class LoadingScreen extends Screen
{
    Bitmap logoBitmap;

    public LoadingScreen(GameRunner game)
    {
        super(game,"LoadingScreen");
        logoBitmap = BitmapFactory.decodeResource(((Activity)game).getResources(), R.drawable.tedit_icon);
        if(logoBitmap==null)
        {
            Log.e("Graphics", "AAAOOOOOOOOGGGGGGGGGGGGGGGGGAAAAAAAAAAAAAAAH");
        }
    }

    @Override
    public void update(float deltaTime)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void drawFrame(float deltaTime)
    {
        // TODO Auto-generated method stub
        gameInstance.getRenderer().RenderBitmap(logoBitmap, 720, 300);
    }

    @Override
    public void pause()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispose()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void middleButton()
    {
        // TODO Auto-generated method stub
        
    }
}
