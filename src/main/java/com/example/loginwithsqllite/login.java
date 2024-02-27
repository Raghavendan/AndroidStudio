package com.example.loginwithsqllite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class login extends AppCompatActivity {
    TextInputEditText name;
    TextInputEditText password;
    Button submit;
    TextView gotoregister;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Boolean e=false, p=false;
        name = findViewById(R.id.email);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.btn_login);
        gotoregister = findViewById(R.id.nextRegister);
        dbHelper = new DBHelper(getApplicationContext());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Password = password.getText().toString();

                Cursor cursor = dbHelper.getData();
                if(cursor.getCount() == 0)
                {
                    Toast.makeText(getApplicationContext(),"No entries Exists",Toast.LENGTH_LONG).show();
                }
                if(check(cursor, Name, Password)){
                    Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                    intent.putExtra("name",Name);
                    name.setText("");
                    password.setText("");
                    startActivity(intent);
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setCancelable(true);
                    builder.setTitle("wrong credential");
                    builder.setMessage("wrong credential");
                    builder.show();
                    dbHelper.close();
                }
            }
        });
        gotoregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), register.class);
                startActivity(intent);
            }
        });
    }

    private boolean check(Cursor cursor, String name, String password) {
        while (cursor.moveToNext()){
            if(cursor.getString(0).equals(name)){
                if(cursor.getString(1).equals(password)){
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}