package com.example.home;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TransactionReport extends AppCompatActivity
{

    DatabaseHelper myDb;
    ArrayList<String> listTransactions;
    ArrayAdapter adapter;


    Button btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_report);
        setTitle("Transaction Report");
        myDb = new DatabaseHelper(this);
        listTransactions=new ArrayList<>();

        viewData();

        Button backButton=(Button) findViewById(R.id. bttn_transac_back);
        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent chart1Intent=new Intent(getApplicationContext(), Mainactivity.class);
                startActivity(chart1Intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuforreport,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        ListView traList = (ListView) findViewById(R.id.list_view);
        List<String> deleteIds = new ArrayList<>();

        switch (id){
            case R.id.deleteTransaction:

                for ( int i=0; i< traList.getChildCount();i++){

                    View element = traList.getChildAt(i);
                    TextView transactionId = (TextView) element.findViewById(R.id.transactionId);
                    CheckBox box = (CheckBox)element.findViewById(R.id.rowCheckBox);

                    if(box.isChecked())
                        deleteIds.add(transactionId.getText().toString());

                }
                myDb.deleteTransactions(deleteIds);
                viewData();
            /*case R.id.filterContent:
                RelativeLayout layout = (RelativeLayout)this.findViewById(R.id.filterLayout);
                initFilterLayout(layout);*/



        }

        return true;
    }

    /*private void initFilterLayout(RelativeLayout layout){
        Spinner filterChoices = (Spinner) findViewById(R.id.spinnerFilter);
        List<String> filterChoicesData = new ArrayList<>();
        filterChoicesData.add("Category");
        filterChoicesData.add("Date");
        filterChoicesData.add("Transaction Type");
        filterChoicesData.add("Payment Type");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, filterChoicesData);

        filterChoices.setAdapter(adapter2);
//        spinner2.setOnItemSelectedListener(this);
        filterChoices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView) parent.getChildAt(0)).setTextSize(18);
                ((TextView) parent.getChildAt(0)).setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);

                String item = parent.getItemAtPosition(position).toString();
                switch (item){
                    case "Category":
                        break;
                    case "Date":
                        break;
                    case "Transaction Type":
                        break;
                    case "Payment Type":
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // show or hide filter menu when clicked on apply filters
        layout.setVisibility(layout.getVisibility()==View.VISIBLE?View.GONE:View.VISIBLE);
    }*/

    private void viewData() {

        ListView traList = (ListView) findViewById(R.id.list_view);
        traList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                CheckBox cb = (CheckBox)v.findViewById(R.id.rowCheckBox);
                cb.setChecked(!cb.isChecked());
            }
        });
        Cursor cursor = myDb.getAllData();

        if (cursor.getCount() == 0)
        {
            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        }


        Currency currency = new Currency();
        /*String valueOfcurr = currency.valueOfSlectedSpinner;

//            CurrencyConst currencyConst = new CurrencyConst();
//            String valueOfCurrency = currencyConst.getCurrencyName();*/

        String[] columns = new String[] {myDb.column_amount_E,
                myDb.column_category_E, myDb.column_transactionType,
                myDb.column_date_E,myDb.column_payment_E, myDb.column_currency_E, myDb.column_transaction_ID,myDb.column_note_E};

        int[] to = new int[] {R.id.textViewAmt, R.id.textViewCate, R.id.textViewType, R.id.textViewDte,R.id.textViewpaytype,
                R.id.textViewCurrency, R.id.transactionId,R.id.notess};
        ListAdapter ada = new SimpleCursorAdapter(this, R.layout.row, cursor, columns, to, 0){
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);
                return view;
            }
        };


        traList.setAdapter(ada);
       /* btnDel = (Button) findViewById(R.id.btn_delete);

        traList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                DatabaseHelper myDb2;
                ListActivity la=new ListActivity();
                SparseBooleanArray checkedItemPositions = la.getListView().getCheckedItemPositions();
                myDb2 =new DatabaseHelper(getApplicationContext()) ;
                boolean result = myDb2.deleteTransaction(id);
            }
        });*/
    }
}