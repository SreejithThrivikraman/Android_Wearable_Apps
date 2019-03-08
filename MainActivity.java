package com.madt.sree.assignment_2;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends WearableActivity {


    public EditText username,password;
    public Button login,signup;
    public SQLiteDatabase database;
    public Boolean login_success = false;
    private DatabaseHelper DB;
    private boolean flag_check_username = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.Edit_username);
        password = (EditText) findViewById(R.id.Edit_password);

        login = (Button)findViewById(R.id.Login);
        signup = (Button)findViewById(R.id.SignUp);

        DB = new DatabaseHelper(getApplicationContext());

        database= DB.getReadableDatabase();

        System.out.println("Helooooooo");

        // Enables Always-on
        setAmbientEnabled();

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                System.out.println("Name = " + username.getText().toString() );
                login_success = DB.successful_login_check(username.getText().toString(),password.getText().toString());

                if(login_success == true)
                {
                    Toast.makeText(getApplicationContext(),"Hello " +username.getText().toString(),Toast.LENGTH_SHORT).show();
                }

                else
                {
                    flag_check_username = DB.search_existing_user_name(username.getText().toString());

                    if (flag_check_username == true)
                    {
                        Toast.makeText(getApplicationContext(),"Wrong Password !" ,Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Username is not found !" ,Toast.LENGTH_SHORT).show();
                        signup.setEnabled(true);
                    }


                }
            }
        });


        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent signUp = new Intent(getApplicationContext(),SignUp.class);
                startActivity(signUp);

            }
        });


    }



    public void check_username_password(String u_name)
    {

    }

    public void signIn(String u_name, String u_pass)
    {


    }
}
