package com.example.home;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

public class EmailReport extends AppCompatActivity
{
    DatabaseHelper myDb;
    private EditText mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_report);
        setTitle("Email Report");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myDb = new DatabaseHelper(this);
        Cursor res_E = myDb.getAllData();
        StringBuffer buffer = new StringBuffer();
        buffer.append("--------------------------------\n");
        buffer.append(" Transaction Summary:\n");
        buffer.append("--------------------------------\n");
        while (res_E.moveToNext()) {
            buffer.append("Transaction Number :" + res_E.getString(0) + "\n");
            buffer.append("Transaction Type :" + res_E.getString(1) + "\n");
            buffer.append("Category :" + res_E.getString(2) + "\n");
            buffer.append("Date :" + res_E.getString(3) + "\n");
            buffer.append("Reccurrency :" + res_E.getString(4) + "\n");
            buffer.append("Amount :" + res_E.getString(5) + "\n");
            buffer.append("Payment :" + res_E.getString(6) + "\n");
            buffer.append("Currency :" + res_E.getString(7) + "\n");
            buffer.append("Note :" + res_E.getString(8) + "\n\n\n");
        }
       // buffer.append("--------------------------------\n");
        //buffer.append("All Income Transactions:\n");
       // buffer.append("--------------------------------\n");
        Cursor res_I = myDb.getAllData_I();

        while (res_I.moveToNext()) {
            buffer.append("Income ID :" + res_I.getString(0) + "\n");
            buffer.append("Category :" + res_I.getString(1) + "\n");
            buffer.append("Date :" + res_I.getString(2) + "\n");
            buffer.append("Reccurrency :" + res_I.getString(3) + "\n");
            buffer.append("Amount :" + res_I.getString(4) + "\n");
            buffer.append("Payment :" + res_I.getString(5) + "\n");
            buffer.append("Note :" + res_I.getString(7) + "\n");
            buffer.append("Currency :" + res_I.getString(6) + "\n\n\n");
        }


        mEditTextTo = findViewById(R.id.edit_text_to);
        mEditTextSubject = findViewById(R.id.edit_text_subject);
        mEditTextMessage = findViewById(R.id.edit_text_message);

        mEditTextMessage.setText(buffer.toString());
        mEditTextSubject.setText("Income & Expense Transaction Report");
        Button buttonSend = findViewById(R.id.button_send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }
        private void sendMail() {
            String recipientList = mEditTextTo.getText().toString();
            String[] recipients = recipientList.split(",");

            String subject = mEditTextSubject.getText().toString();
            String message = mEditTextMessage.getText().toString();

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, message);

            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent, "Choose an email client"));
        }

}
