package com.tedit.engine.graphics;

import android.graphics.Bitmap;

import com.tedit.engine.CustomColor;

public interface Renderer
{
    public void ClearBuffer(CustomColor color);
    public void RenderSprite(int spriteResourceId, Vector position);
    public void RenderSprite(int spriteResourceId, int x, int y);
    public void RenderBitmap(Bitmap bitmap, int x, int y);
}
