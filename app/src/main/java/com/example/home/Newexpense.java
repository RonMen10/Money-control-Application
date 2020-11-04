package com.example.home;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.app.AlertDialog;
import android.database.Cursor;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Newexpense extends AppCompatActivity {
    DatabaseHelper myDb;
    Currency cur=new Currency();
    private Spinner spinner;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner transactionSpinner;

    EditText editDate, editTranscationID, editAmount, editNote;
    Spinner editTransactionType, editCategory, editRecurrecny, editPaymentType, editCurrency;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;
    Button btnviewUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);
        setTitle("Add Transaction");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<String> Transaction = new ArrayList<>();
        Transaction.add(0, "Choose Transaction");
        Transaction.add("Income");
        Transaction.add("Expense");


        transactionSpinner = findViewById(R.id.spinnerTransaction);
        ArrayAdapter<String> adapterTransaction = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Transaction);
        adapterTransaction.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        transactionSpinner.setAdapter(adapterTransaction);
        transactionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        /*
        List<String> Currency = new ArrayList<>();
        Currency.add(0, "Choose currency");
        Currency.add("Euro");
        Currency.add("Dollar");
        Currency.add("Rupee");
        Currency.add("Lekë");
        Currency.add("Dollar");


        spinner = findViewById(R.id.editCurrencyField);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Currency);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView) parent.getChildAt(0)).setTextSize(18);
                String item = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        List<String> Payment_Method = new ArrayList<>();
        Payment_Method.add(0, "Choose payment");
        Payment_Method.add("Debit Card");
        Payment_Method.add("Credit Card");
        Payment_Method.add("Cheque");
        Payment_Method.add("Payment Order");
        Payment_Method.add("Cash");

        spinner1 = findViewById(R.id.editPaymentField);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Payment_Method);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

//        List<String> Category = new ArrayList<>();
//        Category.add(0, "Choose category");
//        Category.add("Rent");
//        Category.add("Food");
//        Category.add("Internet");
//        Category.add("Electricity Bill");
//        Category.add("Movies");

        List<String> deftaultCateList = new ArrayList<>();
        myDb = new DatabaseHelper(this);

        deftaultCateList = myDb.getNewCategories();
//
//        if (deftaultCateList.isEmpty()) {
//
//            CategoryConst cat0 = new CategoryConst();
//            cat0.setCategory_name("Choose category");
//            myDb.insertCategories(cat0);
//
//            CategoryConst cat1 = new CategoryConst();
//            cat1.setCategory_name("Rent");
//            myDb.insertCategories(cat1);
//
//            CategoryConst cat2 = new CategoryConst();
//            cat2.setCategory_name("Food");
//            myDb.insertCategories(cat2);
//
//            CategoryConst cat3 = new CategoryConst();
//            cat3.setCategory_name("Internet");
//            myDb.insertCategories(cat3);
//
//            CategoryConst cat4 = new CategoryConst();
//            cat4.setCategory_name("Electricity Bill");
//            myDb.insertCategories(cat4);
//
//            deftaultCateList = myDb.getNewCategories();
//
//
//        }

        spinner2 = (Spinner) findViewById(R.id.editCategoryField);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, deftaultCateList);
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
//        spinner2.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        List<String> Recurrence = new ArrayList<>();
        Recurrence.add(0, "Choose recurrence");
        Recurrence.add("None");
        Recurrence.add("Weekly");
        Recurrence.add("Monthly");
        Recurrence.add("Yearly");
        Recurrence.add("Others");

        spinner3 = findViewById(R.id.editRecurrencyField);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Recurrence);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView) parent.getChildAt(0)).setTextSize(18);
                String item = parent.getItemAtPosition(position).toString();

                /*if (item.equals("Choose Recurrence")) {
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


        Button selectDate = findViewById(R.id.dateButton);
        final TextView date = findViewById(R.id.editDateField);
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Newexpense.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "/" + month + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        myDb = new DatabaseHelper(this);


        //editTranscationID = (EditText)findViewById(R.id.editExpenseIdField);
        editTransactionType = (Spinner) findViewById(R.id.spinnerTransaction);
        editCategory = (Spinner) findViewById(R.id.editCategoryField);
        editDate = (EditText) findViewById(R.id.editDateField);
        editRecurrecny = (Spinner) findViewById(R.id.editRecurrencyField);
        editAmount = (EditText) findViewById(R.id.editAmountField);
        editPaymentType = (Spinner) findViewById(R.id.editPaymentField);
        // editCurrency = (Spinner) findViewById(R.id.editCurrencyField);
        editNote = (EditText) findViewById(R.id.editNoteField);


        btnAddData = (Button) findViewById(R.id.button_add);
       // btnviewAll = (Button)findViewById(R.id.viewReportBtn);
        //btnviewUpdate= (Button)findViewById(R.id.button_update);
        //btnDelete= (Button)findViewById(R.id.button_delete);
        AddData();
        //viewAll();
        //UpdateData();
        // DeleteData();
    }

    /*public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editExpenseId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(Newexpense.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Newexpense.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editExpenseId.getText().toString(),
                                editCategory.getSelectedItem().toString(),
                                editDate.getText().toString(),editRecurrecny.getSelectedItem().toString(),editAmount.getText().toString(),editPaymentType.getSelectedItem().toString(),editCurrency.getSelectedItem().toString(),editNote.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(Newexpense.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Newexpense.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }*/
    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(spinner1.getSelectedItemPosition() == 0 && spinner2.getSelectedItemPosition() == 0 && spinner3.getSelectedItemPosition() == 0 && transactionSpinner.getSelectedItemPosition() == 0 && editAmount.getText().toString().isEmpty()&& editDate.getText().toString().isEmpty() && editNote.getText().toString().isEmpty())
                        {
                            Toast.makeText(Newexpense.this, "All the entries are missing please add missing values ", Toast.LENGTH_SHORT).show();
                        }
                        else if ( spinner1.getSelectedItemPosition() == 0)
                        {
                            Toast.makeText(Newexpense.this, "Please select type of payment", Toast.LENGTH_SHORT).show();
                        }
                        else if(spinner2.getSelectedItemPosition() == 0)
                        {
                            Toast.makeText(Newexpense.this, "Please select Category field ", Toast.LENGTH_SHORT).show();
                        }
                        else if(spinner3.getSelectedItemPosition() == 0)
                        {
                            Toast.makeText(Newexpense.this, "Please select recurrency type", Toast.LENGTH_SHORT).show();
                        }
                        else if(transactionSpinner.getSelectedItemPosition() == 0)
                        {
                            Toast.makeText(Newexpense.this, "Please select TYPE OF TRANSACTION", Toast.LENGTH_SHORT).show();
                        }
                        else if(editAmount.getText().toString().isEmpty())
                        {
                            Toast.makeText(Newexpense.this, "Please enter the AMOUNT", Toast.LENGTH_SHORT).show();
                        }
                        else if(editDate.getText().toString().isEmpty())
                        {
                            Toast.makeText(Newexpense.this, "Please select DATE of transaction", Toast.LENGTH_SHORT).show();
                        }
                        else if(editNote.getText().toString().isEmpty())
                        {
                            Toast.makeText(Newexpense.this, "Please add NOTE for transaction", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            boolean isInserted = myDb.insertData(editTransactionType.getSelectedItem().toString(),
                                    editCategory.getSelectedItem().toString(),
                                    editDate.getText().toString(),
                                    editRecurrecny.getSelectedItem().toString(),
                                    Integer.parseInt(editAmount.getText().toString()),
                                    editPaymentType.getSelectedItem().toString(),
                                    editNote.getText().toString());
                            if (isInserted == true) {
                                Toast.makeText(Newexpense.this, "Transaction Added", Toast.LENGTH_LONG).show();
                                myDb = new DatabaseHelper(getApplicationContext());
                                if(editTransactionType.getSelectedItem().toString()=="Expense") {
                                    double sumCategory = myDb.getCategorySum(editCategory.getSelectedItem().toString());
                                    Double budgetAmt = myDb.getBudgetAmount(editCategory.getSelectedItem().toString());
                                    if (null != budgetAmt && sumCategory >= 0.75 * budgetAmt && sumCategory <= budgetAmt) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(Newexpense.this);
                                        builder.setTitle("Warning");
                                        builder.setMessage("Careful! you have crossed 75% of the limit set on the amount of money you can spend for " + editCategory.getSelectedItem().toString());
                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }
                                    if (null != budgetAmt && sumCategory >= budgetAmt) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(Newexpense.this);
                                        builder.setTitle("Warning");
                                        builder.setMessage("Careful! you have crossed 100% of the limit set on the amount of money you can spend for " + editCategory.getSelectedItem().toString());
                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }
                                }
                            }
                            else{
                                Toast.makeText(Newexpense.this, "Transaction not added", Toast.LENGTH_LONG).show();}
                        }
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Transaction ID :" + res.getString(0) + "\n");
                            buffer.append("Transaction Type :" + res.getString(1) + "\n");
                            buffer.append("Category :" + res.getString(2) + "\n");
                            buffer.append("Date :" + res.getString(3) + "\n");
                            buffer.append("Recurrency :" + res.getString(4) + "\n");
                            buffer.append("Amount :" + res.getString(5) + "\n");
                            buffer.append("Payment Type :" + res.getString(6) + "\n");
                            buffer.append("Note :" + res.getString(8) + "\n\n\n");
                            //buffer.append("Currency :" + res.getString(8) + "\n\n\n");
                        }
                        showMessage("Transactions Report", buffer.toString());

                    }
                }
        );
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    @Override
    public void onBackPressed() {
        Intent chart1Intent=new Intent(getApplicationContext(), Mainactivity.class);
        startActivity(chart1Intent);
    }
}

