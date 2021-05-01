package com.brian.podcast.player.episode;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.brian.podcast.player.AppInjections;
import com.brian.podcast.player.R;
import com.bumptech.glide.Glide;

public class EpisodeFragment extends Fragment {

    private static final String ARG_EPISODE_INDEX = "episodeIndex";

    private EpisodeViewModel viewModel;

    public static Fragment newInstance(int episodeIndex) {
        EpisodeFragment fragment = new EpisodeFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_EPISODE_INDEX, episodeIndex);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = AppInjections.getViewModelFactory().getViewModel(this, EpisodeViewModel.class);

        int episodeIndex = getArguments().getInt(ARG_EPISODE_INDEX);
        viewModel.loadEpisode(episodeIndex);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_episode, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupCoverImage();
        setupEpisodeTitle();
        setupEpisodeDescription();
    }

    private void setupEpisodeDescription() {
        AppCompatTextView textView = requireView().findViewById(R.id.episode_episodeDescription);
        textView.setMovementMethod(new ScrollingMovementMethod());

        viewModel.getObservableEpisode().observe(this, episode -> {
            textView.setText(episode.description);
        });

    }

    private void setupEpisodeTitle() {
        AppCompatTextView textView = requireView().findViewById(R.id.episode_episodeTitle);
        viewModel.getObservableEpisode().observe(this, episode -> {
            textView.setText(episode.title);
        });
    }

    private void setupCoverImage() {
        AppCompatImageView imageView = requireView().findViewById(R.id.episode_cover_image);
        viewModel.getObservableEpisode().observe(this, episode -> {
            Glide.with(imageView)
                    .load(episode.coverImageUrl)
                    .into(imageView);
        });
    }


}
