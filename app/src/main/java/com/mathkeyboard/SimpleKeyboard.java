package com.mathkeyboard;

import android.graphics.Canvas;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

/**
 * Created by vyasm on 2016-12-27.
 */

public class SimpleKeyboard extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {
    private KeyboardView kv;
    private Keyboard keyboard;

    private boolean caps = false;

    @Override
    public View onCreateInputView() {
        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;
    }


    public void onDraw(Canvas canvas){

    }
    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();

        switch(primaryCode){
            case 0 :
                keyboard = new Keyboard(this, R.xml.greek);
                kv.setKeyboard(keyboard);
                kv.setOnKeyboardActionListener(this);
                break;
            case 1 :
                keyboard = new Keyboard(this, R.xml.qwerty);
                kv.setKeyboard(keyboard);
                kv.setOnKeyboardActionListener(this);
                break;
            case 2 :
                keyboard = new Keyboard(this, R.xml.calc);
                kv.setKeyboard(keyboard);
                kv.setOnKeyboardActionListener(this);
                break;
            case 3:
                keyboard = new Keyboard(this, R.xml.num1);
                kv.setKeyboard(keyboard);
                kv.setOnKeyboardActionListener(this);
                break;
            case 4:
                keyboard = new Keyboard(this, R.xml.num2);
                kv.setKeyboard(keyboard);
                kv.setOnKeyboardActionListener(this);
                break;
            case 5:
                keyboard = new Keyboard(this, R.xml.popupnew);
                kv.setKeyboard(keyboard);
                kv.setOnKeyboardActionListener(this);
                break;
            case Keyboard.KEYCODE_DELETE :
                ic.deleteSurroundingText(1, 0);
                break;
            case -1:
                caps = !caps;
                keyboard.setShifted(caps);
                kv.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            default:
                char code = (char)primaryCode;
                if(Character.isLetter(code) && caps){
                    code = Character.toUpperCase(code);
                }
                ic.commitText(String.valueOf(code),1);
        }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
