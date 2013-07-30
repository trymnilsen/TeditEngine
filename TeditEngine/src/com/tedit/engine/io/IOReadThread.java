package com.tedit.engine.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import android.os.Environment;
import android.util.Log;

public class IOReadThread extends Thread
{
    private IoLoadable callBackObject;
    private String filePath;
    public IOReadThread(String filePath, IoLoadable callBack)
    {
        callBackObject=callBack;
        this.filePath = filePath;
    }
    public void run()
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
               Log.e("IO", "Failed to load file: "+filePath+e.toString());
        }
        //Return value to our callback method
        if(bytes.length==0)
        {
            callBackObject.ioFinishedLoading(null);
        }
        else
        {
            callBackObject.ioFinishedLoading(bytes);
        }
        
    }
    
}
