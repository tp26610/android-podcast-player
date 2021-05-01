package com.brian.podcast.player;

import com.brian.podcast.common.RxScheduler;
import com.brian.podcast.common.UseCaseHandler;
import com.brian.podcast.common.UseCaseScheduler;

public class AppInjections {

    private static ViewModelFactory viewModelFactory;

    public static void init() {
        UseCaseScheduler useCaseScheduler = new RxScheduler();
        UseCaseHandler useCaseHandler = new UseCaseHandler(useCaseScheduler);

        viewModelFactory = new ViewModelFactory(useCaseHandler);
    }

    public static ViewModelFactory getViewModelFactory() {
        return viewModelFactory;
    }



}
