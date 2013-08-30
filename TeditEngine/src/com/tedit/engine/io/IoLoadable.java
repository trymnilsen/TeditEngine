package com.tedit.engine.io;

public interface IoLoadable
{
    /**
     * Method called when file is fully async loaded 
     * by the file io manger
     * @param resourceLoaded the loaded resource
     * @param fetchId a id for the fetch request, enabling mapping of responses to fetches, knowing what the sent bytes represent
     */
    void ioFinishedLoading(byte[] dataLoaded, int fetchId);
}
