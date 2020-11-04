package com.example.home;

        import android.app.DatePickerDialog;
        import android.content.Intent;
        import android.database.Cursor;
        import android.graphics.Color;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.List;

public class Budget extends AppCompatActivity {
    private Spinner spinnerR;
    private Spinner spinnerC;
    private Spinner spinnerDelete;

    EditText editBudget_ID;
    EditText editAmount_B;
    Spinner editCategory_B;
    EditText editDate_B;
    Spinner editRecurrencyB;
    Spinner deleteBudget;
    Button btnSave_B,btnDelete_B,btnView_B;
    DatabaseHelper myDb_B;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        setTitle("Budget");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myDb_B = new DatabaseHelper(this);
        editAmount_B = (EditText)findViewById(R.id.amount_B);
        editCategory_B = (Spinner)findViewById(R.id.category_B);
        editDate_B = (EditText)findViewById(R.id.enterDateB);
        editRecurrencyB = (Spinner) findViewById(R.id.spinnerRecurrencyB);
        deleteBudget = (Spinner) findViewById(R.id.spinnerDeleteB);
        //Buttons
        btnSave_B = (Button)findViewById(R.id.button_saveB2);
        btnDelete_B = (Button)findViewById(R.id.button_deleteB2);
        btnView_B= (Button)findViewById(R.id.button_viewB2);
        Save_B();
        Delete_B();
        View_B();

        List<String> RecurrencyB = new ArrayList<>();
        RecurrencyB.add(0, "Choose Recurrency");
        RecurrencyB.add("None");
        RecurrencyB.add("Weekly");
        RecurrencyB.add("Monthly");
        RecurrencyB.add("Yearly");
        RecurrencyB.add("Others");

