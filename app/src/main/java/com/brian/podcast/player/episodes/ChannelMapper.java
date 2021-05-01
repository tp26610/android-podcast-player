package com.brian.podcast.player.episodes;

import com.prof.rssparser.Article;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChannelMapper {
    private static final DateFormat rssDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
    private static final DateFormat outDateFormat = new SimpleDateFormat("yyyy/MM/d", Locale.TAIWAN);

    public static Channel mapChannelFromRss(com.prof.rssparser.Channel rssChannel) {
        String coverImageUrl = rssChannel.getImage().getUrl();

        List<Episode> episodes = new ArrayList<>();
        for (Article article : rssChannel.getArticles()) {
            Episode episode = mapEpisode(article);
            episodes.add(episode);
        }

        return new Channel(coverImageUrl, episodes);
    }

    public static Episode mapEpisode(Article article) {
        String coverImageUrl = article.getImage();
        String title = article.getTitle();

        String publishedDate = null;
        try {
            publishedDate = outDateFormat.format(rssDateFormat.parse(article.getPubDate()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String mediaUrl = article.getAudio();
        return new Episode(coverImageUrl, title, publishedDate, mediaUrl);
    }
}
