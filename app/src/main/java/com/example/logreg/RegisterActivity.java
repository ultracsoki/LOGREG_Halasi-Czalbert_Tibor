package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextFelhNev;
    private EditText editTextJelszo;
    private EditText editTextTeljesNev;
    private Button buttonRegisztracioValidacio;
    private Button buttonVissza;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        buttonRegisztracioValidacio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextEmail.getText().toString().isEmpty() || editTextFelhNev.getText().toString().isEmpty() || editTextJelszo.getText().toString().isEmpty() || editTextTeljesNev.getText().toString().isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Minden adatot meg kell adnia!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    adatRogzites();
                }
            }
        });

        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void adatRogzites()
    {
        String email = editTextEmail.getText().toString();
        String felhNev = editTextFelhNev.getText().toString();
        String jelszo = editTextJelszo.getText().toString();
        String teljesNev = editTextTeljesNev.getText().toString();
        if (dbHelper.rogzites(email,felhNev,jelszo,teljesNev))
        {

            Toast.makeText(this, "Az adat felvétele sikeres volt!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Az adat felvétele sikertelen volt!", Toast.LENGTH_SHORT).show();
        }
    }

    public void init()
    {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextFelhNev = findViewById(R.id.editTextFelhNev);
        editTextJelszo = findViewById(R.id.editTextJelszo);
        editTextTeljesNev = findViewById(R.id.editTextTeljesNev);
        buttonRegisztracioValidacio = findViewById(R.id.buttonRegisztracioValidacio);
        buttonVissza = findViewById(R.id.buttonVissza);
        dbHelper = new DbHelper(RegisterActivity.this);
    }
}