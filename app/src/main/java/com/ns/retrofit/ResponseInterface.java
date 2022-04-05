package com.ns.retrofit;

import com.ns.model.FlightSeatList.FlightSeatListResponse;

public interface ResponseInterface<T> {
    void onError(Throwable e);
    void onNext(T t);

}
