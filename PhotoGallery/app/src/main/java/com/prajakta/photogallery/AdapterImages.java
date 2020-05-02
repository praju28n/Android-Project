package com.prajakta.photogallery;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterImages extends RecyclerView.Adapter<AdapterImages.ImageViewHolder> {

    private ArrayList<PhotoInfo> mArrayListPhotoInfo;

    public AdapterImages(ArrayList<PhotoInfo> arrayListPhotoInfo) {
        this.mArrayListPhotoInfo = arrayListPhotoInfo;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.author_info, null);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        PhotoInfo photoInfo = mArrayListPhotoInfo.get(position);

        holder.txtAuthorName.setText(photoInfo.getAuthorName());
        if (photoInfo.getImgUri() == null) {
            holder.imageView.setImageResource(R.drawable.ic_launcher_background);
        }
        else {
            holder.imageView.setImageURI(Uri.parse(photoInfo.getImgUri()));
        }
    }

    @Override
    public int getItemCount() {
        return mArrayListPhotoInfo.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        public TextView txtAuthorName;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAuthorName = itemView.findViewById(R.id.txtAuthorName);
            imageView = itemView.findViewById(R.id.imgGallery);
        }
    }

}
