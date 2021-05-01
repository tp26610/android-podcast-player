package com.brian.podcast.player.episodes;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.brian.podcast.common.Event;
import com.brian.podcast.common.UseCaseHandler;

public class EpisodesViewModel extends ViewModel {

    private final UseCaseHandler useCaseHandler;
    private final GetChannelUseCase getChannelUseCase;

    private final MutableLiveData<Channel> observableChannel = new MutableLiveData<>();
    private final MutableLiveData<Event<Throwable>> observableError = new MutableLiveData<>();

    public EpisodesViewModel(UseCaseHandler useCaseHandler, GetChannelUseCase getChannelUseCase) {
        this.useCaseHandler = useCaseHandler;
        this.getChannelUseCase = getChannelUseCase;
    }

    public void loadChannel() {
        useCaseHandler.execute(this.getChannelUseCase, new GetChannelUseCase.Input())
                .subscribe(output -> {
                    Channel channel = output.channel;
                    observableChannel.setValue(channel);
                }, error -> {
                    observableError.setValue(new Event<>(error));
                });
    }

    public LiveData<Channel> getObservableChannel() {
        return observableChannel;
    }
}
