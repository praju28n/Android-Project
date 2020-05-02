package com.prajakta.photogallery;

public class PhotoInfo{

    private String mImgUri;
    private String mAuthorName;
    private int mId;

    public String getAuthorName() {
        return mAuthorName;
    }

    public void setAuthorName(String authorName) {
        this.mAuthorName = authorName;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getImgUri() {
        return mImgUri;
    }

    public void setImgUri(String imgUri) {
        this.mImgUri = imgUri;
    }
}
