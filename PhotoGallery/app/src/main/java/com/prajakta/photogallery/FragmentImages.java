package com.prajakta.photogallery;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class FragmentImages extends Fragment {

    private RecyclerView mRecyclerViewImages;
    private AdapterImages mAdapterImages;
    private ArrayList<PhotoInfo> mArrayListPhotoInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_images,null);

        mRecyclerViewImages = view.findViewById(R.id.recyclerImages);
        mArrayListPhotoInfo = new ArrayList<>();
        mAdapterImages = new AdapterImages(mArrayListPhotoInfo);

        mRecyclerViewImages.setAdapter(mAdapterImages);

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false);

        mRecyclerViewImages.setLayoutManager(gridLayoutManager);

        new AsyncThread(new PhotoInfoHandler()).execute();

        return view;
    }

     class PhotoInfoHandler extends Handler{

        @Override
        public void handleMessage(@NonNull Message msg) {

            if( msg.obj != null ) {
                ArrayList<PhotoInfo> photoInfos = (ArrayList<PhotoInfo>) msg.obj;
                Log.e("tag", "Size: " + photoInfos.size() );
                mArrayListPhotoInfo.addAll(photoInfos);
                mAdapterImages.notifyDataSetChanged();

                new ImageDownloadThread(new ImageHandler()).execute(mArrayListPhotoInfo);
            }
            else {
                Log.e("tag", "msg.obj is null");
            }

        }
    }

    class ImageHandler extends Handler{

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if ( msg.obj != null ){
                mArrayListPhotoInfo.get( msg.arg1 ).setImgUri( ( (String[]) msg.obj)[0] );
                mAdapterImages.notifyDataSetChanged();
                Log.e("tag", "Got: " + ( (String[]) msg.obj)[0] );
            }
        }
    }
}
