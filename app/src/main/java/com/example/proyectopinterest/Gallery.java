package com.example.proyectopinterest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Gallery extends AppCompatActivity implements Adapter.onItemClickListener {

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_AUTOR = "AutorName";
    public static final String EXTRA_LIKES = "LikeCount";

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private ArrayList<ModelItem> mList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_rv);


        mRecyclerView =findViewById(R.id.RV);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJson();

    }

    private void parseJson() {


        String url= "https://pixabay.com/api/?key=17928583-32797671385677407f1267da7&q=kitty&image_type=photo&pretty=true";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String autorName = hit.getString("user");
                                String imageUrl = hit.getString("largeImageURL");
                                String likeCount = hit.getString("likes");

                                mList.add(new ModelItem(imageUrl,likeCount,autorName));

                            }

                            mAdapter = new Adapter(Gallery.this, mList);
                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.setOnItemClickListener(Gallery.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);

    }


    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, ImagesDetails.class);
        ModelItem clickedItem = mList.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedItem.getImageURL());
        detailIntent.putExtra(EXTRA_AUTOR, clickedItem.getAutor());
        detailIntent.putExtra(EXTRA_LIKES, clickedItem.getLikes());

        startActivity(detailIntent);
    }
}