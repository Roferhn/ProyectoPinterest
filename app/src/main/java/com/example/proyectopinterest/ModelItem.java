package com.example.proyectopinterest;

public class ModelItem {

    private String mImageURL;
    private String mLikes;
    private String mAutor;

    public ModelItem() {
    }

    public ModelItem(String ImageURL, String Likes, String Autor) {
        mImageURL = ImageURL;
        mLikes = Likes;
        mAutor = Autor;
    }


    public String getAutor() {
        return mAutor;
    }

    public String getImageURL() {
        return mImageURL;
    }

    public String getLikes() {
        return mLikes;
    }
}
