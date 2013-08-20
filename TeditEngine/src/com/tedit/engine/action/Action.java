package com.tedit.engine.action;

import com.tedit.engine.entity.Entity;

public abstract class Action
{
    protected Entity parent;
    
    public Action(Entity parent)
    {
        this.parent=parent;
    }
    
    public abstract boolean isSingleStep();
    public abstract boolean isBlocking();
    public abstract boolean isExclusive();
    public abstract int getId();
    public abstract void update(float deltaTime);
    public abstract void start();
    public abstract void draw();
}
