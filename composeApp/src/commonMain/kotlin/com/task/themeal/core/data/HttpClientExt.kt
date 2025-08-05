package com.task.themeal.core.data

import com.task.themeal.core.domain.Result
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> callApi(
    execute: () -> HttpResponse
): Result<T, Boolean> {
    val response = try {
        execute()
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(true)
    }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, Boolean> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch (e: Exception) {
                Result.Error(true)
            }
        }

        else -> Result.Error(true)
    }
}