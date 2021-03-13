package edu.umich.fastfabricui1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { //When app starts make listener for each button.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        setTitle("FastFabric.io");
        //differnet ways to find button
        Button materialButton = findViewById(R.id.material);
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(CategoryActivity.this, MaterialActivity.class));
            }
        });

        Button brandButton = findViewById(R.id.brand);
        brandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(CategoryActivity.this, BrandActivity.class));
            }
        });

        Button strengthButton = findViewById(R.id.strength);
        strengthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(CategoryActivity.this, StrengthActivity.class));
            }
        });

        Button textureButton = findViewById(R.id.texture);
        textureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(CategoryActivity.this, TextureActivity.class));
            }
        });

        Button colorButton = findViewById(R.id.color);
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(CategoryActivity.this, ColorActivity.class));
            }
        });

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

    }

    // On Menu creation
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

//End of Category Activity.
