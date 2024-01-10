package com.example.t_managerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    //Create variables for the different elements
    EditText name, email, password;
    Button signUp;
    DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        //we initialize the variables created
        name = (EditText) findViewById(R.id.signup_name_input);
        email = (EditText) findViewById(R.id.signup_email_input);
        password = (EditText) findViewById(R.id.signup_password_input);
        signUp = (Button) findViewById(R.id.signup_btn);


        databaseHelper = new DatabaseHelper(this);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //assigning the different fields data
                String Uname = name.getText().toString();
                String Uemail = email.getText().toString();
                String Upassword = password.getText().toString();

                //checking if the fields are empty
                if (Uname.equals("")||Uemail.equals("")||Upassword.equals(""))
                    Toast.makeText(SignupActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else{
                    //if the email doesn't exists
                    Boolean checkuser = databaseHelper.checkemail(Uemail);
                    if (checkuser == false){
                        Boolean insert = databaseHelper.insertData(Uname,Uemail,Upassword);
                        if (insert == true){
                            Toast.makeText(SignupActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(SignupActivity.this, "Sorry unable to register you ", Toast.LENGTH_SHORT).show();
                        }
                        //in case the email already exists
                    }else {
                        Toast.makeText(SignupActivity.this, "Email already exists! please login", Toast.LENGTH_SHORT).show();
                    }
                    // In case the passwords don't match

                }
            }
        });


        //Takes you to the login Page
        TextView btn = findViewById(R.id.have_account);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}