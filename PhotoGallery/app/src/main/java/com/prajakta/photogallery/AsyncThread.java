package com.prajakta.photogallery;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.webkit.HttpAuthHandler;

import java.util.ArrayList;

public class AsyncThread extends AsyncTask<Object,Object, ArrayList<PhotoInfo>> {

    private Handler mHandler;

    public AsyncThread(Handler handler) {
        this.mHandler = handler;
    }

    @Override
    protected ArrayList<PhotoInfo> doInBackground(Object... objects) {
        return WebUtil.getPhotoInfo();
    }

    @Override
    protected void onPostExecute(ArrayList<PhotoInfo> photoInfos) {

        Message msg = new Message();
        msg.obj = photoInfos;
        mHandler.sendMessage(msg);

    }
}
