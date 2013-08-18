package com.tedit.engine.events.event;

import tv.ouya.console.api.OuyaController;

import com.tedit.engine.events.Event;
import com.tedit.engine.events.ButtonId;
import com.tedit.engine.events.EventIdUtil;
import com.tedit.engine.events.EventType;

public class ButtonPressed implements Event
{
    
    private int playerNum;
    private int buttonId;
    private OuyaController controller;
    
    public ButtonPressed(int playerNum, int buttonId)
    {
        this.playerNum=playerNum;
        this.buttonId = buttonId;
        this.controller=OuyaController.getControllerByPlayer(playerNum);
    }
    @Override
    public boolean Test()
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
        
        // TODO implement usage of eventIdUtilClass
        return EventIdUtil.generateId(EventType.eventButtonPressed.value, buttonId);
    }

}
