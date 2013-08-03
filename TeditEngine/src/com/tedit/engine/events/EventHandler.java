package com.tedit.engine.events;

import java.util.ArrayList;
import java.util.HashMap;

import com.tedit.engine.entity.Entity;

public class EventHandler
{
    private HashMap<EventType,Event> events = new HashMap<EventType,Event>();
    private HashMap<EventType,ArrayList<Entity>> subscriptions= new HashMap<EventType,ArrayList<Entity>>();
    
    //private Dispatcher actionDispatcher;
    
    public EventHandler()
    {

    }
    public void subscribe(EventType eventId, Entity entity)
    {
        //If our subscription list already contains this event add it to
        //If not create it
        if(!subscriptions.containsKey(eventId))
        {
            subscriptions.put(eventId, new ArrayList<Entity>());
        }

        subscriptions.get(eventId).add(entity);
    }
    public void update()
    {
        for(Event e:events.values())
        {
            if(e.Test())
            {
                triggerEvent(e.getId());
            }
        }
    }
    public void draw()
    {
        triggerEvent(EventType.eventDraw);
    }
    private void triggerEvent(EventType eventId)
    {
        for(Entity ent:subscriptions.get(eventId))
        {
            ent.dispatchEvent(eventId);
        }
    }
}
