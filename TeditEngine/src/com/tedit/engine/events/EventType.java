package com.tedit.engine.events;

public enum EventType
{
    eventUpdate(11),
    eventDraw(12),
    eventButtonPressed(13),
    eventButtonDown(14)
    ;
    private EventType(int n)
    {
        value = n;
    }
    public final int value;
}

