package com.example.t_managerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotpasswordActivity extends AppCompatActivity {
     Button nextbtn;
     EditText email;
     DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        email = findViewById(R.id.forgot_email_input);
        nextbtn = findViewById(R.id.confirm_btn);
        databaseHelper = new DatabaseHelper(this);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Uemail = email.getText().toString();
                Boolean checkUemail = databaseHelper.checkemail(Uemail);
                if (checkUemail==true){
                    Intent intent = new Intent(getApplicationContext(), ResetpasswordActivity.class);
                    intent.putExtra("email", Uemail);
                    startActivity(intent);
                }else {
                    Toast.makeText(ForgotpasswordActivity.this, "Invalid email or email doesnt exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}