package com.prajakta.photogallery;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class ImageDownloadThread extends AsyncTask<ArrayList<PhotoInfo>,String,Object> {

    private Handler mHandler;
    private static int i;

    public ImageDownloadThread(Handler handler) {
        this.mHandler = handler;
    }

    @Override
    protected Object doInBackground(ArrayList<PhotoInfo>... arrayLists) {

        for (i = 0; i< arrayLists[0].size(); i++){
            int id = arrayLists[0].get(i).getId();
            String imgUri = WebUtil.downloadImage(id);
            publishProgress(imgUri);
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        if ( mHandler!= null ) {
            Message message = new Message();
            message.obj = values;
            message.arg1 = i;
            mHandler.sendMessage(message);
        }
    }
}
