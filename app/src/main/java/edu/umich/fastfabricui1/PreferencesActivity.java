package edu.umich.fastfabricui1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        //setTitle("FastFabric.io");

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        Button woolButton = findViewById(R.id.wool);
        Button cottonButton = findViewById(R.id.cotton);
        Button polyButton = findViewById(R.id.polyester);
        Button vinylButton = findViewById(R.id.vinyl);
        Button otherButton = findViewById(R.id.other_material);

        // wool Button
        woolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addMaterial("wool");
                startActivity(new Intent(PreferencesActivity.this, PreferPrice.class));
            }
        });

        // cotton Button
        cottonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addMaterial("cotton");
                startActivity(new Intent(PreferencesActivity.this, PreferPrice.class));
            }
        });

        // poly Button
        polyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addMaterial("poly");
                startActivity(new Intent(PreferencesActivity.this, PreferPrice.class));
            }
        });

        // vinyl Button
        vinylButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addMaterial("vinyl");
                startActivity(new Intent(PreferencesActivity.this, PreferPrice.class));
            }
        });

        // other Button
        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addMaterial("other");
                startActivity(new Intent(PreferencesActivity.this, PreferPrice.class));
            }
        });

        getSupportActionBar().setTitle("FastFabric.io");


    }

    public void addMaterial(String material) {
        ((FastFabric) this.getApplication()).addMaterial(material);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.home:
                login(item);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void login(MenuItem item) {
        startActivity(new Intent(this, MainActivity.class));
    }
}