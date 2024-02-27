package com.example.loginwithsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text;
    @Override
    public void onBackPressed(){
        MainActivity.this.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       text = findViewById(R.id.username);
        Intent intent = getIntent();
        String s2 = intent.getStringExtra("name");
        text.setText(""+ s2);
    }
}