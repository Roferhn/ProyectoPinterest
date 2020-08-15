package com.example.proyectopinterest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    private void GetDatos(){

        String url = "https://api.rawg.io/api/games";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray Juegos = response.getJSONArray("results");

                    for(int i=0; i<Juegos.length(); i++){
                        JSONObject object = Juegos.getJSONObject(i);
                        String name = object.getString("name");

                        TVResult.append(name + "\n\n");
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }


        });
        queue.add(request);
    }
}