package com.brian.podcast.player.episodes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.brian.podcast.player.AppInjections;
import com.brian.podcast.player.MainActivity;
import com.brian.podcast.player.R;
import com.brian.podcast.player.episode.EpisodeFragment;

public class EpisodesFragment extends Fragment {

    private MainActivity activity;

    private EpisodesViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = AppInjections
                .getViewModelFactory()
                .getViewModel(this, EpisodesViewModel.class);
        viewModel.loadChannel();

        activity = (MainActivity) requireActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_episodes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = requireView().findViewById(R.id.episodes_recyclerView);

        ChannelAdapter adapter = new ChannelAdapter();
        adapter.setOnItemClickListener(episodeIndex -> {
            Fragment fragment = EpisodeFragment.newInstance(episodeIndex);
            activity.navigateByFragment(fragment);
        });

        recyclerView.setAdapter(adapter);

        viewModel.getObservableChannel().observe(this, adapter::setChannel);
    }
}
