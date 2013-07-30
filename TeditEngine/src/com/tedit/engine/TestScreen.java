package com.tedit.engine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.util.Log;

import com.tedit.engine.graphics.Sprite;

public class TestScreen extends Screen
{
    CustomColor clearColor;
    Sprite testSprite;
    float x = 0;
    public TestScreen(Game game, String path)
    {
        super(game);
        clearColor = new CustomColor(72, 109, 159, 1);
        Log.d("IO",path);
        Bitmap bm = BitmapFactory.decodeFile(path+"/skate1.png");
        testSprite = new Sprite(bm,1);
    }

    @Override
    public void update(float deltaTime)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void drawFrame(float deltaTime)
    {
        x+=1*deltaTime;
               //Log.d("Graphics","Drawing"+testSprite.resourceId);
            gameInstance.getRenderer().ClearBuffer(clearColor);
            gameInstance.getRenderer().RenderSprite(testSprite, (int)x, 500);
//        // TODO Auto-generated method stub
        
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
