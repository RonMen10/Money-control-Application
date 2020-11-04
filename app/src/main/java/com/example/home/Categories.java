package com.example.home;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Categories extends AppCompatActivity
{
    private Spinner spinner, spinnerIcon;

    DatabaseHelper myDbCategory;
    EditText categoryID,categoryName;

    Spinner deleteCategory, addIcon;
    Button btnAddData_C;
    Button btnViewAll_C;
    Button btnDelete_C;

    IconAdapter iconAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        setTitle("Add/Delete Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myDbCategory = new DatabaseHelper(this);


        deleteCategory = (Spinner) findViewById(R.id.spinnerDeleteC);

        //categoryID = (EditText) findViewById(R.id.addCatID);
        categoryName = (EditText) findViewById(R.id.addCategoryName);
        btnAddData_C=(Button) findViewById(R.id.DoneCategory);
//        btnViewAll_C=(Button) findViewById(R.id.viewCategory);
        btnDelete_C=(Button) findViewById(R.id.deleteCategory);

        addData_C();
        deleteData_C();
        viewData_C();

        List<String> deftaultCateList = new ArrayList<>();
        myDbCategory = new DatabaseHelper(this);
        deftaultCateList = myDbCategory.getNewCategories();

        spinner = (Spinner) findViewById(R.id.spinnerDeleteC);
        ArrayAdapter<String> adapterC = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, deftaultCateList);
        spinner.setAdapter(adapterC);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView) parent.getChildAt(0)).setTextSize(20);
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<Icon> defaultIconList = new ArrayList<>();

        defaultIconList.add(new Icon(R.drawable.ic_question));
        defaultIconList.add(new Icon(R.drawable.ic_towel));
        defaultIconList.add(new Icon(R.drawable.ic_airplane));
        defaultIconList.add(new Icon(R.drawable.ic_automobile));
        defaultIconList.add(new Icon(R.drawable.ic_books));
        defaultIconList.add(new Icon(R.drawable.ic_coffee));
        defaultIconList.add(new Icon(R.drawable.ic_document));
        defaultIconList.add(new Icon(R.drawable.ic_gas));
        defaultIconList.add(new Icon(R.drawable.ic_gift));
        defaultIconList.add(new Icon(R.drawable.ic_hospital));
        defaultIconList.add(new Icon(R.drawable.ic_hotel));
        defaultIconList.add(new Icon(R.drawable.ic_makeover));
        defaultIconList.add(new Icon(R.drawable.ic_medicine));
        defaultIconList.add(new Icon(R.drawable.ic_netflix));
        defaultIconList.add(new Icon(R.drawable.ic_popcorn));
        defaultIconList.add(new Icon(R.drawable.ic_restaurant));
        defaultIconList.add(new Icon(R.drawable.ic_shoe));
        defaultIconList.add(new Icon(R.drawable.ic_student));
        defaultIconList.add(new Icon(R.drawable.ic_sun_umbrella));
        defaultIconList.add(new Icon(R.drawable.ic_weightlifter));



        spinnerIcon = (Spinner) findViewById(R.id.spinnerIcon);
        iconAdapter = new IconAdapter(this, defaultIconList);
        spinnerIcon.setAdapter(iconAdapter);
        spinnerIcon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Icon item = (Icon) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void deleteData_C()
    {
        btnDelete_C.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(deleteCategory.getSelectedItemPosition()>4)
                        {
                            Integer deletedRows = myDbCategory.deleteCategory(deleteCategory.getSelectedItem().toString());
                            if (deletedRows > 0) {
                                Intent intent = new Intent(getApplicationContext(), Categories.class);//to refresh the activity as soon as you delet
                                startActivity(intent);
                                Toast.makeText(Categories.this, "Category Deleted", Toast.LENGTH_LONG).show();
                            }
                            else
                                Toast.makeText(Categories.this, "Category not Deleted", Toast.LENGTH_LONG).show();}
                        else
                            Toast.makeText(Categories.this, "Cannot delete default categories", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void addData_C() {
        btnAddData_C.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CategoryConst newCate = new CategoryConst();
                        int newIcon =spinnerIcon.getSelectedItemPosition();
                        int newIconImage = iconAdapter.getItem(newIcon).getIconItem();
                        Icon newI = new Icon(newIconImage);
                        String nonNull =categoryName.getText().toString();
                        if(nonNull.isEmpty() || newIcon==0)
                        {
                            Toast.makeText(Categories.this, "Please select all the values", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            newCate.setCategory_name(categoryName.getText().toString());
                            boolean isAdded= myDbCategory.insertCategories(newCate, newI);
                            if (isAdded == true)
                            {
                                Intent intent = new Intent(getApplicationContext(), Categories.class);
                                startActivity(intent);
                                Toast.makeText(Categories.this, "Category Added", Toast.LENGTH_LONG).show();}
                        }
                    }
                }
        );
    }
    /*public void viewData_C() {
        btnViewAll_C.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDbCategory.getAllCategories();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage_C("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Category Id :" + res.getString(0) + "\n");
                            buffer.append("Category Name :" + res.getString(1) + "\n");
                            buffer.append("Category ICon :" + res.getString(2) + "\n\n\n");

                        }
                        // Show all data
                        showMessage_C("", buffer.toString());
                    }
                }
        );
    }*/

    public void viewData_C() {
        ListView catList = (ListView) findViewById(R.id.listCategories);
        Cursor cursor = myDbCategory.getAllCategories();

        if (cursor.getCount() == 0)
        {
            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        }
        else {
//            String[] columns = new String[] {myDbCategory.column_category_name};
//            StringBuffer columns = new StringBuffer();
            List<String> columns = new ArrayList<>();
            List<Integer> icons = new ArrayList<>();
            while (cursor.moveToNext()) {
                String category = cursor.getString(1);
                if(category.equals("Choose category")){
                    continue;
                }
                columns.add(category);

                Integer icon = cursor.getInt(2);
                icons.add(icon);
            }

            String[] str = new String[columns.size()];
            for (int i = 0; i < columns.size(); i++) {
                str[i] = columns.get(i);
            }

            Integer[] icon_arr = new Integer[icons.size()];
            for (int i = 0; i < icons.size(); i++) {
                icon_arr[i] = icons.get(i);
            }

            //String[] to = new String[]{myDbCategory.column_category_icon};
//            Integer[] to = new Integer[Integer.valueOf(myDbCategory.column_category_icon)];
            CategoryListAdapter adapter = new CategoryListAdapter(this, str, icon_arr);
            catList.setAdapter(adapter);

        }
    }
    public void showMessage_C(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}