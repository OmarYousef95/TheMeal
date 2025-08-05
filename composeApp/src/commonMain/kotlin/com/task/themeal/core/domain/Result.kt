package com.task.themeal.core.domain

sealed interface Result<out D, out E> {
    data class Success<out D>(val data: D): Result<D, Nothing>
    data class Error(val error: Boolean):
        Result<Nothing, Boolean>
}

inline fun <T, R> Result<T, Boolean>.map(map: (T) -> R): Result<R, Boolean> {
    return when(this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}

inline fun <T> Result<T, Boolean>.onSuccess(action: (T) -> Unit): Result<T, Boolean> {
    return when(this) {
        is Result.Error -> this
        is Result.Success -> {
            action(data)
            this
        }
    }
}
inline fun <T> Result<T, Boolean>.onError(action: (Boolean) -> Unit): Result<T, Boolean> {
    return when(this) {
        is Result.Error -> {
            action(error)
            this
        }
        is Result.Success -> this
    }
}
