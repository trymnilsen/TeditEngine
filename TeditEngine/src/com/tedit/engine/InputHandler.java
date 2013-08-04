package com.tedit.engine;

import tv.ouya.console.api.OuyaController;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.KeyEvent;

public class InputHandler implements KeyEvent.Callback
{

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //Get the player #
        int player = OuyaController.getPlayerNumByDeviceId(event.getDeviceId());       
        boolean handled = false;

        //Handle the input
        switch(keyCode){
            case OuyaController.BUTTON_O:
                //You now have the key pressed and the player # that pressed it
                //doSomethingWithKey();
                Log.d("Controlls", "O"+player);
                handled = true;
                break;
        }
        return handled; //|| super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int arg0, KeyEvent arg1)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onKeyMultiple(int arg0, int arg1, KeyEvent arg2)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onKeyUp(int arg0, KeyEvent arg1)
    {
        // TODO Auto-generated method stub
        return false;
    }

}
