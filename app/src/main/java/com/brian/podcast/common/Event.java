package com.brian.podcast.common;

import androidx.annotation.Nullable;

/**
 * Use example:
 * private MutableLiveData<Event<Boolean>> _isProgressEnabled = new MutableLiveData<>();
 * _isProgressEnabled.setValue(new Event<>(true/false));
 * **/

public class Event<T> {
    private boolean hasBeenHandled = false;
    private T content;

    public Event(T content) {
        this.content = content;
    }

    @Nullable
    public T getContentIfNotHandled() {
        if (hasBeenHandled) {
            return null;
        } else {
            hasBeenHandled = true;
            return content;
        }
    }

    public T peekContent() {
        return content;
    }
}
