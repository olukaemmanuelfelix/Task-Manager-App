package com.example.t_managerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetpasswordActivity extends AppCompatActivity {
    EditText    newPass, confirmPass;
    Button resetbtn;
    TextView Uemail;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        newPass = findViewById(R.id.newpass_input);
        confirmPass =findViewById(R.id.confirmPass_input);
        Uemail = findViewById(R.id.Username_reset_text);
        resetbtn = findViewById(R.id.reset_btn);


      //  intent.putExtra("email", Uemail);
        Intent intent = getIntent();
        Uemail.setText(intent.getStringExtra("Uemail"));

        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String Pass = newPass.getText().toString();
               String user = Uemail.getText().toString();
               String repeat_pass = confirmPass.getText().toString();


               if (Pass.equals(repeat_pass)){
                   Boolean check_pass_update = databaseHelper.updatepassword(user, Pass);
                   if (check_pass_update == true){
                       Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                       startActivity(intent);
                       Toast.makeText(ResetpasswordActivity.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                   }else {
                       Toast.makeText(ResetpasswordActivity.this, "Failed to changed password sorry", Toast.LENGTH_SHORT).show();
                   }
               }else {
                   Toast.makeText(ResetpasswordActivity.this, "Password don't match", Toast.LENGTH_SHORT).show();
               }

            }
        });
    }
}