package com.brian.podcast.player.episodes;

public class Episode {
    public final String coverImageUrl;
    public final String title;
    public final String publishedDate;
    public final String mediaUrl;

    public Episode(String coverImageUrl, String title, String publishedDate, String mediaUrl) {
        this.coverImageUrl = coverImageUrl;
        this.title = title;
        this.publishedDate = publishedDate;
        this.mediaUrl = mediaUrl;
    }
}
