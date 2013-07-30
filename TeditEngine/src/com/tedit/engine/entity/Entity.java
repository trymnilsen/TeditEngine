package com.tedit.engine.entity;

import com.tedit.engine.events.EventActions;
import com.tedit.engine.graphics.Sprite;

public class Entity
{
    private int id;
    private Sprite entitySprite;
    private boolean visible;
    private boolean staticEntity;
    private int depth;
    private Entity parent;
    private EventActions eventActions;
    
}
