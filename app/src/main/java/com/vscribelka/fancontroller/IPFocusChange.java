package com.vscribelka.fancontroller;

import android.view.View;
import android.widget.EditText;

public class IPFocusChange implements View.OnFocusChangeListener {
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus && v instanceof EditText) {
            EditText editText = (EditText) v;
            editText.setSelectAllOnFocus(true);
        }
    }
}
