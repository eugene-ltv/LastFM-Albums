package com.saiferwp.lastfmalbums.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.saiferwp.lastfmalbums.util.Result
import kotlinx.coroutines.*

/**
 * Executes business logic synchronously or asynchronously using a [CoroutineScope].
 */
abstract class UseCase<in P, R> {
    private val scope = CoroutineScope(Dispatchers.IO + Job())

    /** Executes the use case asynchronously and places the [Result] in a MutableLiveData
     *
     * @param parameters the input parameters to run the use case with
     * @param result the MutableLiveData where the result is posted to
     *
     */
    open operator fun invoke(parameters: P, result: MutableLiveData<Result<R>>) {
        try {
            scope.launch {
                try {
                    execute(parameters).let { useCaseResult ->
                        result.postValue(Result.Success(useCaseResult))
                    }
                } catch (e: Exception) {
                    result.postValue(Result.Error(e))
                }
            }
        } catch (e: Exception) {
            result.postValue(Result.Error(e))
        }
    }

    /** Executes the use case asynchronously and returns a [Result] in a new LiveData object.
     *
     * @return an observable [LiveData] with a [Result].
     *
     * @param parameters the input parameters to run the use case with
     */
    operator fun invoke(parameters: P): LiveData<Result<R>> {
        val liveCallback: MutableLiveData<Result<R>> = MutableLiveData()
        this(parameters, liveCallback)
        return liveCallback
    }

    /** Executes the use case synchronously  */
    fun executeNow(parameters: P): Result<R> {
        return try {
            runBlocking {
                Result.Success(execute(parameters))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}

operator fun <R> UseCase<Unit, R>.invoke(): LiveData<Result<R>> = this(Unit)
operator fun <R> UseCase<Unit, R>.invoke(result: MutableLiveData<Result<R>>) = this(Unit, result)