        spinnerR = findViewById(R.id.spinnerRecurrencyB);
        ArrayAdapter<String> adapter_R = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, RecurrencyB);
        adapter_R.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerR.setAdapter(adapter_R);
        spinnerR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView) parent.getChildAt(0)).setTextSize(18);
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button selectDate = findViewById(R.id.DateB);
        final TextView date = findViewById(R.id.enterDateB);
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Budget.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "/" + month + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        List<String> deftaultCateList = new ArrayList<>();
        myDb_B = new DatabaseHelper(this);

        deftaultCateList = myDb_B.getNewCategories();


        spinnerC = (Spinner) findViewById(R.id.category_B);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, deftaultCateList);
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerC.setAdapter(adapter2);
//        spinner2.setOnItemSelectedListener(this);
        spinnerC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView) parent.getChildAt(0)).setTextSize(18);
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        List<String> defaultBudgetList = new ArrayList<>();
        myDb_B = new DatabaseHelper(this);

        defaultBudgetList = myDb_B.getCategoriesB();

        spinnerDelete = (Spinner) findViewById(R.id.spinnerDeleteB);
        ArrayAdapter<String> adapterB = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, defaultBudgetList);
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDelete.setAdapter(adapterB);
//        spinner2.setOnItemSelectedListener(this);
        spinnerDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView) parent.getChildAt(0)).setTextSize(18);
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    //Button methods

    public  void Save_B() { btnSave_B.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editCategory_B.getSelectedItemPosition() == 0 && editRecurrencyB.getSelectedItemPosition() == 0 && editAmount_B.getText().toString().isEmpty() && editDate_B.getText().toString().isEmpty()) {
                        Toast.makeText(Budget.this, "Please insert data about the budget type", Toast.LENGTH_SHORT).show();
                    }
                    else if (editCategory_B.getSelectedItemPosition() == 0)
                        {
                            Toast.makeText(Budget.this, "Please select the category type before adding the budget", Toast.LENGTH_SHORT).show();
                        }
                    else if(editRecurrencyB.getSelectedItemPosition() == 0)
                    {
                        Toast.makeText(Budget.this, "Please select the recurrency type before adding the budget", Toast.LENGTH_SHORT).show();
                    }
                    else if (editAmount_B.getText().toString().isEmpty())
                    {
                        Toast.makeText(Budget.this, "Please set the amount for budget", Toast.LENGTH_SHORT).show();
                    }

                    else if (  editDate_B.getText().toString().isEmpty())
                    {
                        Toast.makeText(Budget.this, "Please set the date value", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //boolean isInserted = myDb_B.insertData_B(editAmount_B.getText().toString(), editCategory_B.getSelectedItem().toString(), editDate_B.getText().toString(), editRecurrencyB.getSelectedItem().toString());
                        CategoryConst cat = new CategoryConst();
                        cat.setAmount(Integer.parseInt(editAmount_B.getText().toString()));
                        cat.setCategory_name(editCategory_B.getSelectedItem().toString());
                        cat.setRecurrencyOfBudget(editRecurrencyB.getSelectedItem().toString());
                        cat.setDateOfBudget(editDate_B.getText().toString());


                        boolean isInserted = myDb_B.insertData_B(cat);
                        if (isInserted == true) {
                            Intent intent = new Intent(getApplicationContext(),Budget.class);
                            startActivity(intent);
                            Toast.makeText(Budget.this, "Budget Inserted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Budget.this, "Budget not Inserted", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
    );
    }
    public void Delete_B() {
        btnDelete_B.setOnClickListener(
                new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               if(deleteBudget.getSelectedItemPosition()>0)
                                               {
                                                   Integer deletedRows = myDb_B.deleteData_B(deleteBudget.getSelectedItem().toString());
                                                   if (deletedRows > 0) {
                                                       Intent intent = new Intent(getApplicationContext(), Budget.class);
                                                       startActivity(intent);
                                                       Toast.makeText(Budget.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                                                   }
                                                   else
                                                       Toast.makeText(Budget.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
                                               }
                                               else
                                                   Toast.makeText(Budget.this, "Choose a budget from dropdown delete", Toast.LENGTH_SHORT).show();
                                           }
                                       }
        );
    }

    public void View_B() {
        btnView_B.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Cursor bdg = myDb_B.getAllData_B();
                                             if (bdg.getCount() == 1) {
                                                 showMessage("Error", "Nothing found");
                                                 return;
                                             }
                                             StringBuffer buffer = new StringBuffer();
                                                 while (bdg.moveToNext()) {
                                                     if(bdg.getString(2).equals("Choose budget to delete")){
                                                         continue;
                                                     }
                                                     //buffer.append("Budget ID :"+ bdg.getString(0)+"\n");
                                                     buffer.append("Amount :" + bdg.getString(1) + "\n");
                                                     buffer.append("Category :" + bdg.getString(2) + "\n");
                                                     buffer.append("Recurrency :" + bdg.getString(3) + "\n");
                                                     buffer.append("Date :" + bdg.getString(4) + "\n\n\n");
                                                 }
                                                 showMessage("Budget", buffer.toString());
                                             }
                                     }
        );
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        TextView AmountRent = (TextView) findViewById(R.id.AmountRent);
        TextView AmountMovies = (TextView) findViewById(R.id.AmountMovies);
        TextView AmountElectricity = (TextView) findViewById(R.id.AmountElectricity);
        TextView AmountInternet = (TextView) findViewById(R.id.AmountInternet);
        TextView AmountFood = (TextView) findViewById(R.id.AmountFood);

        final EditText ThresholdRent = (EditText) findViewById(R.id.ThresholdRent);
        final EditText ThresholdMovies = (EditText) findViewById(R.id.ThresholdMovies);
        final EditText ThresholdElectricity = (EditText) findViewById(R.id.ThresholdElectricity);
        final EditText ThresholdInternet = (EditText) findViewById(R.id.ThresholdInternet);
        final EditText ThresholdFood = (EditText) findViewById(R.id.ThresholdFood);


        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        final Cursor s1 = db.getAllAmount1();
        AmountRent.setText(s1.toString());

        final Cursor s2 = db.getAllAmount2();
        AmountMovies.setText(s2.toString());

        final Cursor s3 = db.getAllAmount3();
        AmountElectricity.setText(s3.toString());

        final Cursor s4 = db.getAllAmount4();
        AmountInternet.setText(s4.toString());

        final Cursor s5 = db.getAllAmount5();
        AmountFood.setText(s5.toString());

        Button Save = (Button) findViewById(R.id.Save);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a1 = s1.toString();
                String a2 = s2.toString();
                String a3 = s3.toString();
                String a4 = s4.toString();
                String a5 = s5.toString();

                if (a1 == ThresholdRent.toString() || a2 == ThresholdMovies.toString() || a3 == ThresholdElectricity.toString() || a4 == ThresholdInternet.toString() || a5 == ThresholdFood.toString())
                {
                    Toast.makeText(Budget.this,"You have reached the limit for rent",Toast.LENGTH_LONG).show();

                }
            }
        });*/
    public void onBackPressed() {
        Intent budgetIntent=new Intent(getApplicationContext(), Mainactivity.class);
        startActivity(budgetIntent);
    }
}
