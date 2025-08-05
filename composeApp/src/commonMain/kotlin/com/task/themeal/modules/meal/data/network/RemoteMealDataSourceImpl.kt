package com.task.themeal.modules.meal.data.network

import com.task.themeal.core.data.callApi
import com.task.themeal.core.domain.Result
import com.task.themeal.modules.meal.data.dto.MealDetailsListDto
import com.task.themeal.modules.meal.data.dto.MealListDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.Url
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom

class RemoteMealDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteMealDataSource {

    companion object {
        private const val BASE_URL_STRING = "https://www.themealdb.com/api/json/v1/1"
        private val BASE_URL = Url(BASE_URL_STRING)

        private const val FILTER_ENDPOINT = "/filter.php"
        private const val LOOKUP_ENDPOINT = "/lookup.php"
    }

    override suspend fun getMealList(tab: String): Result<MealListDto, Boolean> {
        return callApi {
            httpClient.get {
                url {
                    takeFrom(BASE_URL)
                    encodedPath += FILTER_ENDPOINT
                    parameters.append("c", tab)
                }
            }
        }
    }

    override suspend fun getMealDetails(id: String): Result<MealDetailsListDto, Boolean> {
        return callApi {
            httpClient.get {
                url {
                    takeFrom(BASE_URL)
                    encodedPath += LOOKUP_ENDPOINT
                    parameters.append("i", id)
                }
            }
        }
    }


}

