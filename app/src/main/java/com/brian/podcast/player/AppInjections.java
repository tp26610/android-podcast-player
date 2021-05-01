package com.brian.podcast.player;

import com.brian.podcast.common.RxScheduler;
import com.brian.podcast.common.UseCaseHandler;
import com.brian.podcast.common.UseCaseScheduler;

public class AppInjections {

    private static ViewModelFactory viewModelFactory;

    public static void init() {
        viewModelFactory = new ViewModelFactory();
    }

    public static ViewModelFactory getViewModelFactory() {
        return viewModelFactory;
    }



}
