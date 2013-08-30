package com.tedit.engine.graphics;

import com.tedit.engine.CustomColor;

public interface Renderer
{
    public void ClearBuffer(CustomColor color);
    public void RenderSprite(int spriteResourceId, Vector position);
    public void RenderSprite(int spriteResourceId, int x, int y);
}
