package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextFelhEmail;
    private EditText editTextJelszoBejelentkezes;
    private Button buttonBejelentkezes;
    private Button buttonRegisztracio;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        buttonBejelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextFelhEmail.getText().toString().isEmpty() || editTextJelszoBejelentkezes.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Minden mezőt ki kell töltenie!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(dbHelper.bejelentkezesFelh(editTextFelhEmail.getText().toString(),editTextJelszoBejelentkezes.getText().toString()))
                    {
                        Intent intent = new Intent(MainActivity.this,LoggedInActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else if (dbHelper.bejelentkezesEmail(editTextFelhEmail.getText().toString(),editTextJelszoBejelentkezes.getText().toString()))
                    {
                        Intent intent = new Intent(MainActivity.this,LoggedInActivity.class);
                        startActivity(intent);
                        finish();
                    } else
                    {
                        Toast.makeText(MainActivity.this, "A bejelentkezés nem sikerült!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonRegisztracio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init()
    {
        editTextFelhEmail = findViewById(R.id.editTextFelhEmail);
        editTextJelszoBejelentkezes = findViewById(R.id.editTextJelszoBejelentkezes);
        buttonBejelentkezes = findViewById(R.id.buttonBejelentkezes);
        buttonRegisztracio = findViewById(R.id.buttonRegisztracio);
        dbHelper = new DbHelper(MainActivity.this);
    }
}

