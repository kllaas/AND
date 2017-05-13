package com.klimchuk.and.data;

/**
 * Created by alexey on 13.05.17.
 */

public interface LoadingCallback<T> {

    void onPlaceLoaded(T place);

    void onLoadingFailed();

}