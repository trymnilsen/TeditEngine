package com.tedit.engine.graphics;

import com.tedit.engine.resource.Resource;

import android.graphics.Bitmap;

public class Sprite implements Resource
{
    private Bitmap spriteBitmap;
    
    //public int glTextureId;
    public int resourceId;
    public boolean dirty;
    
    
    public Sprite(Bitmap bitmap, int resourceId)
    {
        spriteBitmap = bitmap;
        this.resourceId = resourceId;
    }
    public void drawSprite()
    {
        
    }
    public Bitmap getBitmap()
    {
        return spriteBitmap;
    }
    
}
