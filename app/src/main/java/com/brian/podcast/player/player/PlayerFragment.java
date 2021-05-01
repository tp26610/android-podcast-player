package com.brian.podcast.player.player;

import android.os.Bundle;
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
import com.brian.podcast.player.episode.EpisodeViewModel;
import com.bumptech.glide.Glide;

public class PlayerFragment extends Fragment {

    private EpisodeViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = AppInjections.getViewModelFactory().getViewModel(requireActivity(), EpisodeViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_player, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupCoverImage();
        setupEpisodeTitle();
    }

    private void setupEpisodeTitle() {
        AppCompatTextView textView = requireView().findViewById(R.id.player_episodeTitle);
        viewModel.getObservableEpisode().observe(this, episode -> {
            textView.setText(episode.title);
        });
    }

    private void setupCoverImage() {
        AppCompatImageView imageView = requireView().findViewById(R.id.player_cover_image);
        viewModel.getObservableEpisode().observe(this, episode -> {
            Glide.with(imageView)
                    .load(episode.coverImageUrl)
                    .into(imageView);
        });
    }
}
