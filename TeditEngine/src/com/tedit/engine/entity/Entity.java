package com.tedit.engine.entity;

import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;
import android.util.SparseArray;

import com.tedit.engine.action.Action;
import com.tedit.engine.action.transform.Displace;
import com.tedit.engine.events.EventActions;
import com.tedit.engine.events.EventType;
import com.tedit.engine.graphics.EngineRenderer;
import com.tedit.engine.graphics.Renderer;
import com.tedit.engine.graphics.Sprite;
import com.tedit.engine.graphics.Vector;

public class Entity
{
    private int id;
    private Sprite entitySprite;
    private boolean visible;
    private boolean staticEntity;
    private int depth;
    private Entity parent;
    
    private SparseArray<ArrayList<Action>> eventActions;
    private ArrayList<Action> currentActions;
    
    public EntityTransformation transformation;
    
    public Entity()
    {
        eventActions = new SparseArray<ArrayList<Action>>();
        currentActions = new ArrayList<Action>();
    }
    public Entity(int id, Sprite entitySprite)
    {
        eventActions = new SparseArray<ArrayList<Action>>();
        currentActions = new ArrayList<Action>();
        transformation = new EntityTransformation(parent, new Vector(100,100), Vector.One(), 0);
        
        this.id=1203;
        this.entitySprite = entitySprite;
        
        //FIXME: remove Testing
        //FIXME: Hardcoded event id
        //TODO: create method for adding actions
        Displace testAction = new Displace(this,new Vector(1,1));
        ArrayList<Action> temp = new ArrayList<Action>();
        temp.add(testAction);
        eventActions.put(14096, temp);
        
        
    }
    
    public void dispatchEvent(int id)
    {
        //If we dont have a blocking action, trigger and add new actions
        
        for(Action act: eventActions.get(id))
        {
            //if the action is not exclusive (meaning we only can run one at a time, 
            //for example pathfinding, move to point) we add it to the list of actions to be run
            //if it is exclusive we replace it
            
            //Lastly or most importantly if the action is a singlestep action, meaning it should only be executed once
            //We dont bother about adding it to our current running actions list
            if(!act.isSingleStep())
            {
                if(act.isExclusive() && runningAction(act.getId()))
                {
                  //remember to remove old one 
                    Action toRemove=null;
                    for(Action a: currentActions)
                    {
                        if(a.getId()==act.getId())
                        {
                            toRemove = a;
                            break;
                        }
                    }
                    //If we for some reason get null just add it, dont remove
                    if(toRemove!=null)
                    {
                        currentActions.remove(toRemove);
                    }
                    currentActions.add(act);
                }
                else
                {
                    //Action was not excluse and already running. We can add it
                    currentActions.add(act);
                }
            }
            //Instantly start the action, giving chance to set values later actions or the update method might need
            act.start();
        }
    }
    public void update(float deltaTime)
    {
        for(Action act: currentActions)
        {
            act.update(deltaTime);
        }
    }
    public void draw(Renderer renderer)
    {
        //Log.d("Drawing", "drawing sprite, at pos"+transformation.getPostion().xValue+"/"+transformation.getPostion().yValue);
        renderer.RenderSprite(entitySprite, transformation.getPostion());
        for(Action act: currentActions)
        {
            act.draw();
        }
    }
    //Gets and sets
    public Entity getParent()
    {
        return parent;
    }
    //~gets and sets
    private boolean runningAction(int ActionId)
    {
        for(Action a: currentActions)
        {
            if(a.getId()==ActionId)
            {
                return true;
            }
        }
        return false;
    }
    
}
