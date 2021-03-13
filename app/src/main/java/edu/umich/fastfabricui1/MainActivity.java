package edu.umich.fastfabricui1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    private LoginFragment alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button imageButton = findViewById(R.id.cameraSearch);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, ImageActivity.class));
            }
        });

        Button categoryButton = findViewById(R.id.categorySearch);
        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, CategoryActivity.class));
            }
        });

        Button historyButton = findViewById(R.id.history);
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_id = "231";
                String session_id = "history";
                Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                intent.putExtra("User_ID", user_id);
                intent.putExtra("Session_ID", session_id);
                startActivity(intent);
            }
        });

        Button preferencesButton = findViewById(R.id.preferences);
        preferencesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, PreferencesActivity.class));
            }
        });

        // my_child_toolbar is defined in the layout file
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        MenuItem item = menu.findItem(R.id.home);
        item.setTitle("Login");
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
        Log.d("click", "dialog shown");
        FragmentManager fm = getSupportFragmentManager();
        alertDialog = LoginFragment.newInstance();
        alertDialog.show(fm, "fragment_alert");
    }
}
