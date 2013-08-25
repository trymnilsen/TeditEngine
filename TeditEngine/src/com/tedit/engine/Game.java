package com.tedit.engine;

import com.tedit.engine.entity.EntityManager;
import com.tedit.engine.events.EventHandler;
import com.tedit.engine.graphics.*;

public interface Game
{
    //public FileIO getFileIO();
    public Renderer getRenderer();
    public void setScreen(Screen screen);
    public Screen getScreen();
    public Screen getInitScreen();
    public EventHandler getEventHandler();
    public EntityManager getEntityManager();
}
