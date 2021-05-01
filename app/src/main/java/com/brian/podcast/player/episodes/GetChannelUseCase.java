package com.brian.podcast.player.episodes;

import com.brian.podcast.common.UseCase;

import io.reactivex.rxjava3.core.Single;

public class GetChannelUseCase extends UseCase<GetChannelUseCase.Input, GetChannelUseCase.Output> {

    private final ChannelRepository channelRepository;

    public GetChannelUseCase(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public Single<Output> execute(Input inputData) {
        return channelRepository.loadChannel()
                .map(Output::new);
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
