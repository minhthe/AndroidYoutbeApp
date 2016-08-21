package com.minhthe.dell.androidyoutbeapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 8/21/2016.
 */
public class MyHelperYoutube extends AsyncTask {


    Context context;
    ListView listView;
    ArrayList<ItemVideo> itemVideos;
    public MyHelperYoutube(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    String playlistId = "PL6B8DaHavsG1O4YagP4gY_bbrFI9zjMGz";
    String browserId = "AIzaSyD-wNumKB8j3V-zx-tsU-DOaf9JGQQ-A54";

    @Override
    protected Object doInBackground(Object[] params) {
        String pathGetJsonPlayList = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId="+playlistId+"&key="+ browserId;
        itemVideos = new ArrayList<>();
        try {
            InputStream is = new URL(pathGetJsonPlayList).openConnection().getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line + "\n");
            }
            is.close();
            JSONArray items= new JSONObject(sb.toString()).getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i).getJSONObject("snippet");
                String title = item.getString("title");
                String videoId= item.getJSONObject("resourceId").getString("videoId");

                ItemVideo itemVideo = new ItemVideo(title,videoId);
                itemVideos.add(itemVideo);


                Log.d("title", title);
                Log.d("videoId", videoId);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("loi", e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {

        ArrayAdapter<ItemVideo>   arrayAdapter = new ArrayAdapter<ItemVideo>(context,android.R.layout.simple_list_item_1,itemVideos);
        listView.setAdapter(arrayAdapter);

        super.onPostExecute(o);
    }
}
