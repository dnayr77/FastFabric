package edu.umich.fastfabricui1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class PreferPrice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefer_price);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        Button threeButton = findViewById(R.id.three);
        Button fiveButton = findViewById(R.id.five);
        Button sevenButton = findViewById(R.id.seven);
        Button nineButton = findViewById(R.id.nine);
        Button anypriceButton = findViewById(R.id.anyprice);
        // three Button
        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addCost(3.00);
                startActivity(new Intent(PreferPrice.this, PreferStrengthActivity.class));
            }
        });

        // five Button
        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addCost(5.00);
                startActivity(new Intent(PreferPrice.this, PreferStrengthActivity.class));
            }
        });

        // seven Button
        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addCost(7.00);
                startActivity(new Intent(PreferPrice.this, PreferStrengthActivity.class));
            }
        });
        // nine Button
        nineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addCost(9.00);
                startActivity(new Intent(PreferPrice.this, PreferStrengthActivity.class));
            }
        });
        // any price Button
        anypriceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addCost(0.0);
                startActivity(new Intent(PreferPrice.this, PreferStrengthActivity.class));
            }
        });

    }

    public void addCost(Double cost) {
        ((FastFabric) this.getApplication()).addCost(cost);
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
