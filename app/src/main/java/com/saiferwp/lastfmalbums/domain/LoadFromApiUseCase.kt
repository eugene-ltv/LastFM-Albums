package com.saiferwp.lastfmalbums.domain

import androidx.lifecycle.MutableLiveData
import com.saiferwp.lastfmalbums.data.api.ApiClient
import com.saiferwp.lastfmalbums.data.api.request.Request
import com.saiferwp.lastfmalbums.data.api.response.Response
import com.saiferwp.lastfmalbums.util.Result

abstract class LoadFromApiUseCase<P, R, T : Response>(
    private val api: ApiClient
) : UseCase<P, R>() {

    override operator fun invoke(parameters: P, result: MutableLiveData<Result<R>>) {
        result.value = Result.Loading
        super.invoke(parameters, result)
    }

    override suspend fun execute(parameters: P): R {
        val request = prepareRequest(parameters)

        val execute = api.executeAsync(request)
        val response = execute.await()

        return if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                if (body.error == null) {
                    getResponseData(body)
                } else {
                    throw RuntimeException(body.message)
                }
            } else {
                throw RuntimeException(response.message())
            }
        } else {
            throw RuntimeException(response.message())
        }
    }

    abstract fun prepareRequest(parameters: P): Request<T>

    abstract fun getResponseData(body: T): R
}

