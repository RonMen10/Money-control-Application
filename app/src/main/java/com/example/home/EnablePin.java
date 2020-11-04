package com.example.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class EnablePin extends AppCompatActivity {

    Switch enablePinSwitch;
    EditText newPin;
    EditText confirmNewPin;
    Button savePin;
    DatabaseHelper myDb;
    boolean pinStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable_pin);
        setTitle("Set PIN");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myDb = new DatabaseHelper(this);
        enablePinSwitch = (Switch) findViewById(R.id.enablePinSwitch);
        if( myDb.isPinEnabled()){
            enablePinSwitch.setChecked(true);
        }
        newPin = (EditText) findViewById(R.id.newPin);
        confirmNewPin = (EditText) findViewById(R.id.confirmNewPin);
        savePin = (Button) findViewById(R.id.savePin);
        enablePinSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    newPin.setVisibility(View.VISIBLE);
                    confirmNewPin.setVisibility(View.VISIBLE);
                    savePin.setVisibility(View.VISIBLE);
                    enablePinSwitch.setChecked(true);
                }
                else {
                    newPin.setVisibility(View.INVISIBLE);
                    confirmNewPin.setVisibility(View.INVISIBLE);
                    savePin.setVisibility(View.INVISIBLE);
                    pinStatus=false;
                    String currentPin = myDb.fetchCurrentPin();
                    myDb.updatePin(currentPin, pinStatus);
                    Toast.makeText(getBaseContext(), "Pin disabled", Toast.LENGTH_LONG).show();
                }
            }
        });

        savePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPinText = newPin.getText().toString();
                String confirmNewPinText = confirmNewPin.getText().toString();

                if(!newPinText.equals(confirmNewPinText))
                    showMessage("Error", "Pins do not match");
                else if(newPinText.equals("") | confirmNewPinText.equals(""))
                    showMessage("Error", "Please enter valid PIN");
                else {
                    pinStatus = true;
                    myDb.updatePin(newPinText, pinStatus);
                    myDb.close();
                    Toast.makeText(getBaseContext(), "Pin Updated", Toast.LENGTH_SHORT).show();
                    Intent save = new Intent(getApplicationContext(), Mainactivity.class);
                    startActivity(save);
                }
            }
        });


    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
