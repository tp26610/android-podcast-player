package com.brian.podcast.common;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

/**
 * Use example:
 * viewModel.isProgressEnabled.observe(this, new EventObserver<>(hasEnabled -> {
 *         if (hasEnabled) {
 *             // showProgress
 *         } else {
 *             // hideProgress
 *         }
 *     }));
 * **/

public class EventObserver<T> implements Observer<Event<? extends T>> {
    public interface EventUnhandledContent<T> {
        void onEventUnhandledContent(T t);
    }
    private EventUnhandledContent<T> callback;

    public EventObserver(EventUnhandledContent<T> callback) {
        this.callback = callback;
    }

    @Override
    public void onChanged(@Nullable Event<? extends T> event) {
        if (event != null) {
            T content = event.getContentIfNotHandled();
            if (content != null && callback != null) {
                this.callback.onEventUnhandledContent(content);
            }
        }

    }
}
