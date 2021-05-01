package com.brian.podcast.player.episodes;

import java.util.ArrayList;
import java.util.List;

public class Channel {
    public final String coverImageUrl;
    public final List<Episode> episodes;

    public Channel(String coverImageUrl, List<Episode> episodes) {
        this.coverImageUrl = coverImageUrl;
        this.episodes = episodes;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }
}
