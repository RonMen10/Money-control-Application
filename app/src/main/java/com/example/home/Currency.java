package com.example.home;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Currency extends AppCompatActivity
{

    private Spinner spinnerCurrency;
    DatabaseHelper myDbCurrency;
    Button btnSaveCurrency;
    public String valueOfSlectedSpinner = "Euro";

    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        setTitle("Default Currency");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDbCurrency = new DatabaseHelper(this);
        spinnerCurrency = (Spinner) findViewById(R.id.editCurrencyFielddiff);
        //spinnerPosition();

        //addData_Currency();
        List<String> deftaultCurrencyList = new ArrayList<>();
        myDbCurrency = new DatabaseHelper(this);
        deftaultCurrencyList = myDbCurrency.getNewCurrency();


        spinnerCurrency = (Spinner) findViewById(R.id.editCurrencyFielddiff);


        ArrayAdapter<String> adapterC = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, deftaultCurrencyList);
        spinnerCurrency.setAdapter(adapterC);
//        valueOfSlectedSpinner = spinnerCurrency.getSelectedItem().toString();
//        CurrencyConst currencyConst = new CurrencyConst();
//        currencyConst.setCurrencyName(valueOfSlectedSpinner);

        spinnerCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView) parent.getChildAt(0)).setTextSize(18);
                valueOfSlectedSpinner = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnSaveCurrency=(Button)findViewById(R.id.DoneCurrency);
        btnSaveCurrency.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myDbCurrency.chooseCurrency(valueOfSlectedSpinner);
                Toast.makeText(Currency.this, "Default currency set to "+ valueOfSlectedSpinner, Toast.LENGTH_SHORT).show();
                Intent backToSettings=new Intent(getApplicationContext(), Settings.class);
                startActivity(backToSettings);
            }
        });

    }
/*    public void spinnerPosition()
    {
        btnSaveCurrency.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        boolean isInserted = myDbCurrency.chooseCurrency(spinnerCurrency.getSelectedItem().toString());
                        if (isInserted == true)
                            Toast.makeText(Currency.this, "Default currency set"+ "", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(Currency.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        }*/
}