package com.tedit.engine.graphics;

import com.tedit.engine.CustomColor;

public interface Renderer
{
    public void ClearBuffer(CustomColor color);
    public void RenderSprite(Sprite sprite, int x, int y);
}
