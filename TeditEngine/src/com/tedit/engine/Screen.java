package com.tedit.engine;

public abstract class Screen
{
    public String ScreenName;
    protected final Game gameInstance;
    
    public Screen(Game game, String name)
    {
        this.gameInstance = game;
        this.ScreenName = name;
    }
    public abstract void update(float deltaTime);
    public abstract void drawFrame(float deltaTime);
    public abstract void pause();
    public abstract void resume();
    public abstract void dispose();
    public abstract void middleButton();
}
