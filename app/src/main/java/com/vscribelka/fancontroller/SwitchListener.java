package com.vscribelka.fancontroller;

import android.content.Context;
import android.graphics.Color;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class SwitchListener implements CompoundButton.OnCheckedChangeListener {
    private SeekBar seek;
    private TextView txt;
    private ImageView img;
    private TextView ip;
    private String urlString;
    private Context context;
    SwitchListener(SeekBar s, TextView t, ImageView i, TextView ip, Context context) {
        this.seek = s;
        this.txt = t;
        this.img = i;
        this.ip = ip;
        this.context = context;
    }
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            seek.setEnabled(true);
            txt.setTextColor(Color.BLACK);
            urlString = String.format("http://%s?fan=%s&state=on&speed=%d", ip.getText(), img.getTag(), seek.getProgress());
        }
        else {
            seek.setEnabled(false);
            txt.setTextColor(ContextCompat.getColor(context, R.color.text_desactivat));
            urlString = String.format("http://%s?fan=%s&state=off", ip.getText(), img.getTag());
        }
        Toast.makeText(context, urlString, Toast.LENGTH_SHORT).show();
    }
}
