package com.brian.podcast.player.episode;

import com.brian.podcast.common.UseCase;
import com.brian.podcast.player.episodes.ChannelRepository;
import com.brian.podcast.player.episodes.Episode;

import io.reactivex.rxjava3.core.Single;

public class GetNearEpisodesUseCase extends UseCase<GetNearEpisodesUseCase.Input, GetNearEpisodesUseCase.Output> {
    private final ChannelRepository channelRepository;

    public GetNearEpisodesUseCase(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public Single<Output> execute(Input inputData) {

        return Single.fromCallable(() -> {
            int episodeIndex = inputData.episodeIndex;
            Episode episode = channelRepository.getEpisodeByIndex(episodeIndex);
            Episode prevEpisode = channelRepository.getEpisodeByIndex(episodeIndex - 1);
            Episode nextEpisode = channelRepository.getEpisodeByIndex(episodeIndex + 1);

            NearEpisodes nearEpisodes = new NearEpisodes(prevEpisode, episode, nextEpisode);

            return new Output(episodeIndex, episode, nearEpisodes);
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
        public NearEpisodes nearEpisodes;

        public Output(int episodeIndex, Episode episode, NearEpisodes nearEpisodes) {
            this.episodeIndex = episodeIndex;
            this.episode = episode;
            this.nearEpisodes = nearEpisodes;
        }
    }
}
