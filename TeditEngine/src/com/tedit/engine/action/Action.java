package com.tedit.engine.action;

public interface Action
{
    boolean isBlocking();
    boolean isExclusive();
    int getId();
    void update(float deltaTime);
    void start();
    void draw();
}
