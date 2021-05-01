package com.brian.podcast.player.episodes;

public class Episode {
    public final String coverImageUrl;
    public final String title;
    public final String publishedDate;
    public final String mediaUrl;
    public final String description;

    public Episode(String coverImageUrl, String title, String publishedDate, String mediaUrl, String description) {
        this.coverImageUrl = coverImageUrl;
        this.title = title;
        this.publishedDate = publishedDate;
        this.mediaUrl = mediaUrl;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "coverImageUrl='" + coverImageUrl + '\'' +
                ", title='" + title + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", mediaUrl='" + mediaUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
