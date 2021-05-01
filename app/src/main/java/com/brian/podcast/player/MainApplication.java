package com.brian.podcast.player;

import android.app.Application;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AppInjections.init();
    }
}
