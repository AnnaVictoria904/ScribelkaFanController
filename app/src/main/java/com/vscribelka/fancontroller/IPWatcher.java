package com.vscribelka.fancontroller;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class IPWatcher implements TextWatcher {
    private EditText currentEditText;
    private EditText nextEditText;
    IPWatcher(EditText one, EditText two) {
        this.currentEditText = one;
        this.nextEditText = two;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length() > 0 && charSequence.charAt(charSequence.length() - 1) == '.') {
            String newText = charSequence.subSequence(0, charSequence.length() - 1).toString();
            currentEditText.setText(newText);
            if (nextEditText != null) {
                nextEditText.requestFocus();
            }
            else {
                currentEditText.setSelection(currentEditText.length());
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        String text = editable.toString();
        try {
            int value = Integer.parseInt(text);
            if (value >= 0 && value <= 255) {
                currentEditText.setBackgroundColor(Color.WHITE);
                if (text.length() == 3 && nextEditText != null) {
                    nextEditText.requestFocus();
                }
            } else {
                currentEditText.setBackgroundColor(Color.parseColor("#FFFFCCCC"));
            }
        } catch (NumberFormatException e) {
            //currentEditText.setBackgroundColor(Color.parseColor("#FFFFCCCC"));
        }
    }
}
