package com.example.t_managerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    TextView forgot;
    DatabaseHelper databaseHelper;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.login_email_input);
        password = (EditText)  findViewById(R.id.login_password_input);
        login = (Button) findViewById(R.id.login_btn);
        databaseHelper = new DatabaseHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Uemail = email.getText().toString();
                String Upassword = password.getText().toString();

                if (Uemail.equals("")||Upassword.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkUpass = databaseHelper.checkemailpassword(Uemail, Upassword);
                    if (checkUpass == true){
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // takes you to forgot password page
        forgot = findViewById(R.id.forgot_password);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ForgotpasswordActivity.class));
            }
        });

        //Takes you to the Signup Page
        Button btn = findViewById(R.id.signup2_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignupActivity.class));
                finish();
            }
        });

    }
}