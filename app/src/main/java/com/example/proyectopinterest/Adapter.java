package com.example.proyectopinterest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    //-------------------------------------------------------//-----------------------------------------------------------
    private Context mContext;
    private ArrayList<ModelItem> mList;

    public Adapter(Context context, ArrayList<ModelItem> list) {
        mContext = context;
        mList = list;
    }



    //----------------------------------------------------------//-----------------------------------------------------------

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mTextviewAutor;
        public TextView mTextviewLikes;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image_view);
            mTextviewAutor = itemView.findViewById(R.id.text_view_autor);
            mTextviewLikes = itemView.findViewById(R.id.text_view_likes);
        }
    }

}
