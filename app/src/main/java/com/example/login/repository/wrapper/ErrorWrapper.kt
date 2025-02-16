package com.example.login.repository.wrapper

enum class ErrorWrapper(
    var code: Int? = null,
    var message: String,
    var errorBody: String? = null,
    var throwable: Throwable? = null,
) {
    ResponseFailure(code = 500, message = "Response Failure"),
    RemoteNetworkError(message = "Remote NetworkError"),
    LocalNetworkError(message = "Local Network Error"),
    UnknownError(message = "Unknown Error"),
}
