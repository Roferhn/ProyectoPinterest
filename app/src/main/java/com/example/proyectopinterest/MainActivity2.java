package com.example.proyectopinterest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Display;

import com.android.volley.RequestQueue;
import com.example.proyectopinterest.R;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private ArrayList<ModelItem> mList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


    }
}