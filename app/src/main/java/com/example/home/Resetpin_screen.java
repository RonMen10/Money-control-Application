package com.example.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Resetpin_screen extends AppCompatActivity {
    boolean pinStatus;
    String pin;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DatabaseHelper(this);
        setContentView(R.layout.activity_resetpin_screen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button savePin=(Button) findViewById(R.id.savePin);
        Button enablePin=(Button) findViewById(R.id.enablePin);
        Button disablePin=(Button) findViewById(R.id.disablePin);

        savePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText existingPin = (EditText) findViewById(R.id.existingPin);
                EditText newPin = (EditText) findViewById(R.id.newPin);
                EditText confirmNewPin = (EditText) findViewById(R.id.confirmNewPin);

                String existingPinText = existingPin.getText().toString();
                String newPinText = newPin.getText().toString();
                String confirmNewPinText = confirmNewPin.getText().toString();

                String currentPin = myDb.fetchCurrentPin();

                if(existingPinText.isEmpty() || newPinText.isEmpty() || confirmNewPinText.isEmpty())
                    showMessage("Error", "Please enter input in all fields");
                else if(existingPinText.length() < 4 || newPinText.length() < 4 || confirmNewPinText.length() < 4)
                    showMessage("Error", "Invalid input pin should be at least of length 4");
                else if(!currentPin.equals(existingPinText) && !currentPin.equals("???"))
                    showMessage("Error","Current pin does not match with existing pin");
                else if(!newPinText.equals(confirmNewPinText))
                    showMessage("Eror", "Pins do not match");
                else {
                    myDb.updatePin(newPinText, pinStatus);
                    myDb.close();
                    Toast.makeText(getBaseContext(), "Pin Updated", Toast.LENGTH_SHORT).show();
                    Intent save = new Intent(getApplicationContext(), Settings.class);
                    startActivity(save);
                }
            }
        });
        disablePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinStatus=false;
                String currentPin = myDb.fetchCurrentPin();
                myDb.updatePin(currentPin, pinStatus);
                Toast.makeText(getBaseContext(), "Pin disabled", Toast.LENGTH_LONG).show();
            }
        });
        enablePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinStatus = true;
                if(!myDb.isInitialPinCreated())
                    showMessage("Error", "Please create a pin before enabling this feature. Use 0000 as first existing pin");
                else {
                    String currentPin = myDb.fetchCurrentPin();
                    myDb.updatePin(currentPin, pinStatus);
                    Toast.makeText(getBaseContext(), "Pin enabled", Toast.LENGTH_LONG).show();
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
