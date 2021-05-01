package com.brian.podcast.player.episodes;

public class ChannelMapper {
    public static Channel mapChannelFromRss(com.prof.rssparser.Channel rssChannel) {
        String coverImageUrl = rssChannel.getImage().getUrl();
        return new Channel(coverImageUrl, null);
    }
}
