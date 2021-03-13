package edu.umich.fastfabricui1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity implements ResultListAdapter.OnResultListener, ResultFragment.OnLearnListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter fabricAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Result> fabricResults;
    private String serverUrl = "http://167.172.144.70/";
    private ProgressBar loading;
    private List<Integer> likedFabric;
    private List<Integer> dislikedFabric;
    private String sessionId;
    private ResultFragment alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        setTitle("FastFabric.io");
        // sets the toolbar and back button
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);


        recyclerView = findViewById(R.id.recycler_view);

        // init values
        fabricResults = new ArrayList<>();
        fabricAdapter = new ResultListAdapter(getApplicationContext(), fabricResults, this);
        likedFabric = ((FastFabric) this.getApplication()).getLikedFabric();
        dislikedFabric = ((FastFabric) this.getApplication()).getDislikedFabric();

        // Not sure about this but it works
        recyclerView.setHasFixedSize(true);

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);

        // Set the adapter
        recyclerView.setAdapter(fabricAdapter);

        // See where we came from
        sessionId = getIntent().getStringExtra("Session_ID");

        // If last activity was materials
        if (sessionId.equals("material")) {
            String materialType = getIntent().getStringExtra("Material_Type");
            Log.d("search","running material search");
            MaterialRequest(materialType);
        }
        // If last activity was photos
        else if(sessionId.equals("photo_search")){
            String photoString = getIntent().getStringExtra("Image_String");
            Log.d("search","running image search");
            PhotoRequest(photoString);
        }
        else if(sessionId.equals("parameter")){
            String parameter = getIntent().getStringExtra("Material_Type");
            Log.d("search","running parameter search");
            ParameterRequest(parameter);
        }
        else if(sessionId.equals("color")){
            String color_string = getIntent().getStringExtra("Color_Type");
            Log.d("search","running color search");
            ColorRequest(color_string);
        }
        else if(sessionId.equals("history")){
            String user_id = getIntent().getStringExtra("User_ID");
            Log.d("search", "running history search");
            HistoryRequest(user_id);
        }
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

    @Override
    public void onResultClick(int position) {
        //Intent intent = new Intent(this, MainActivity.class);
        Log.d("click", "adapter clicked");
        showResultDialog(position);
    }

    @Override
    public void onLearnClick(int position) {
        int session_id = fabricResults.get(position).getIndex();
        Intent intent = new Intent(ResultsActivity.this, Product.class);
        intent.putExtra("fabric_index", session_id);
        startActivity(intent);
    }

    @Override
    public void onLikeClick(int position) {
        Integer session_id = new Integer(fabricResults.get(position).getIndex());
        fabricResults.clear();
        ((FastFabric) this.getApplication()).addLike(session_id);
        likedFabric = ((FastFabric) this.getApplication()).getLikedFabric();
        // If last activity was materials
        if (sessionId.equals("material")) {
            String materialType = getIntent().getStringExtra("Material_Type");
            Log.d("search","running material search");
            MaterialRequest(materialType);
        }
        // If last activity was photos
        else if(sessionId.equals("photo_search")){
            String photoString = getIntent().getStringExtra("Image_String");
            Log.d("search","running image search");
            PhotoRequest(photoString);
        }
        else if(sessionId.equals("parameter")){
            String parameter = getIntent().getStringExtra("Material_Type");
            Log.d("search","running parameter search");
            ParameterRequest(parameter);
        }
        else if(sessionId.equals("color")){
            String color_string = getIntent().getStringExtra("Color_Type");
            Log.d("search","running color search");
            ColorRequest(color_string);
        }
        else if(sessionId.equals("history")){
            String user_id = getIntent().getStringExtra("User_id");
            Log.d("search", "running history search");
            HistoryRequest(user_id);
        }
        alertDialog.dismiss();
    }

    @Override
    public void onDislikeClick(int position) {
        Integer session_id = new Integer(fabricResults.get(position).getIndex());
        ((FastFabric) this.getApplication()).addDislike(session_id);
        dislikedFabric = ((FastFabric) this.getApplication()).getDislikedFabric();
        fabricResults.clear();
        // If last activity was materials
        if (sessionId.equals("material")) {
            String materialType = getIntent().getStringExtra("Material_Type");
            Log.d("search","running material search");
            MaterialRequest(materialType);
        }
        // If last activity was photos
        else if(sessionId.equals("photo_search")){
            String photoString = getIntent().getStringExtra("Image_String");
            Log.d("search","running image search");
            PhotoRequest(photoString);
        }
        else if(sessionId.equals("parameter")){
            String parameter = getIntent().getStringExtra("Material_Type");
            Log.d("search","running parameter search");
            ParameterRequest(parameter);
        }
        else if(sessionId.equals("color")){
            String color_string = getIntent().getStringExtra("Color_Type");
            Log.d("search","running color search");
            ColorRequest(color_string);
        }
        else if(sessionId.equals("history")){
            String user_id = getIntent().getStringExtra("User_id");
            Log.d("search", "running history search");
            HistoryRequest(user_id);
        }
        alertDialog.dismiss();
    }

    private void showResultDialog(int position) {
        Log.d("click", "dialog shown");
        FragmentManager fm = getSupportFragmentManager();
        alertDialog = ResultFragment.newInstance(position);
        alertDialog.show(fm, "fragment_alert");
    }

    public void MaterialRequest(String material) {
        String requestUrl = serverUrl + "material/?ID=1";
        JSONArray dislikedArray = new JSONArray(dislikedFabric);
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("material", material);
            jsonBody.put("disliked", dislikedArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("json", jsonBody.toString());

        JsonObjectRequest getRequest = new JsonObjectRequest(requestUrl, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("response", response.toString());
                    Log.d("response", response.getJSONObject("0").toString());
                    Log.d("response", Integer.toString(response.length()));
                    for (int i = 0; i < response.length(); i++) {
                        int index = response.getJSONObject(Integer.toString(i)).getInt("index");
                        String photoUrl = response.getJSONObject(Integer.toString(i)).getString("url");
                        String name = response.getJSONObject(Integer.toString(i)).getString("name");
                        String cost = Double.toString(response.getJSONObject(Integer.toString(i)).getDouble("price"));
                        Log.d("result", "looping");
                        fabricResults.add(new Result(photoUrl, name, cost, index));
                    }
                    fabricAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //TODO: handle failure
                Log.d("server", "Server response issue in MaterialRequest");
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(getRequest);
    }

    private void PhotoRequest(String photoString) {
        ConstraintLayout layout = findViewById(R.id.result_layout_id);
        loading = new ProgressBar(ResultsActivity.this, null, android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout.addView(loading, params);
        loading.setVisibility(View.VISIBLE);

        String requestUrl = serverUrl + "search/?ID=1";
        JSONArray dislikedArray = new JSONArray(dislikedFabric);
        JSONArray likedArray = new JSONArray(likedFabric);
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("image", photoString);
            jsonBody.put("disliked", dislikedArray);
            jsonBody.put("liked", likedArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("json", jsonBody.toString());

        JsonObjectRequest getRequest = new JsonObjectRequest(requestUrl, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    loading.setVisibility(View.GONE);
                    Log.d("response", response.toString());
                    Log.d("response", response.getJSONObject("0").toString());
                    Log.d("response", Integer.toString(response.length()));
                    for (int i = 0; i < response.length(); i++) {
                        int index = response.getJSONObject(Integer.toString(i)).getInt("index");
                        String photoUrl = response.getJSONObject(Integer.toString(i)).getString("url");
                        String name = response.getJSONObject(Integer.toString(i)).getString("name");
                        String cost = Double.toString(response.getJSONObject(Integer.toString(i)).getDouble("price"));
                        Log.d("result", "looping");
                        fabricResults.add(new Result(photoUrl, name, cost, index));
                    }
                    fabricAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //TODO: handle failure
                Log.d("server", "Server response issue in ImageRequest");
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(getRequest);
    }

    public void ParameterRequest(String parameter) {
        String requestUrl = serverUrl + "filter/?ID=1";
        JSONArray dislikedArray = new JSONArray(dislikedFabric);
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("parameter", parameter);
            jsonBody.put("disliked", dislikedArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("json", jsonBody.toString());

        JsonObjectRequest getRequest = new JsonObjectRequest(requestUrl, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("response", response.toString());
                    Log.d("response", response.getJSONObject("0").toString());
                    Log.d("response", Integer.toString(response.length()));
                    for (int i = 0; i < response.length(); i++) {
                        int index = response.getJSONObject(Integer.toString(i)).getInt("index");
                        String photoUrl = response.getJSONObject(Integer.toString(i)).getString("url");
                        String name = response.getJSONObject(Integer.toString(i)).getString("name");
                        String cost = Double.toString(response.getJSONObject(Integer.toString(i)).getDouble("price"));
                        Log.d("result", "looping");
                        fabricResults.add(new Result(photoUrl, name, cost, index));
                    }
                    fabricAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //TODO: handle failure
                Log.d("server", "Server response issue in ParameterRequest");
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(getRequest);
    }

    public void ColorRequest(String color) {
        String requestUrl = serverUrl + "color/?ID=1";
        JSONArray dislikedArray = new JSONArray(dislikedFabric);
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("color", color);
            jsonBody.put("disliked", dislikedArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("json", jsonBody.toString());

        JsonObjectRequest getRequest = new JsonObjectRequest(requestUrl, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("response", response.toString());
                    Log.d("response", response.getJSONObject("0").toString());
                    Log.d("response", Integer.toString(response.length()));
                    for (int i = 0; i < response.length(); i++) {
                        int index = response.getJSONObject(Integer.toString(i)).getInt("index");
                        String photoUrl = response.getJSONObject(Integer.toString(i)).getString("url");
                        String name = response.getJSONObject(Integer.toString(i)).getString("name");
                        String cost = Double.toString(response.getJSONObject(Integer.toString(i)).getDouble("price"));
                        Log.d("result", "looping");
                        fabricResults.add(new Result(photoUrl, name, cost, index));
                    }
                    fabricAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //TODO: handle failure
                Log.d("server", "Server response issue in ColorRequest");
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(getRequest);
    }

    public void HistoryRequest(String SC){
        String requestUrl = serverUrl + "account/?SC=" + SC;
        JSONObject jsonBody = null;
        //Json request
        JsonObjectRequest getRequest = new JsonObjectRequest(requestUrl, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        int index = response.getJSONObject(Integer.toString(i)).getInt("index");
                        String photoUrl = response.getJSONObject(Integer.toString(i)).getString("url");
                        String name = response.getJSONObject(Integer.toString(i)).getString("name");
                        String cost = Double.toString(response.getJSONObject(Integer.toString(i)).getDouble("price"));
                        Log.d("result", "looping");
                        fabricResults.add(new Result(photoUrl, name, cost, index));
                    }
                    fabricAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //TODO: handle failure
                Log.d("server", "Server response issue in HistoryRequest");
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(getRequest);
    }
}
