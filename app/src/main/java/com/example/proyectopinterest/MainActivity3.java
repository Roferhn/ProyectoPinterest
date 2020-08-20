package com.example.proyectopinterest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.proyectopinterest.MainActivity2.EXTRA_AUTOR;
import static com.example.proyectopinterest.MainActivity2.EXTRA_LIKES;
import static com.example.proyectopinterest.MainActivity2.EXTRA_URL;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String autorName = intent.getStringExtra(EXTRA_AUTOR);
        String likeCount = intent.getStringExtra(EXTRA_LIKES);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewAutor = findViewById(R.id.text_view_autor_detail);
        TextView textViewLikes = findViewById(R.id.text_view_like_detail);

        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        textViewAutor.setText("Photo by: "+ autorName);
        textViewLikes.setText("Likes: " + likeCount);

    }
}