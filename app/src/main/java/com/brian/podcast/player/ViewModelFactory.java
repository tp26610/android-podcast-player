package com.brian.podcast.player;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.brian.podcast.common.RxScheduler;
import com.brian.podcast.common.UseCaseHandler;
import com.brian.podcast.common.UseCaseScheduler;
import com.brian.podcast.player.episode.EpisodeViewModel;
import com.brian.podcast.player.episode.GetEpisodeUseCase;
import com.brian.podcast.player.episodes.ChannelRepository;
import com.brian.podcast.player.episodes.EpisodesViewModel;
import com.brian.podcast.player.episodes.GetChannelUseCase;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final UseCaseScheduler useCaseScheduler;
    private final UseCaseHandler useCaseHandler;
    private final ChannelRepository channelRepository;

    public ViewModelFactory() {
        useCaseScheduler = new RxScheduler();
        useCaseHandler = new UseCaseHandler(useCaseScheduler);

        channelRepository = new ChannelRepository();
    }

    public <T extends ViewModel> T getViewModel(@NonNull Fragment fragment, @NonNull Class<T> viewModelClass) {
        return new ViewModelProvider(fragment, this).get(viewModelClass);
    }

    public <T extends ViewModel> T getViewModel(@NonNull FragmentActivity activity, @NonNull Class<T> viewModelClass) {
        return new ViewModelProvider(activity, this).get(viewModelClass);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(EpisodesViewModel.class)) {
            return (T) new EpisodesViewModel(useCaseHandler, provideGetChannelUseCase());
        } else if (modelClass.isAssignableFrom(EpisodeViewModel.class)) {
            return (T) new EpisodeViewModel(useCaseHandler, provideGetEpisodeUseCase());
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

    private GetEpisodeUseCase provideGetEpisodeUseCase() {
        return new GetEpisodeUseCase(channelRepository);
    }

    private GetChannelUseCase provideGetChannelUseCase() {
        return new GetChannelUseCase(channelRepository);
    }
}
