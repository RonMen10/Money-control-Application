package com.example.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Mainactivity extends AppCompatActivity {
    DatabaseHelper myDb;
    int backButtonCount =0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home");

        Button settings_Main=(Button) findViewById(R.id.Settings);
        settings_Main.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View W)
                {
                    Intent settingsIntent=new Intent(getApplicationContext(), Settings.class);
                    startActivity(settingsIntent);
                }
        });
        Button expense=(Button) findViewById(R.id.NewExpense);
        expense.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent expenseIntent=new Intent(getApplicationContext(), Newexpense.class);
                startActivity(expenseIntent);
            }
        });
        Button chart1=(Button) findViewById(R.id.chart);
        chart1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent chart1Intent=new Intent(getApplicationContext(), TransactionSelect.class);
                startActivity(chart1Intent);
            }
        });

        Button traRep=(Button) findViewById(R.id.transaction_report);
        traRep.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent traRepIntent=new Intent(getApplicationContext(), TransactionReport.class);
                startActivity(traRepIntent);
            }
        });

        myDb = new DatabaseHelper(this);

        List<String> defaultBudgetList = new ArrayList<>();
        defaultBudgetList = myDb.getCategoriesB();

        if (defaultBudgetList.isEmpty()){
            CategoryConst cat0 = new CategoryConst();
            cat0.setCategory_name("Choose budget to delete");
            cat0.setAmount(0);
            cat0.setDateOfBudget("undefined");
            cat0.setRecurrencyOfBudget("undefined");
            myDb.insertData_B(cat0);
        }

       List<String> defaultCurrencyList = new ArrayList<>();
        defaultCurrencyList = myDb.getNewCurrency();

        if (defaultCurrencyList.isEmpty())
        {
            CurrencyConst curr1 = new CurrencyConst();
            curr1.setCurrencyName("Euro");
            myDb.insertCurrency(curr1);

            CurrencyConst curr2 = new CurrencyConst();
            curr2.setCurrencyName("Rupee");
            myDb.insertCurrency(curr2);

            CurrencyConst curr3 = new CurrencyConst();
            curr3.setCurrencyName("Lekë");
            myDb.insertCurrency(curr3);

            CurrencyConst curr4 = new CurrencyConst();
            curr4.setCurrencyName("Dollar");
            myDb.insertCurrency(curr4);
        }

        List<String> deftaultCateList = new ArrayList<>();
        deftaultCateList = myDb.getNewCategories();

        if (deftaultCateList.isEmpty()) {

            CategoryConst cat0 = new CategoryConst();
            Icon icon0 = new Icon(R.drawable.ic_question);
            cat0.setCategory_name("Choose category");
            myDb.insertCategories(cat0, icon0);

            CategoryConst cat1 = new CategoryConst();
            Icon icon1 = new Icon(R.drawable.ic_house);
            cat1.setCategory_name("Rent");
            myDb.insertCategories(cat1, icon1);

            CategoryConst cat2 = new CategoryConst();
            Icon icon2 = new Icon(R.drawable.ic_groceries);
            cat2.setCategory_name("Food");
            myDb.insertCategories(cat2, icon2);

            CategoryConst cat3 = new CategoryConst();
            Icon icon3 = new Icon(R.drawable.ic_wifi);
            cat3.setCategory_name("Internet");
            myDb.insertCategories(cat3, icon3);

            CategoryConst cat4 = new CategoryConst();
            Icon icon4 = new Icon(R.drawable.ic_light_bulb);
            cat4.setCategory_name("Electricity Bill");
            myDb.insertCategories(cat4, icon4);

           /* CategoryConst cat5 = new CategoryConst();
            Icon icon5 = new Icon(R.drawable.ic_airplane); // change id
            cat5.setCategory_name("Salary");
            myDb.insertCategories(cat5, icon5);

            CategoryConst cat6 = new CategoryConst();
            Icon icon6 = new Icon(R.drawable.ic_books); // change id
            cat6.setCategory_name("Scholarship");
            myDb.insertCategories(cat6, icon6);

            CategoryConst cat7 = new CategoryConst();
            Icon icon7 = new Icon(R.drawable.ic_coffee); // change id
            cat7.setCategory_name("Reimbursement");
            myDb.insertCategories(cat7, icon7);*/
        }
    }
    public void onBackPressed()
    {
        int backButtonCount=0;
        if(backButtonCount>=1)
        {
            Intent intent= new Intent(Intent.ACTION_MAIN);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Please press the home button to exit the application", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }
}
