package com.minhthe.dell.androidyoutbeapp;

/**
 * Created by dell on 8/21/2016.
 */
public class ItemVideo {
    String title;
    String videoId;

    public ItemVideo(String title, String videoId) {
        this.title = title;
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
