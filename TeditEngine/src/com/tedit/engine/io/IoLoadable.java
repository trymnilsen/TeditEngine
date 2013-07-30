package com.tedit.engine.io;

public interface IoLoadable
{
    /**
     * Method called when file is fully async loaded 
     * by the file io manger
     * @param resourceLoaded the loaded resource
     */
    void ioFinishedLoading(byte[] dataLoaded);
}
