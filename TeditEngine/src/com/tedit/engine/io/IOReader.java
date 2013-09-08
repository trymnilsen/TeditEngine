package com.tedit.engine.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import android.util.Log;

import com.tedit.engine.resource.*;

public class IOReader
{
    public enum ResourceLoadType
    {
        BINARY,TEXT
    }
    /**
     * Loads a resource from disk/"memory" using the specified loadType
     * The method does not return until loaded/synchronous loaded
     * @param path Path of the file to load from the external package path
     * @param loadType type of load mode
     * @return returns the loaded resource
     */
    public static byte[] ReadFileSync(String filePath)
    {
        byte[] bytes = new byte[0];
        try
        {
            //Instantiate the file object
            File file = new File(filePath);
            //Instantiate the input stread
            InputStream insputStream = new FileInputStream(file);
            long length = file.length();
            bytes = new byte[(int) length];
    
            insputStream.read(bytes);
            insputStream.close();

        }
        catch(Exception e)
        {
               Log.e("IO", "Failed to load file async: "+filePath+e.toString());
        }
        return bytes;
    }
    public static Resource LoadResource(String path, ResourceLoadType loadType)
    {
        //if()
        return null;
    }
    /**
     * Loads a resource from disk/"memory" using the specified loadType
     * This method returns instantly and calls the callback method when finished
     * @param path Path the resource gets loaded from
     * @param callBack the object implementing resouceloader to callback
     */
    public static void FetchResource(String path, IoLoadable callBack,int fetchId)
    {
        IOReadThread readT = new IOReadThread(path, callBack, fetchId);
        try
        {
            readT.start();
        }
        catch(IllegalStateException e)
        {
            Log.e("IOThread", "Failed to start IoReadThread");
        }
    }
}
