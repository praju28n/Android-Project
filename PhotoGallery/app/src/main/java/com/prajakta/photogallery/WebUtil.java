package com.prajakta.photogallery;

import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class WebUtil {

    public static String downloadImage(int id) {
        String url = "https://picsum.photos/300/300?image="+id;
        Log.e("tag", "Starting: " + url );

        try {

            URL newUrl = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) newUrl.openConnection();

            InputStream in =connection.getInputStream();

            if (connection.getResponseCode() != 200){
                return null;
            }

            File sdCard = Environment.getExternalStorageDirectory();
            File newDir = new File(sdCard.getAbsolutePath()+"/DownloadedImages/");

            if (!newDir.exists()){
                newDir.mkdir();
            }

            File newFile = new File(newDir,id+".jpg");
            FileOutputStream fout = new FileOutputStream(newFile);

            int count;
            byte [] arr = new byte[ 2 * 1024];
            while ( ( count = in.read(arr)) != -1){
                fout.write(arr,0,count);
            }
            in.close();
            Log.e("tag", "Finished: " + url );

            return newFile.getAbsolutePath();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static ArrayList<PhotoInfo> getPhotoInfo() {

        String url = "https://picsum.photos/list";

        try {

            URL urlNew = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) urlNew.openConnection();

            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != 200){
                return null;
            }

            StringBuilder response = new StringBuilder();

            int count;
            byte [] arr = new byte[ 2 * 1024];
            while ( ( count = in.read(arr)) != -1){
                response.append(new String(arr,0,count));
            }
            in.close();

            Log.e("tag", response.toString() );

            ArrayList<PhotoInfo> photoInfos = new ArrayList<>();
            JSONArray jResArray = new JSONArray( response.toString() );

            for( int i = 0; i < jResArray.length(); i++) {
                PhotoInfo photoInfo = new PhotoInfo();

                JSONObject jObj = jResArray.getJSONObject( i );

                photoInfo.setId( jObj.getInt("id"));
                photoInfo.setAuthorName( jObj.getString("author") );

                photoInfos.add( photoInfo );
            }


            return photoInfos;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
