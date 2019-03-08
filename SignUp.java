package com.madt.sree.assignment_2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends WearableActivity {


    EditText name, password, address, age;
    Button signup_button;
    public SQLiteDatabase DH;
    DatabaseHelper database;
    private boolean flag = false;
    private boolean user_name_check_flag = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.signup_username);
        password = findViewById(R.id.signup_password);
        address = findViewById(R.id.signup_address);
        age = findViewById(R.id.signup_Age);

        signup_button = findViewById(R.id.signup_button);

        // Enables Always-on
        setAmbientEnabled();


        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = new DatabaseHelper(getApplicationContext());
                DH = database.getWritableDatabase();
                user_name_check_flag = database.search_existing_user_name(name.getText().toString());


                Log.e("Sreejith >>>>","user_name_check_flag = " + user_name_check_flag);
                if (user_name_check_flag == false)
                {
                    flag = database.insertData(name.getText().toString(), password.getText().toString());

                    if (!flag)
                    {
                        Toast.makeText(getApplicationContext(), "Insert failed !", Toast.LENGTH_SHORT).show();
                    }
                    else
                        {
                            Toast.makeText(getApplicationContext(), "User Added successfully !", Toast.LENGTH_SHORT).show();
                            Intent back_to_login = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(back_to_login);
                        }
                }
                else
                    {
                        Toast.makeText(getApplicationContext(), "username already exists !", Toast.LENGTH_SHORT).show();
                    }


            }


        });
    }
}
