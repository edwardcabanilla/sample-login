package com.example.login.repository.wrapper

/**
 * Sealed Class For Network Bound Resource
 * Used To Wrap Response From Network/Local Database W/ Kotlin Flow Implementation
 * @param data The Data Came From Each Class Under ResourceWrapper
 * @param error Exception Error Thrown While Performing An Operation
 * @param statusCode The Status Code That Will Be Coming From The Http Response
 * @param errorMessage Error Message In The Form of String
 */
sealed class ResourceWrapper<T> constructor(
    val data: T? = null,
    val error: Throwable? = null,
    val statusCode: Int? = null,
    val errorMessage: String? = null,
) {
    /**
     * Invoke When Response From Network/Local Database Was Success
     * @param data The Data Came From Local Database
     */
    class Success<T>(data: T) : ResourceWrapper<T>(data)

    /**
     * Invoke When Operation Of Either Performing API or Local Calls Is Still Ongoing
     * @param data The Data Came From Local Database
     */
    class Loading<T>(data: T? = null) : ResourceWrapper<T>(data)

    /**
     * Invoke When Exception Was Thrown Or Encountered While Performing An Operation
     * @param throwable Exception Error Thrown While Performing An Operation
     * @param data The Data Came From Local Database
     * @param statusCode The Status Code That Will Be Coming From The Http Response
     * @param error Error Message In The Form of String
     */
    class Error<T>(
        throwable: Throwable,
        data: T? = null,
        statusCode: Int? = null,
        error: String? = null,
    ) : ResourceWrapper<T>(data, throwable, statusCode, error)
}
