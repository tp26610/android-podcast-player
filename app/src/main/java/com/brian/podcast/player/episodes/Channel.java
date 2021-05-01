package com.brian.podcast.player.episodes;

import java.util.List;

public class Channel {
    public final String coverImageUrl;
    public final List<Episode> episodes;

    public Channel(String coverImageUrl, List<Episode> episodes) {
        this.coverImageUrl = coverImageUrl;
        this.episodes = episodes;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "coverImageUrl='" + coverImageUrl + '\'' +
//                ", episodes=" + episodes +
                '}';
    }
}
