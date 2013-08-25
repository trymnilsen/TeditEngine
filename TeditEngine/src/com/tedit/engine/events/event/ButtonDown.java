package com.tedit.engine.events.event;

import tv.ouya.console.api.OuyaController;

import android.util.Log;

import com.tedit.engine.events.Event;
import com.tedit.engine.events.ButtonId;
import com.tedit.engine.events.EventIdUtil;
import com.tedit.engine.events.EventType;

public class ButtonDown implements Event
{
    
    private int playerNum;
    private int buttonId;
    private OuyaController controller;
    
    public ButtonDown(int playerNum, int buttonId)
    {
        this.playerNum=playerNum;
        this.buttonId = buttonId;
        this.controller=OuyaController.getControllerByPlayer(playerNum);
        Log.d("ButtonPress", "Id of buttonpress"+EventIdUtil.generateId(EventType.eventButtonDown.value, buttonId));
    }
    
    @Override
    public boolean test()
    {
        if(controller.getButton(buttonId))
        {
            return true;
        }
        return false;
    }

    @Override
    public int getId()
    {
        return EventIdUtil.generateId(EventType.eventButtonDown.value, buttonId);
    }

}
