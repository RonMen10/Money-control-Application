package com.example.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TransactionSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_select);
        setTitle("Charts");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button transactionIncome=(Button) findViewById(R.id.incomeTransaction);
        transactionIncome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent trIntent=new Intent(getApplicationContext(), Chart.class);
                startActivity(trIntent);
                Intent texIntent=new Intent(TransactionSelect.this,Chart.class);
                texIntent.putExtra("type","Income");
                startActivity(texIntent);
            }
        });

        Button transactionExpese=(Button) findViewById(R.id.expenseTransaction);
        transactionExpese.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent trIntent2=new Intent(getApplicationContext(), Chart.class);
                startActivity(trIntent2);
                Intent texIntent=new Intent(TransactionSelect.this,Chart.class);
                texIntent.putExtra("type","Expense");
                startActivity(texIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent chart1Intent=new Intent(getApplicationContext(), Mainactivity.class);
        startActivity(chart1Intent);
    }
}
