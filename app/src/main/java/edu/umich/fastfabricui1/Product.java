package edu.umich.fastfabricui1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class Product extends AppCompatActivity {

    private String serverUrl = "http://167.172.144.70/";
    private String purchaseURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        // my_child_toolbar is defined in the layout file
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        final int index = getIntent().getIntExtra("fabric_index", 0);
        infoRequest(index);

        Button buyButton = findViewById(R.id.buy_now);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //purchaseFabric(index);
                    purchaseFabric(index);
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(purchaseURL));
                    startActivity(browserIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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

    public void purchaseFabric(int index) {
        String requestUrl = serverUrl + "purchase/?SC=231&index=" + Integer.toString(index);
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("test", "value");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest getRequest = new JsonObjectRequest(requestUrl, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    assert(response.getString("status") == "success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(getRequest);
    }


    public void infoRequest(int index) {
        String requestUrl = serverUrl + "info/?ID=1&index=" + Integer.toString(index);
        JSONObject jsonBody = null;

        JsonObjectRequest getRequest = new JsonObjectRequest(requestUrl, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String photoURL = response.getString("url");
                    purchaseURL = response.getString("purchase");
                    DecimalFormat df = new DecimalFormat("#.00");
                    String cost = "$" + df.format(response.getDouble("price")) + " per yard";
                    String material = response.getString("material");
                    String name = response.getString("name");
                    TextView newName = findViewById(R.id.name);
                    newName.setText(name);
                    TextView newCost = findViewById(R.id.cost);
                    newCost.setText(cost);
                    TextView newMaterial = findViewById(R.id.material);
                    newMaterial.setText(material);
                    new ImageLoad(photoURL, (ImageView) findViewById(R.id.fabricImage)).execute();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //TODO: handle failure
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(getRequest);
    }
}