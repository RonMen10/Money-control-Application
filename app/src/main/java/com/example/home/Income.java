package com.example.home;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog;
import android.database.Cursor;
import android.widget.EditText;
import android.widget.Toast;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Income extends AppCompatActivity {
    private Spinner spinner;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;


    DatabaseHelper myDbIncome;
    EditText editIncomeId_I, editDate_I, editExpenseId_I, editAmount_I , editNote_I;
    Spinner editCategory_I, editRecurrecny_I,editPaymentType_I,editCurrency_I;
    Button btnAddData_I;
    Button btnviewAll_I;
    Button btnDelete_I;
    Button btnviewUpdate_I;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        setTitle("Incomes");
        myDbIncome = new DatabaseHelper(this);

        List<String> Currency = new ArrayList<>();
        Currency.add(0, "Choose currency");
        Currency.add("Euro");
        Currency.add("Dollar");
        Currency.add("Lekë");
        Currency.add("Rupee");

        spinner = findViewById(R.id.editCurrency_IField);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Currency);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView) parent.getChildAt(0)).setTextSize(20);
                String item = parent.getItemAtPosition(position).toString();
                /*if (item.equals("Choose Currency")) {
                    //do nothing
                } else
                {
                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                    // here you can do anything else with the item selected
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        List<String> Payment_Method = new ArrayList<>();
        Payment_Method.add(0, "Choose payment");
        Payment_Method.add("Debit");
        Payment_Method.add("Cash");

        spinner1 = findViewById(R.id.editPayment_IField);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Payment_Method);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView) parent.getChildAt(0)).setTextSize(20);
                String item = parent.getItemAtPosition(position).toString();
                /*if (item.equals("Choose Payment")) {
                    //do nothing
                } else
                {
                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                    // here you can do anything else with the item selected
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        List<String> Category = new ArrayList<>();
        Category.add(0, "Choose category");
        Category.add("Salary");
        Category.add("Shares");
        Category.add("Lottery");
        Category.add("Miscellaneous");

        Spinner spinner2 = findViewById(R.id.editCategory_IField);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Category);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView) parent.getChildAt(0)).setTextSize(20);
                String item = parent.getItemAtPosition(position).toString();

                /*if (item.equals("Choose Category")) {
                    //do nothing
                }
                else
                {
                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                    // here you can do anything else with the item selected
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        List<String> Recurrence = new ArrayList<>();
        Recurrence.add(0, "Choose recurrence");
        Recurrence.add("Weekly");
        Recurrence.add("Monthly");
        Recurrence.add("Yearly");

        spinner3 = findViewById(R.id.editRecurrency_IField);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Recurrence);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView) parent.getChildAt(0)).setTextSize(20);
                String item = parent.getItemAtPosition(position).toString();

               /* if (item.equals("Choose Recurrence")) {
                    //do nothing
                } else {
                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                    // here you can do anything else with the item selected
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button selectDate_I = findViewById(R.id.button_date);
        final TextView date = findViewById(R.id.editDate_IField);
        selectDate_I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog= new DatePickerDialog(Income.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "/" + month + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        editIncomeId_I = (EditText) findViewById(R.id.editIncomeId_IField);
        editCategory_I = (Spinner) findViewById(R.id.editCategory_IField);
        editDate_I = (EditText) findViewById(R.id.editDate_IField);
        editRecurrecny_I = (Spinner) findViewById(R.id.editRecurrency_IField);
        editAmount_I = (EditText) findViewById(R.id.editAmount_IField);
        editPaymentType_I = (Spinner) findViewById(R.id.editPayment_IField);
        editCurrency_I = (Spinner) findViewById(R.id.editCurrency_IField);
        editNote_I = (EditText) findViewById(R.id.editNote_IField);

        btnAddData_I = (Button) findViewById(R.id.button_add_I);
        btnviewAll_I = (Button) findViewById(R.id.button_viewAll_I);
        btnDelete_I = (Button) findViewById(R.id.button_update_I);
        btnviewUpdate_I = (Button) findViewById(R.id.button_delete_I);

        AddData_I();
        viewAll_I();
        UpdateData_I();
        DeleteData_I();
    }

    public void DeleteData_I() {
        btnDelete_I.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDbIncome.deleteData_I(editIncomeId_I.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(Income.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Income.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData_I() {
        btnviewUpdate_I.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDbIncome.updateData_I(editIncomeId_I.getText().toString(),
                                editCategory_I.getSelectedItem().toString(),
                                editDate_I.getText().toString(), editRecurrecny_I.getSelectedItem().toString() , editAmount_I.getText().toString(), editPaymentType_I.getSelectedItem().toString(), editCurrency_I.getSelectedItem().toString(), editNote_I.getText().toString());
                        if (isUpdate == true)
                            Toast.makeText(Income.this, "Data Update", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Income.this, "Data not Updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void AddData_I() {
        btnAddData_I.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDbIncome.insertDataIncome( editCategory_I.getSelectedItem().toString(),
                                editDate_I.getText().toString(), editRecurrecny_I.getSelectedItem().toString(), editAmount_I.getText().toString(), editPaymentType_I.getSelectedItem().toString(), editCurrency_I.getSelectedItem().toString(), editNote_I.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(Income.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Income.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll_I() {
        btnviewAll_I.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDbIncome.getAllData_I();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage_I("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Income ID :" + res.getString(0) + "\n");
                            buffer.append("Category :" + res.getString(1) + "\n");
                            buffer.append("Date :" + res.getString(2) + "\n");
                            buffer.append("Reccurrency :" + res.getString(3) + "\n");
                            buffer.append("Amount :" + res.getString(4) + "\n");
                            buffer.append("Payment :" + res.getString(5) + "\n");
                            buffer.append("Note :" + res.getString(7) + "\n");
                            buffer.append("Currency :" + res.getString(6) + "\n\n\n");
                        }
                        // Show all data
                        showMessage_I("Income Transactions", buffer.toString());
                    }
                }
        );
    }

    public void showMessage_I(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/