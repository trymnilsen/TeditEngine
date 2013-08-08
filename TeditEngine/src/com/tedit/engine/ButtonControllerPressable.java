package com.tedit.engine;

import android.view.KeyEvent;

public interface ButtonControllerPressable
{
     void KeyDown(int player, final int keyCode);
     void KeyUp(int player, final int keyCode);
}
