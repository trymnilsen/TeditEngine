package com.tedit.engine.action.transform;

import com.tedit.engine.action.Action;
import com.tedit.engine.entity.Entity;

public class Displace extends Action
{
    
    public Displace(Entity parent)
    {
        super(parent);
    }

    @Override
    public boolean isSingleStep()
    {
        // TODO Auto-generated method stubl
        return true;
    }

    @Override
    public boolean isBlocking()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isExclusive()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getId()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void update(float deltaTime)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void start()
    {
        
        // TODO Auto-generated method stub
        
    }

    @Override
    public void draw()
    {
        // TODO Auto-generated method stub
        
    }

}
