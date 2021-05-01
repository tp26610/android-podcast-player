package com.brian.podcast.player.episode;

import com.brian.podcast.common.UseCase;
import com.brian.podcast.player.episodes.ChannelRepository;
import com.brian.podcast.player.episodes.Episode;

import io.reactivex.rxjava3.core.Single;

public class GetEpisodeUseCase extends UseCase<GetEpisodeUseCase.Input, GetEpisodeUseCase.Output> {
    private final ChannelRepository channelRepository;

    public GetEpisodeUseCase(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public Single<Output> execute(Input inputData) {

        return Single.fromCallable(() -> {
            int episodeIndex = inputData.episodeIndex;
            Episode episode = channelRepository.getEpisodeByIndex(inputData.episodeIndex);
            return new Output(episodeIndex, episode);
        });
    }

    public static class Input implements UseCase.Input {
        public final int episodeIndex;

        public Input(int episodeIndex) {
            this.episodeIndex = episodeIndex;
        }
    }

    public static class Output implements UseCase.Output {
        public final Episode episode;
        public final int episodeIndex;

        public Output(int episodeIndex, Episode episode) {
            this.episodeIndex = episodeIndex;
            this.episode = episode;
        }

        public boolean hasEpisode() {
            return episode != null;
        }
    }
}
