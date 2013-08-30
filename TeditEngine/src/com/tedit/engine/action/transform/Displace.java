package com.tedit.engine.action.transform;

import com.tedit.engine.action.Action;
import com.tedit.engine.entity.Entity;
import com.tedit.engine.graphics.Vector;

public class Displace extends Action
{
    private Vector displacement;
    public Displace(Entity parent, Vector displacement)
    {
        super(parent);
        this.displacement=displacement;
    }

    @Override
    public boolean isSingleStep()
    {
        //we directly displace the entity, we do not move slowly towards it
        //this is done in one step; singlestep
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
        parent.transformation.translate(displacement);
        // TODO Auto-generated method stub
        
    }

    @Override
    public void draw()
    {
        // TODO Auto-generated method stub
        
    }

	@Override
	public void applyParams(Object[] params) {
		// TODO Auto-generated method stub
		
	}

}
