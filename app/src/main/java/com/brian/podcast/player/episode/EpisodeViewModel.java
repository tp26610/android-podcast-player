package com.brian.podcast.player.episode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.brian.podcast.common.UseCaseHandler;
import com.brian.podcast.player.episodes.Episode;

public class EpisodeViewModel extends ViewModel {

    private final UseCaseHandler useCaseHandler;
    private final GetNearEpisodesUseCase getNearEpisodesUseCase;

    private MutableLiveData<Episode> observableEpisode = new MutableLiveData<>();
    private MutableLiveData<NearEpisodes> observableNearEpisodes = new MutableLiveData<>();

    private int currentEpisodeIndex = 0;

    public EpisodeViewModel(UseCaseHandler useCaseHandler, GetNearEpisodesUseCase getNearEpisodesUseCase) {
        this.useCaseHandler = useCaseHandler;
        this.getNearEpisodesUseCase = getNearEpisodesUseCase;
    }

    public void loadEpisode(int episodeIndex) {
        currentEpisodeIndex = episodeIndex;
        useCaseHandler.execute(getNearEpisodesUseCase, new GetNearEpisodesUseCase.Input(episodeIndex))
                .subscribe(
                        output -> {
                            observableEpisode.setValue(output.episode);
                            observableNearEpisodes.setValue(output.nearEpisodes);
                        },
                        error -> {
                        }
                );
    }

    public LiveData<Episode> getObservableEpisode() {
        return observableEpisode;
    }

    public LiveData<NearEpisodes> getObservableNearEpisodes() {
        return observableNearEpisodes;
    }

    public int getCurrentEpisodeIndex() {
        return currentEpisodeIndex;
    }
}
