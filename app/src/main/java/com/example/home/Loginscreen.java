package com.example.home;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*public class Loginscreen extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        setTitle("Login");
        Button signInButton_loginScreen=(Button) findViewById(R.id.signInButton_loginScreen); //creating a variable in On_create method -type button and nick naming it add button and using find view by id to search throug our resources for an id called add button
        signInButton_loginScreen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent new1=new Intent(getApplicationContext(), Mainactivity.class);
                startActivity(new1);
            }
        });
    }
}*/
public class Loginscreen extends AppCompatActivity
{
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        myDb = new DatabaseHelper(this);
        final Intent new1 = new Intent(getApplicationContext(), Mainactivity.class);
        if (myDb.isPinEnabled()) {
            setContentView(R.layout.activity_login_screen);
            Button signInButton_loginScreen = (Button) findViewById(R.id.signInButton_loginScreen); //creating a variable in On_create method -type button and nick naming it add button and using find view by id to search throug our resources for an id called add button

            signInButton_loginScreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText pinFromPrompt = (EditText) findViewById(R.id.pinEntryNum_loginScreen);
                    if(myDb.fetchCurrentPin().equals(pinFromPrompt.getText().toString()))
                        startActivity(new1);
                    else
                        Toast.makeText(getBaseContext(), "Invalid PIN!!!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else startActivity(new1);
    }
}


