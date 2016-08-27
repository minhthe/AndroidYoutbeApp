package com.minhthe.dell.androidyoutbeapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    String[] listVideoId ;
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
            listVideoId = new String[items.length()];
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i).getJSONObject("snippet");
                String title = item.getString("title");
                String videoId= item.getJSONObject("resourceId").getString("videoId");

                ItemVideo itemVideo = new ItemVideo(title,videoId);
                itemVideos.add(itemVideo);

                listVideoId[i] = videoId;
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

        ArrayAdapter<String>   arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,listVideoId);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,((TextView) view).getText(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,YoutubeActivity.class);
                intent.putExtra("videoId",((TextView) view).getText());
                context.startActivity(intent);
            }
        });
        super.onPostExecute(o);
    }
}
