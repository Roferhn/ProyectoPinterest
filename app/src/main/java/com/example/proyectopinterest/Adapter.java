package com.example.proyectopinterest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    //-------------------------------------------------------//-----------------------------------------------------------
    private Context mContext;
    private ArrayList<ModelItem> mList;

    public Adapter(Context context, ArrayList<ModelItem> list) {
        mContext = context;
        mList = list;
    }



    //-----------------------------------------------------------//-----------------------------------------------------------

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item,parent, false);
        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelItem currentItem = mList.get(position);
        String imageUrl = currentItem.getImageURL();
        String autorName = currentItem.getAutor();
        String likesCount = currentItem.getLikes();

       holder.mTextviewAutor.setText("Photo by: "+autorName);
//        holder.mTextviewLikes.setText("Likes: " + likesCount);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
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
//            mTextviewLikes = itemView.findViewById(R.id.text_view_likes);
        }
    }

}
