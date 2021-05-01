package com.brian.podcast.player.episodes;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.prof.rssparser.Article;
import com.prof.rssparser.Channel;
import com.prof.rssparser.OnTaskCompleted;
import com.prof.rssparser.Parser;

public class EpisodesViewModel extends ViewModel {

    public EpisodesViewModel() {

    }

    public void loadChannel() {
        Parser parser = new Parser.Builder()
                .build();

        parser.onFinish(new OnTaskCompleted() {

            //what to do when the parsing is done
            @Override
            public void onTaskCompleted(Channel channel) {
                // Use the channel info
                Log.i("Brian", ">> loaded image " + channel.getImage());
                Log.i("Brian", ">> loaded channel " + channel);

//                Article article = channel.getArticles().get(0);
//                Log.i("Brian", ">> episodes 0 article = " + article);
//                Log.i("Brian", ">> episodes 0 article.image = " + article.getImage());

            }

            //what to do in case of error
            @Override
            public void onError(Exception e) {
                // Handle the exception
            }
        });
        parser.execute("https://feeds.soundcloud.com/users/soundcloud:users:322164009/sounds.rss");
    }
}
