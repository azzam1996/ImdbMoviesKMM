package com.azzam.imdbmovies.data.remote

import com.azzam.imdbmovies.domain.util.Logger
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

actual object KtorHttpClient {
    actual fun getKtorClient(): HttpClient {
        return HttpClient() {

            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                })
            }


            install(ResponseObserver) {
                onResponse { response ->
                    Logger.log("HTTP status:", "${response.status.value}")
                    Logger.log("HTTP Headers:", response.headers.toString())
                }
            }

            HttpResponseValidator {
                validateResponse { response: HttpResponse ->
                    val statusCode = response.status.value
                }

                handleResponseException { cause ->
                    if (cause is ClientRequestException) {
                        throw  NetworkException(
                            cause.response.status.value,
                            cause.response.receive()
                        )

                    } else {
                        Logger.log("Response Error : ", cause.message ?: "NO Message")
                        throw NetworkException(null, "Something went wrong")
                    }
                }

            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }

        }
    }
}