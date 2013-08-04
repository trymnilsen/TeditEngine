package com.tedit.engine.entity;

import java.util.ArrayList;
import java.util.HashMap;

import com.tedit.engine.action.Action;
import com.tedit.engine.events.EventActions;
import com.tedit.engine.events.EventType;
import com.tedit.engine.graphics.Sprite;

public class Entity
{
    private int id;
    private Sprite entitySprite;
    private boolean visible;
    private boolean staticEntity;
    private int depth;
    private Entity parent;
    private HashMap<EventType, ArrayList<Action>> eventActions;
    
    private ArrayList<Action> currentActions;
    
    public Entity()
    {
        currentActions = new ArrayList<Action>();
    }
    
    public void dispatchEvent(EventType id)
    {
        //If we dont have a blocking action, trigger and add new actions
        
        for(Action act: eventActions.get(id))
        {
            //if the action is not exclusive (meaning we only can run one at a time, 
            //for example pathfinding, move to point) we add it to the list of actions to be run
            //if it is exclusive we replace it
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
                //If we for some reason get null just add it
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
            //Instantly start the action, giving chance to set values later actions might need
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
    public void draw()
    {
        for(Action act: currentActions)
        {
            act.draw();
        }
    }

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
