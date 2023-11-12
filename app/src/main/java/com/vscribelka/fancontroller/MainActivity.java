package com.vscribelka.fancontroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    private SeekBar sbSpeed1;
    private SeekBar sbSpeed2;
    private SeekBar sbSpeed3;
    private SeekBar sbSpeed4;
    private Switch swOnOff1;
    private Switch swOnOff2;
    private Switch swOnOff3;
    private Switch swOnOff4;
    private TextView tvSpeed1;
    private TextView tvSpeed2;
    private TextView tvSpeed3;
    private TextView tvSpeed4;
    private EditText etIPAddressA;
    private EditText etIPAddressB;
    private EditText etIPAddressC;
    private EditText etIPAddressD;
    private ImageButton ibtOk;
    private TextView tvIPAdressConfigurada;
    private ImageView fan1, fan2, fan3, fan4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fan1 = findViewById(R.id.imageView2);
        fan2 = findViewById(R.id.imageView3);
        fan3 = findViewById(R.id.imageView7);
        fan4 = findViewById(R.id.imageView8);

        sbSpeed1 = findViewById(R.id.seekBar);
        sbSpeed2 = findViewById(R.id.seekBar2);
        sbSpeed3 = findViewById(R.id.seekBar3);
        sbSpeed4 = findViewById(R.id.seekBar4);

        swOnOff1 = findViewById(R.id.switch1);
        swOnOff2 = findViewById(R.id.switch2);
        swOnOff3 = findViewById(R.id.switch3);
        swOnOff4 = findViewById(R.id.switch4);

        tvSpeed1 = findViewById(R.id.textView4);
        tvSpeed2 = findViewById(R.id.textView5);
        tvSpeed3 = findViewById(R.id.textView8);
        tvSpeed4 = findViewById(R.id.textView10);

        etIPAddressA = findViewById(R.id.editTextA);
        etIPAddressB = findViewById(R.id.editTextB);
        etIPAddressC = findViewById(R.id.editTextC);
        etIPAddressD = findViewById(R.id.editTextD);

        ibtOk = findViewById(R.id.imageButton);

        tvIPAdressConfigurada = findViewById(R.id.textView);

        // Inicialment, els ventiladors estan parats. Els Switch ja estan en
        // "off" per configuració d'interfície i aquí desactivem les SeekBar i
        // posem els seus TextView de color gris per simular la desactivació.
        sbSpeed1.setEnabled(false);
        sbSpeed2.setEnabled(false);
        sbSpeed3.setEnabled(false);
        sbSpeed4.setEnabled(false);
        tvSpeed1.setTextColor(ContextCompat.getColor(this, R.color.text_desactivat));
        tvSpeed2.setTextColor(ContextCompat.getColor(this, R.color.text_desactivat));
        tvSpeed3.setTextColor(ContextCompat.getColor(this, R.color.text_desactivat));
        tvSpeed4.setTextColor(ContextCompat.getColor(this, R.color.text_desactivat));
    }

    @Override
    protected void onStart() {
        super.onStart();

        etIPAddressA.addTextChangedListener(new IPWatcher(etIPAddressA, etIPAddressB));
        etIPAddressB.addTextChangedListener(new IPWatcher(etIPAddressB, etIPAddressC));
        etIPAddressC.addTextChangedListener(new IPWatcher(etIPAddressC, etIPAddressD));
        etIPAddressD.addTextChangedListener(new IPWatcher(etIPAddressD, null));

        etIPAddressA.setOnFocusChangeListener(new IPFocusChange());
        etIPAddressB.setOnFocusChangeListener(new IPFocusChange());
        etIPAddressC.setOnFocusChangeListener(new IPFocusChange());
        etIPAddressD.setOnFocusChangeListener(new IPFocusChange());

        swOnOff1.setOnCheckedChangeListener(new SwitchListener(sbSpeed1, tvSpeed1, fan1, tvIPAdressConfigurada, this));
        swOnOff2.setOnCheckedChangeListener(new SwitchListener(sbSpeed2, tvSpeed2, fan2, tvIPAdressConfigurada, this));
        swOnOff3.setOnCheckedChangeListener(new SwitchListener(sbSpeed3, tvSpeed3, fan3, tvIPAdressConfigurada, this));
        swOnOff4.setOnCheckedChangeListener(new SwitchListener(sbSpeed4, tvSpeed4, fan4, tvIPAdressConfigurada, this));

        sbSpeed1.setOnSeekBarChangeListener(new SeekBarListener(fan1, tvIPAdressConfigurada, this));
        sbSpeed2.setOnSeekBarChangeListener(new SeekBarListener(fan2, tvIPAdressConfigurada, this));
        sbSpeed3.setOnSeekBarChangeListener(new SeekBarListener(fan3, tvIPAdressConfigurada, this));
        sbSpeed4.setOnSeekBarChangeListener(new SeekBarListener(fan4, tvIPAdressConfigurada, this));

        ibtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etIPAddressA.length() > 0 && etIPAddressB.length() > 0 && etIPAddressC.length() > 0 &&
                        etIPAddressD.length() > 0) {

                    int valueA = Integer.parseInt(etIPAddressA.getText().toString());
                    int valueB = Integer.parseInt(etIPAddressB.getText().toString());
                    int valueC = Integer.parseInt(etIPAddressC.getText().toString());
                    int valueD = Integer.parseInt(etIPAddressD.getText().toString());

                    if (valueA <= 255 && valueB <= 255 && valueC <= 255 && valueD <= 255) {
                        tvIPAdressConfigurada.setText(valueA + "." + valueB + "." + valueC + "." + valueD);
                    } else {
                        Toast.makeText(MainActivity.this, getString(R.string.valueError), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, getString(R.string.emptyField), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}