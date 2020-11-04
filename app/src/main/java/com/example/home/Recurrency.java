package com.example.home;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Recurrency extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recurrency);
        setTitle("Add Recurrency");
        EditText AddNewRecurrency = (EditText) findViewById(R.id.addRecurrencyText);

        Button DoneRecurrency = (Button) findViewById(R.id.DoneRecurrency);
        DoneRecurrency.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent DoneRecurrencyIntent = new Intent(getApplicationContext(), Settings.class);
                startActivity(DoneRecurrencyIntent);
            }
        });
    }
}
