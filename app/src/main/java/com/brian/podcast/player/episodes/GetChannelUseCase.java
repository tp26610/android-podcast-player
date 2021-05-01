package com.brian.podcast.player.episodes;

import android.util.Log;

import com.brian.podcast.common.UseCase;
import com.prof.rssparser.OnTaskCompleted;
import com.prof.rssparser.Parser;

import io.reactivex.rxjava3.core.Single;

public class GetChannelUseCase extends UseCase<GetChannelUseCase.Input, GetChannelUseCase.Output> {

    @Override
    public Single<Output> execute(Input inputData) {
        return Single.create(emitter -> {
            Parser parser = new Parser.Builder()
                    .build();

            parser.onFinish(new OnTaskCompleted() {

                //what to do when the parsing is done
                @Override
                public void onTaskCompleted(com.prof.rssparser.Channel rssChannel) {
                    // Use the channel info
//                    Log.i("Brian", ">> loaded image " + rssChannel.getImage());
//                    Log.i("Brian", ">> loaded channel " + rssChannel);

                    //                Article article = channel.getArticles().get(0);
                    //                Log.i("Brian", ">> episodes 0 article = " + article);
                    //                Log.i("Brian", ">> episodes 0 article.image = " + article.getImage());

                    Channel channel = ChannelMapper.mapChannelFromRss(rssChannel);
                    emitter.onSuccess(new Output(channel));
                }

                //what to do in case of error
                @Override
                public void onError(Exception e) {
                    emitter.onError(e);
                }
            });
            parser.execute("https://feeds.soundcloud.com/users/soundcloud:users:322164009/sounds.rss");
        });
    }

    public static class Input implements UseCase.Input {

    }

    public static class Output implements UseCase.Output {
        public final Channel channel;

        public Output(Channel channel) {
            this.channel = channel;
        }
    }
}
