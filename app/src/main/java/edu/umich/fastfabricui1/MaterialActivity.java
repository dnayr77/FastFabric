package edu.umich.fastfabricui1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MaterialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

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
                String material_type = "wool";
                String session_id = "material";
                Intent intent = new Intent(MaterialActivity.this, ResultsActivity.class);
                intent.putExtra("Material_Type", material_type);
                intent.putExtra("Session_ID", session_id);
                startActivity(intent);
            }
        });
        //Cotton button
        cottonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String material_type = "cotton";
                String session_id = "material";
                Intent intent = new Intent(MaterialActivity.this, ResultsActivity.class);
                intent.putExtra("Material_Type", material_type);
                intent.putExtra("Session_ID", session_id);
                startActivity(intent);
            }
        });
        //polyester button
        polyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String material_type = "polyester";
                String session_id = "material";
                Intent intent = new Intent(MaterialActivity.this, ResultsActivity.class);
                intent.putExtra("Material_Type", material_type);
                intent.putExtra("Session_ID", session_id);
                startActivity(intent);
            }
        });
        // Vinyl button
        vinylButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String material_type = "vinyl";
                String session_id = "material";
                Intent intent = new Intent(MaterialActivity.this, ResultsActivity.class);
                intent.putExtra("Material_Type", material_type);
                intent.putExtra("Session_ID", session_id);
                startActivity(intent);
            }
        });
        //Other type of fabric button.
        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String material_type = "other";
                String session_id = "material";
                Intent intent = new Intent(MaterialActivity.this, ResultsActivity.class);
                intent.putExtra("Material_Type", material_type);
                intent.putExtra("Session_ID", session_id);
                startActivity(intent);
            }
        });

        getSupportActionBar().setTitle("FastFabric.io");


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
