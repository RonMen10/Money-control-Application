//singleDatabase corresponds to dbhelper

package com.example.home;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.Date;
import java.util.List;
import static android.content.ContentValues.TAG;
public final class SingleDatabase extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME = "MoneyControl.db";

    public static final String TABLE_EXPENSES="Expenses";
    public static final String column_expense_ID="Expense_ID";
    public static final String column_date_E="Date";
    public static final String column_amount_E="Amount";
    public static final String column_category_E="Category";
    public static final String column_payment_E="Payment";
    public static final String column_currency_E="Currency";
    public static final String column_note_E="Note";
    public static final String column_recurrency_E="Recurrency";

    public static final String TABLE_INCOMES="Incomes";
    public static final String column_income_ID="Income_ID";
    public static final String column_date_I="Date";
    public static final String column_amount_I="Amount";
    public static final String column_category_I="Category";
    public static final String column_payment_I="Payment";
    public static final String column_currency_I="Currency";
    public static final String column_note_I="Note";
    public static final String column_recurrency_I="Recurrency";

    public static final String Table_pin="pin";


    public SingleDatabase(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String SQL_CREATE_TABLE_EXPENSE = "CREATE TABLE TABLE_EXPENSES"+ "("
                + "column_expense_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "column_date_E  DATE  ,"
                + "column_amount_E  INTEGER ,"
                + "column_category_E  TEXT ,"
                + "column_payment_E  TEXT ,"
                + "column_currency_E  TEXT ,"
                + "column_note_E  TEXT DEFAULT 'No value entered',"
                + "column_recurrency_E  TEXT);";
        db.execSQL(SQL_CREATE_TABLE_EXPENSE);

        String SQL_CREATE_TABLE_INCOME = "CREATE TABLE TABLE_INCOMES "+"("
                + "column_income_ID  INTEGER PRIMARY KEY AUTOINCREMENT,"
                +  "column_date_I  DATE  ,"
                + "column_amount_I   REAL ,"
                + "column_category_I   TEXT ,"
                + "column_payment_I   TEXT ,"
                + "column_currency_I  TEXT ,"
                + "column_note_I   TEXT DEFAULT 'No value entered',"
                + "column_recurrency_I  TEXT);";
        db.execSQL(SQL_CREATE_TABLE_INCOME);

    }
    // we need to creaet a table where we can move the data from existing tables to another table before clearing it using the below code as the user might lose the data if not done
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) // check the db
    {
        //clear all data
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_EXPENSES);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_INCOMES);
        //recreate the DB
        onCreate(db);
    }
    public boolean insertData(String Date, String Amount, String Category, String Payment, String Currency, String Note, String Recurrency )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(column_date_E, Date);
        contentValues.put(column_amount_E, Amount);
        contentValues.put(column_category_E, Category);
        contentValues.put(column_payment_E, Payment);
        contentValues.put(column_currency_E, Currency);
        contentValues.put(column_note_E, Note);
        contentValues.put(column_recurrency_E, Recurrency);
        long result = db.insert(TABLE_EXPENSES,null,contentValues);


        if (result==-1)
            return false;
        else
            return true;
    }

}


/*sadfasdfasdfasdfasdf
package com.example.budget;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MoneyControl.db";

    public static final String TABLE_BUDGET = "Budget"; // TABLE_NAME
    public static final String column_budget_ID = "Budget_ID";  // COL_1
    public static final String column_amount_B = "Amount_B";  // COL_2
    public static final String column_category_B = "Category_B"; //COl_3
    public static final String column_date_B = "Date_B"; //COL_4
    public static final String column_Time_B = "Time"; //COL_5

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_BUDGET +" (Budget_ID INTEGER PRIMARY KEY AUTOINCREMENT,Amount_B INTEGER,Category_B TEXT,Date_B TEXT,Time TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BUDGET);
        onCreate(db);
    }

    public boolean insertData(String Amount_B,String Category_B,String Date_B,String Time)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_amount_B,Amount_B);
        contentValues.put(column_category_B,Category_B);
        contentValues.put(column_date_B,Date_B);
        contentValues.put(column_Time_B,Time);



        long result = db.insert(TABLE_BUDGET,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Integer deleteData (String expenseID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_BUDGET, "Expense_ID = ?",new String[] {expenseID});
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor bdg = db.rawQuery("select * from "+TABLE_BUDGET,null);
        return bdg;
    }
    public int lastAmount;
    public int chartQuery1() {
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor insuranceAmount = db1.rawQuery( "select last " + column_amount_B + " from " + TABLE_BUDGET + " Where " + column_category_B,  null);
        int lastAmount= insuranceAmount.getInt(0);
        return lastAmount;
    }
    public int getLastAmount(){
        return this.lastAmount;
    }


}
 */