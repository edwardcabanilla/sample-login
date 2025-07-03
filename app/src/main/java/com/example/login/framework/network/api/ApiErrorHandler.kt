package com.example.login.framework.network.api

import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException


enum class HttpErrors(val code: Int,val error: String) {
    HTTP_400_ERROR(code = 400,error = "Error 400: Bad Request"),
    HTTP_401_ERROR(code = 401,error = "Error 401: Unauthorized"),
    HTTP_403_ERROR(code = 403,error = "Error 403: Access Forbidden"),
    HTTP_404_ERROR(code = 404,error = "Error 404: Resource Not Found"),
    HTTP_422_ERROR(code = 422,error = "Error 422: Unprocessable Entity"),
    HTTP_500_ERROR(code = 500,error = "Error 500: Internal Server Error"),
    HTTP_502_ERROR(code = 502,error = "Error 502: Bad Gateway"),
    HTTP_503_ERROR(code = 503,error = "Error 503: Service Unavailable"),
    HTTP_GEN_ERROR(code = 505,error = "Unknown error encountered")
}

enum class NetworkErrors(val message: String) {
    SOCKET_TIMEOUT_ERROR(message = "Request Time Out"),
    CONNECTION_ERROR(message = "An error occurred while attempting to connect to the server"),
    IO_EXCEPTION_ERROR(message = "No Internet Connection"),
    UNKNOWN_ERROR(message = "Unknown error encountered");
}

data class ApiErrorWrapper(
    val statusCode: Int = -1,
    val errorMessage: String = "",
    val errorBody: String = "",
    val errorThrowable: Throwable
)

open class ApiErrorHandler {

    private lateinit var apiErrorWrapper: ApiErrorWrapper

    fun checkError(error: Throwable): ApiErrorHandler {
        when (error) {
            is SocketTimeoutException -> {
                apiErrorWrapper = ApiErrorWrapper(
                    errorMessage = NetworkErrors.SOCKET_TIMEOUT_ERROR.message, errorThrowable = error
                )
            }
            is ConnectException -> {
                apiErrorWrapper = ApiErrorWrapper(
                    errorMessage = NetworkErrors.CONNECTION_ERROR.message, errorThrowable = error
                )
            }
            is IOException -> {
                apiErrorWrapper = ApiErrorWrapper(
                    errorMessage = NetworkErrors.IO_EXCEPTION_ERROR.message, errorThrowable = error
                )
            }
            is HttpException -> {
                when (error.code()) {
                    HttpErrors.HTTP_400_ERROR.code -> {
                        apiErrorWrapper = ApiErrorWrapper(
                            statusCode = error.code(),
                            errorMessage = HttpErrors.HTTP_400_ERROR.error,
                            errorThrowable = error
                        )
                    }
                    HttpErrors.HTTP_401_ERROR.code -> {
                        apiErrorWrapper = ApiErrorWrapper(
                            statusCode = error.code(),
                            errorMessage = HttpErrors.HTTP_401_ERROR.error,
                            errorThrowable = error
                        )
                    }
                    HttpErrors.HTTP_403_ERROR.code -> {
                        apiErrorWrapper = ApiErrorWrapper(
                            statusCode = error.code(),
                            errorMessage = HttpErrors.HTTP_403_ERROR.error,
                            errorThrowable = error
                        )
                    }
                    HttpErrors.HTTP_404_ERROR.code -> {
                        apiErrorWrapper = ApiErrorWrapper(
                            statusCode = error.code(),
                            errorMessage = HttpErrors.HTTP_404_ERROR.error,
                            errorThrowable = error
                        )
                    }
                    HttpErrors.HTTP_422_ERROR.code -> {
                        apiErrorWrapper = ApiErrorWrapper(
                            statusCode = error.code(),
                            errorMessage = HttpErrors.HTTP_422_ERROR.error,
                            errorThrowable = error
                        )
                    }
                    HttpErrors.HTTP_500_ERROR.code -> {
                        apiErrorWrapper = ApiErrorWrapper(
                            statusCode = error.code(),
                            errorMessage = HttpErrors.HTTP_500_ERROR.error,
                            errorThrowable = error
                        )
                    }
                    HttpErrors.HTTP_502_ERROR.code -> {
                        apiErrorWrapper = ApiErrorWrapper(
                            statusCode = error.code(),
                            errorMessage = HttpErrors.HTTP_502_ERROR.error,
                            errorThrowable = error
                        )
                    }
                    HttpErrors.HTTP_503_ERROR.code -> {
                        apiErrorWrapper = ApiErrorWrapper(
                            statusCode = error.code(),
                            errorMessage = HttpErrors.HTTP_503_ERROR.error,
                            errorThrowable = error
                        )
                    }
                    else -> {
                        apiErrorWrapper = ApiErrorWrapper(
                            statusCode = error.code(),
                            errorMessage = HttpErrors.HTTP_GEN_ERROR.error,
                            errorThrowable = error
                        )
                    }
                }
            }
            else -> {
                apiErrorWrapper = ApiErrorWrapper(
                    errorMessage = NetworkErrors.UNKNOWN_ERROR.message, errorThrowable = error
                )
            }
        }
        return this
    }

    fun getApiErrorWrapper(): ApiErrorWrapper = this.apiErrorWrapper
}
