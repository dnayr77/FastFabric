package edu.umich.fastfabricui1;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

public class ServerRequest {

    private String serverUrl;
    private List<Result> results;
    RequestQueue requestQueue;

    public ServerRequest(List<Result> refList, RequestQueue refQueue) {
        this.serverUrl = "http://167.172.144.70/";
        this.results = refList;
        this.requestQueue = refQueue;
    }


    public void MaterialRequest(String material) {
        String requestUrl = serverUrl + "material/?ID=1";
        JSONArray emptyArray = new JSONArray();
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("material", material);
            jsonBody.put("disliked", emptyArray);
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
                        String cost = response.getJSONObject(Integer.toString(i)).getString("price");
                        Log.d("result", "looping");
                        results.add(new Result(photoUrl, name, cost, index));
                    }
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
        requestQueue.add(getRequest);
    }

}
