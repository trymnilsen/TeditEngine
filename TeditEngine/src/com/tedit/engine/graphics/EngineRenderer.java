package com.tedit.engine.graphics;

import com.tedit.engine.CustomColor;
import com.tedit.engine.resource.ResourceManager;

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
    public void RenderSprite(int spriteResourceId, Vector position)
    {
        RenderSprite(spriteResourceId, (int)position.xValue, (int)position.yValue);
    }
    @Override
    public void RenderSprite(int spriteResourceId, int x, int y)
    {
    	//retrive the bitmap associated with this id
    	Sprite drawSprite = ResourceManager.getInstance().<Sprite>getAsset(spriteResourceId);
    	Bitmap drawMap = drawSprite.getBitmap();
        srcRect.left = x;
        srcRect.top = y;
        srcRect.right = x+drawMap.getWidth();
        srcRect.bottom = y+drawMap.getHeight();
        
        dstRect = srcRect;

        canvas.drawBitmap(drawMap, null, dstRect, null);
        
    }
    
}
