package com.learning.restaurantdemo.utils

import kotlinx.coroutines.flow.*


inline fun <T, R> networkBoundResource(
    crossinline query: () -> Flow<T>,//from db
    crossinline fetch: suspend () -> R,//fetch data from network
    crossinline saveFetchResult: suspend (R) -> Unit,//save data in db
    crossinline shouldFetch: (T) -> Boolean = { true } // if load from network
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))
        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}