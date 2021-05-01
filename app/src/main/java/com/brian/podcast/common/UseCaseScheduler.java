package com.brian.podcast.common;


import io.reactivex.rxjava3.core.Scheduler;

public interface UseCaseScheduler {
    Scheduler ui();
    Scheduler io();
    Scheduler computation();
}
