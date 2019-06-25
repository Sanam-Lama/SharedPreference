package com.example.sharedpreferences2;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, email;
    Button save, retrieve, clear;

    SharedPreferences sharedPreferences;
    static final String mypreferences = "mypref";
    static final String Name = "nameKey";
    static final String Email = "emailKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);

        save = (Button)findViewById(R.id.buttonSave);
        retrieve = (Button)findViewById(R.id.buttonRetrieve);
        clear = (Button)findViewById(R.id.buttonClear);

        sharedPreferences = getSharedPreferences(mypreferences, Context.MODE_PRIVATE);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = name.getText().toString();
                String userEmail = email.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Name, userName);
                editor.putString(Email, userEmail);
                editor.commit();

                Toast.makeText(MainActivity.this, "Entered value is saved.", Toast.LENGTH_LONG).show();
            }
        });

        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getSharedPreferences(mypreferences, Context.MODE_PRIVATE);

                if (sharedPreferences.contains(Name)) {
                    name.setText(sharedPreferences.getString(Name, ""));
                }
                if (sharedPreferences.contains(Email)) {
                    email.setText(sharedPreferences.getString(Email, ""));
                }

                Toast.makeText(MainActivity.this, "Retrieving the saved value.", Toast.LENGTH_LONG).show();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               name.setText("");
               email.setText("");

               Toast.makeText(MainActivity.this, "Cleared the data.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
