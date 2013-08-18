package com.tedit.engine.events;

public enum EventType
{
    eventUpdate(11),
    eventDraw(12),
    eventButtonPressed(13)
    ;
    private EventType(int n)
    {
        value = n;
    }
    public final int value;
}

