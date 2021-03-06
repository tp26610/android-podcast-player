package com.brian.podcast.player.episodes;

import com.prof.rssparser.OnTaskCompleted;
import com.prof.rssparser.Parser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.reactivex.rxjava3.core.Single;

public class ChannelRepository {

    private Channel cachedChannel;

    public Single<Channel> loadChannel() {
        return Single.create(emitter -> {
            Parser parser = new Parser.Builder()
                    .build();

            parser.onFinish(new OnTaskCompleted() {

                //what to do when the parsing is done
                @Override
                public void onTaskCompleted(@NotNull com.prof.rssparser.Channel rssChannel) {
                    cachedChannel = ChannelMapper.mapChannelFromRss(rssChannel);
                    emitter.onSuccess(cachedChannel);
                }

                //what to do in case of error
                @Override
                public void onError(@NotNull Exception e) {
                    emitter.onError(e);
                }
            });
            parser.execute("https://feeds.soundcloud.com/users/soundcloud:users:322164009/sounds.rss");
        });
    }

    private Channel requireChannel() {
        if (cachedChannel == null) {
            throw new IllegalStateException("Channel is null");
        }
        return cachedChannel;
    }

    @Nullable
    public Episode getEpisodeByIndex(int episodeIndex) {
        if (episodeIndex < 0 || episodeIndex >= requireChannel().episodes.size()) {
            return null;
        }
        return requireChannel().episodes.get(episodeIndex);
    }
}
