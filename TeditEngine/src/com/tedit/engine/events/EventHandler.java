package com.tedit.engine.events;

import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;
import android.util.SparseArray;

import com.tedit.engine.entity.Entity;
import com.tedit.engine.events.event.ButtonPressed;

public class EventHandler
{
    private SparseArray<Event> events = new SparseArray<Event>();
    private SparseArray<ArrayList<Entity>> subscriptions= new SparseArray<ArrayList<Entity>>();
    
    //private Dispatcher actionDispatcher;
    
    public EventHandler()
    {
        events.put(Integer.valueOf(2),new ButtonPressed(2, 4));
    }
    public void subscribe(int eventId, Entity entity)
    {
        //If our subscription list already contains this event add it to
        //If not create it
        if(subscriptions.indexOfKey(eventId)>=0)
        {
            subscriptions.put(eventId, new ArrayList<Entity>());
        }

        subscriptions.get(eventId).add(entity);
    }
    public void update()
    {
        //have to iterate the old way with sparsearray
        int key = 0;
        for(int i = 0; i < events.size(); i++) {
            key = events.keyAt(i);
            // get the object by the key.
            Event e = events.get(key);
            if(e.Test())
            {
                triggerEvent(e.getId());
            }
        }
    }
    public void draw()
    {
        triggerEvent(EventType.eventDraw.ordinal());
    }
    private void triggerEvent(int eventId)
    {
        if(subscriptions.get(eventId)!=null)
        {
            for(Entity ent:subscriptions.get(eventId))
            {
                ent.dispatchEvent(eventId);
            }
        }
        else
        {
            Log.e("EventHandler", "tried to trigger event not existing"+eventId);
        }
    }
}
