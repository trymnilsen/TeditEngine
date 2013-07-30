package com.tedit.engine;

import com.tedit.engine.graphics.*;

public interface Game
{
    //public FileIO getFileIO();
    public Renderer getRenderer();
    public void setScreen(Screen screen);
    public Screen getScreen();
    public Screen getInitScreen();
    
}
