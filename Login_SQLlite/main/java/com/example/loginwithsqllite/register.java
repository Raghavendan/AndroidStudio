package com.example.loginwithsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class register extends AppCompatActivity {

    TextInputEditText name,password;
    Button submit;
    DBHelper dbHelper;
    TextView gotologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.email);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.btn_register);
        gotologin = findViewById(R.id.nextLogin);

        dbHelper = new DBHelper(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Password = password.getText().toString();
                boolean b = dbHelper.register(Name,Password);
                if(b){
                    Toast.makeText(getApplicationContext(),"Data inserted",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), login.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Failed To insert Data",Toast.LENGTH_SHORT).show();
                }
            }
        });
        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), login.class);
                startActivity(i);
            }
        });
    }
}