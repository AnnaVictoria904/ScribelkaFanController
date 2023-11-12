package com.vscribelka.fancontroller;

import android.content.Context;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SeekBarListener implements SeekBar.OnSeekBarChangeListener {
    private ImageView img;
    private TextView ip;
    private String urlString;
    private Context context;
    SeekBarListener(ImageView i, TextView ip, Context context) {
        this.img = i;
        this.ip = ip;
        this.context = context;
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        urlString = String.format("http://%s?fan=%s&state=on&speed=%d", ip.getText(), img.getTag(), seekBar.getProgress());
        Toast.makeText(context, urlString, Toast.LENGTH_SHORT).show();
    }
}
