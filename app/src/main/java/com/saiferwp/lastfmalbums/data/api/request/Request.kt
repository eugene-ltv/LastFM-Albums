package com.saiferwp.lastfmalbums.data.api.request

import com.saiferwp.lastfmalbums.data.api.Api
import kotlinx.coroutines.Deferred
import retrofit2.Response

abstract class Request<T> {
    abstract fun executeAsync(api: Api): Deferred<Response<T>>
}