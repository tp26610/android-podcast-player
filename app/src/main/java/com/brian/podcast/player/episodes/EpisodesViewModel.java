package com.brian.podcast.player.episodes;


import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.brian.podcast.common.UseCaseHandler;

public class EpisodesViewModel extends ViewModel {

    private final UseCaseHandler useCaseHandler;
    private final GetChannelUseCase getChannelUseCase;

    public EpisodesViewModel(UseCaseHandler useCaseHandler, GetChannelUseCase getChannelUseCase) {
        this.useCaseHandler = useCaseHandler;
        this.getChannelUseCase = getChannelUseCase;
    }

    public void loadChannel() {
        useCaseHandler.execute(this.getChannelUseCase, new GetChannelUseCase.Input())
                .subscribe(output -> {
                    Channel channel = output.channel;
                    Log.i("Brian", ">> loaded channel="+channel);
                }, error -> {

                });
    }
}
