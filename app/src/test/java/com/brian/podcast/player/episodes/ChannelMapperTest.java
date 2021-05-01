package com.brian.podcast.player.episodes;

import com.prof.rssparser.Article;
import com.prof.rssparser.Image;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ChannelMapperTest {

    public com.prof.rssparser.Channel createTestRssChannel() {
        Image channelImage = new Image(
                "科技島讀 Podcast",
                "https://i1.sndcdn.com/avatars-000326154119-ogb1ma-original.jpg",
                "https://daodu.tech",
                null);

        Article article = new Article();
        article.setPubDate("Sun, 25 Apr 2021 22:00:11 +0000");
        article.setImage("https://i1.sndcdn.com/artworks-Z7zJRFuDjv63KCHv-5W8whA-t3000x3000.jpg");
        article.setTitle("Ep.141 色彩繽紛的桌上型電腦 — 蘋...");
        article.setDescription("蘋果春季發表會沒有...");
        article.setAudio("https://feeds.soundcloud.com/stream/1036317475-daodutech-podcast-colorful-desktop-computer.mp3");

        List<Article> articles = Collections.singletonList(article);
        return new com.prof.rssparser.Channel(
                "科技島讀",
                "https://daodu.tech",
                "科技島讀是會員制媒體，專注於分析國際事件...",
                channelImage,
                "Sun, 25 Apr 2021 10:29:30 +0000",
                null,
                articles);
    }

    @Test
    public void it_maps_channel_coverImageUrl() {
        com.prof.rssparser.Channel rssChannel = createTestRssChannel();
        Channel channel = ChannelMapper.mapChannelFromRss(rssChannel);

        assertThat(channel.coverImageUrl, is("https://i1.sndcdn.com/avatars-000326154119-ogb1ma-original.jpg"));
    }

    @Test
    public void it_maps_episodes() {
        com.prof.rssparser.Channel rssChannel = createTestRssChannel();
        Channel channel = ChannelMapper.mapChannelFromRss(rssChannel);

        assertThat(channel.episodes.size(), is(1));

        Episode episode = channel.episodes.get(0);
        assertThat(episode.coverImageUrl, is("https://i1.sndcdn.com/artworks-Z7zJRFuDjv63KCHv-5W8whA-t3000x3000.jpg"));
        assertThat(episode.title, is("Ep.141 色彩繽紛的桌上型電腦 — 蘋..."));
        assertThat(episode.publishedDate, is("2021/04/26"));
        assertThat(episode.description, is("蘋果春季發表會沒有..."));
    }
}
