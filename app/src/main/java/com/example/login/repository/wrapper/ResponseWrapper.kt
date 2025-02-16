package com.example.login.repository.wrapper

/**
 * Sealed Class For Wrapping API Call Response W/ Default Coroutine Implementation (No Flow)
 * @param T The Type Of Class That Will Be Used To Serialized Response From API
 */
sealed class ResponseWrapper<out T> {
    /**
     * Invoke When Response From API Was Success
     * @param T The Type Of Class That Will Be Used To Serialized Response From API
     */
    data class ResponseSuccess<out T>(val value: T) : ResponseWrapper<T>()

    /**
     * Invoke When An HTTP Exception Encountered While Performing API
     * @param statusCode HTTP Status Code
     * @param errorMessage Error Message For Local Or UI Use
     * @param errorBody Error Response Body Parsed In Json String Format
     * @param throwable A Throwable HTTP Exception Containing The Said Error
     */
    data class ResponseFailure(
        val statusCode: Int = 0,
        val errorMessage: String = "",
        val errorBody: String = "",
        val throwable: Throwable? = null,
    ) : ResponseWrapper<Nothing>()

    /**
     * Invoke When Network Error Encountered While Performing API, Example Is Unreachable Server Or
     * Request Timeout Or Other Relevant Errors
     * @param errorMessage Error Message For Local Or UI Use
     * @param throwable A Throwable Socket Or Connection Exception Containing The Said Error
     */
    data class RemoteNetworkError(
        val errorMessage: String = "",
        val throwable: Throwable? = null,
    ) : ResponseWrapper<Nothing>()

    /**
     * Invoke When Local Network Error Encountered While Performing API, Example Is Internet Connection Error
     * @param errorMessage Error Message For Local Or UI Use
     * @param throwable A Throwable IOException Exception Containing The Said Error
     */
    data class LocalNetworkError(
        val errorMessage: String = "",
        val throwable: Throwable? = null,
    ) : ResponseWrapper<Nothing>()

    /**
     * Invoke When Unknown Error Is Encountered And Out Of Scope From The Above Handling Classes
     * @param errorMessage Error Message For Local Or UI Use
     * @param throwable A Throwable General Exception Containing The Said Error
     */
    data class UnknownError(
        val errorMessage: String = "",
        val throwable: Throwable? = null,
    ) : ResponseWrapper<Nothing>()
}
