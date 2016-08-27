package com.minhthe.dell.androidyoutbeapp;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by dell on 8/27/2016.
 */
public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
//        AIzaSyDn8LmHdzg7Hfd2iitCnkpykUZzXsvaM_g
//        AIzaSyAslGOXrO-3-qmWJS2k5D-TtXCI5L45zaE
public static  final  String API_KEY = "AIzaSyDn8LmHdzg7Hfd2iitCnkpykUZzXsvaM_g";
public static  String VIDEO_ID = "UbPiCgCkHTE";
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_activity);

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
                VIDEO_ID   = extras.getString("videoId");
        }

        YouTubePlayerView youTubePlayerView= (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY, this);
        }

@Override
public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        /** Start buffering **/
        if (!b) {
        youTubePlayer.cueVideo(VIDEO_ID);
        }
        }

private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
@Override
public void onBuffering(boolean arg0) {
        }
@Override
public void onPaused() {
        }
@Override
public void onPlaying() {
        }
@Override
public void onSeekTo(int arg0) {
        }
@Override
public void onStopped() {
        }
        };

@Override
public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this,"Failured to Initialize", Toast.LENGTH_SHORT).show();
        }

private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
@Override
public void onAdStarted() {
        }
@Override
public void onError(YouTubePlayer.ErrorReason arg0) {
        }
@Override
public void onLoaded(String arg0) {
        }
@Override
public void onLoading() {
        }
@Override
public void onVideoEnded() {
        }
@Override
public void onVideoStarted() {
        }
        };

        }
