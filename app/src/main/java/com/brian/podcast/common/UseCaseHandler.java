package com.brian.podcast.common;

import io.reactivex.rxjava3.core.Single;

public class UseCaseHandler {

    private final UseCaseScheduler scheduler;

    public UseCaseHandler(UseCaseScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public <I extends UseCase.Input, O extends UseCase.Output> Single<O> execute(
            UseCase<I, O> useCase,
            I inputData
    ) {
        return useCase.execute(inputData)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui());
    }
}
