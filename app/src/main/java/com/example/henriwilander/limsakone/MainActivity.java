package com.example.henriwilander.limsakone;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    BottleDispenser dispenser = BottleDispenser.getInstance();

    SeekBar seekBar;
    TextView progressText;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBarFunction();
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Tuotevalinta, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Button button7 = (Button) findViewById(R.id.button8);
        Button button8 = (Button) findViewById(R.id.button9);
        Button button9 = (Button) findViewById(R.id.button10);

        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button9.setOnClickListener(this);
        context = MainActivity.this;
    }


    @Override
    public void onClick(View v) {
        TextView screen = (TextView) findViewById(R.id.textView9);
        switch (v.getId()) {
            case R.id.button8:
                dispenser.addMoney(screen,seekBar);
                break;
            case R.id.button9:
                dispenser.returnMoney(screen);
                break;
            case R.id.button10:
                dispenser.printBottles(screen);
                break;
        }
    }

    public void seekBarFunction( ){
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        progressText =(TextView)findViewById(R.id.textView5);
        progressText.setText(seekBar.getProgress() + " / " +seekBar.getMax());

        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressText.setText(progress + " / " +seekBar.getMax());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        TextView screen = (TextView) findViewById(R.id.textView9);
        dispenser.bottleChecker(text, screen, context);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
