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
import com.brian.podcast.player.episode.NearEpisodes;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerControlView;

public class PlayerFragment extends Fragment {

    private EpisodeViewModel viewModel;
    private SimpleExoPlayer player;

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
        setupPlayer();
    }

    @Override
    public void onPause() {
        super.onPause();

        player.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        player.release();
    }

    private void setupPlayer() {
        player = new SimpleExoPlayer.Builder(requireContext()).build();

        PlayerControlView playerControlView = requireView().findViewById(R.id.player_control_view);
        playerControlView.setPlayer(player);

        player.addListener(new Player.EventListener() {
            @Override
            public void onPlaybackStateChanged(int state) {
                //                Log.i("Brian", ">> onPlaybackStateChanged=" + state);
                switch (state) {
                    case Player.STATE_READY:
                        player.play();

                        NearEpisodes nearEpisodes = viewModel.getObservableNearEpisodes().getValue();
                        if (nearEpisodes.nextEpisode != null) {
                            player.addMediaItem(0,
                                    new MediaItem.Builder()
                                            .setUri(nearEpisodes.nextEpisode.mediaUrl)
                                            .setMediaId("" + (viewModel.getCurrentEpisodeIndex() + 1))
                                            .build()
                            );
                        }
                        if (nearEpisodes.prevEpisode != null) {
                            player.addMediaItem(2,
                                    new MediaItem.Builder()
                                            .setUri(nearEpisodes.prevEpisode.mediaUrl)
                                            .setMediaId("" + (viewModel.getCurrentEpisodeIndex() - 1))
                                            .build()
                            );
                        }
                        break;
                }
            }

            @Override
            public void onMediaItemTransition(@Nullable MediaItem mediaItem, int reason) {
                if (mediaItem == null) return;

                int mediaEpisodeIndex = Integer.parseInt(mediaItem.mediaId);
                if (mediaEpisodeIndex != viewModel.getCurrentEpisodeIndex()) {
                    viewModel.loadEpisode(Integer.parseInt(mediaItem.mediaId));
                }
            }
        });

        viewModel.getObservableNearEpisodes().observe(this, nearEpisodes -> {
            // Set the media item to be played.
            player.clearMediaItems();
            player.setMediaItem(
                    new MediaItem.Builder()
                            .setUri(nearEpisodes.currentEpisode.mediaUrl)
                            .setMediaId("" + viewModel.getCurrentEpisodeIndex())
                            .build()
            );

            // Prepare the player.
            player.prepare();
        });
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
