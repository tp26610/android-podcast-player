package com.brian.podcast.player.episode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.brian.podcast.common.UseCaseHandler;
import com.brian.podcast.player.episodes.Episode;

public class EpisodeViewModel extends ViewModel {

    private final UseCaseHandler useCaseHandler;
    private final GetEpisodeUseCase getEpisodeUseCase;

    private MutableLiveData<Episode> observableEpisode = new MutableLiveData<>();

    public EpisodeViewModel(UseCaseHandler useCaseHandler, GetEpisodeUseCase getEpisodeUseCase) {
        this.useCaseHandler = useCaseHandler;
        this.getEpisodeUseCase = getEpisodeUseCase;
    }

    public void loadEpisode(int episodeIndex) {
        useCaseHandler.execute(getEpisodeUseCase, new GetEpisodeUseCase.Input(episodeIndex))
                .subscribe(
                        output -> observableEpisode.setValue(output.episode),
                        error -> {}
                );
    }

    public LiveData<Episode> getObservableEpisode() {
        return observableEpisode;
    }
}
