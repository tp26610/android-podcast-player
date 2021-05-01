package com.brian.podcast.player.episode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.brian.podcast.player.R;

public class EpisodeFragment extends Fragment {

    private static final String ARG_EPISODE_INDEX = "episodeIndex";

    public static Fragment newInstance(int episodeIndex) {
        EpisodeFragment fragment = new EpisodeFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_EPISODE_INDEX, episodeIndex);

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_episode, container, false);
    }
}
