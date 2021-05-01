package com.brian.podcast.player.episode;

import com.brian.podcast.player.episodes.Episode;

public class NearEpisodes {
    public final Episode prevEpisode;
    public final Episode currentEpisode;
    public final Episode nextEpisode;

    public NearEpisodes(Episode prevEpisode, Episode currentEpisode, Episode nextEpisode) {
        this.prevEpisode = prevEpisode;
        this.currentEpisode = currentEpisode;
        this.nextEpisode = nextEpisode;
    }
}
