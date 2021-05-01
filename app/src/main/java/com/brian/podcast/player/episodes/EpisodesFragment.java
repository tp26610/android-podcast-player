package com.brian.podcast.player.episodes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.brian.podcast.player.R;
import com.bumptech.glide.Glide;

public class EpisodesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_episodes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatImageView coverImageView = requireView().findViewById(R.id.episodes_coverImage);
        Glide.with(this).load("https://i1.sndcdn.com/avatars-000326154119-ogb1ma-original.jpg").into(coverImageView);
    }
}
