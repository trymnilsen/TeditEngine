package com.tedit.engine.graphics;

import com.tedit.engine.CustomColor;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class EngineRenderer implements Renderer
{
    private Bitmap frameBuffer;
    private Canvas canvas;
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();
    
    public EngineRenderer(Bitmap frameBuffer)
    {
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(this.frameBuffer);
    }

    @Override
    public void ClearBuffer(CustomColor color)
    {
        // TODO Auto-generated method stub
        canvas.drawRGB(color.r, color.g, color.b);
    }

    @Override
    public void RenderSprite(Sprite sprite, int x, int y)
    {
        srcRect.left = x;
        srcRect.top = y;
        srcRect.right = x+sprite.getBitmap().getWidth();
        srcRect.bottom = y+sprite.getBitmap().getHeight();
        
        dstRect = srcRect;

        canvas.drawBitmap(sprite.getBitmap(), null, dstRect, null);
        
    }
    
}
