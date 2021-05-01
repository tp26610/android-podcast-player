package com.brian.podcast.common;


import io.reactivex.rxjava3.core.Single;

public abstract class UseCase<I extends UseCase.Input, O extends UseCase.Output> {

    public interface Input {
    }

    public interface Output {
    }

    public abstract Single<O> execute(I inputData);
}
