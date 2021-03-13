package edu.umich.fastfabricui1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class PreferStrengthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefer_strength);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        Button tensileButton = findViewById(R.id.tensile);
        Button punctureButton = findViewById(R.id.puncture);
        Button shearButton = findViewById(R.id.shear);

        // tensile Button
        tensileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addStrength("tensile");
                startActivity(new Intent(PreferStrengthActivity.this, MainActivity.class));
            }
        });

        // puncture Button
        punctureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addStrength("tensile");
                startActivity(new Intent(PreferStrengthActivity.this, MainActivity.class));
            }
        });

        // shear Button
        shearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addStrength("tensile");
                startActivity(new Intent(PreferStrengthActivity.this, MainActivity.class));
            }
        });
    }

    public void addStrength(String strength) {
        ((FastFabric) this.getApplication()).addStrength(strength);
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
