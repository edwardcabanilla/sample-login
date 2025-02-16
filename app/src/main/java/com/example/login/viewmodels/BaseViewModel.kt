package com.example.login.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.repository.wrapper.ErrorWrapper
import com.example.login.repository.wrapper.ResponseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import javax.inject.Inject

/**
 * BaseViewModel Class For All ViewModels, Not Necessarily To Extend If All Of Its Functions Will
 * Not Be Used. All Common Functionalities Of ViewModels Will Be Used Here
 */
@HiltViewModel
open class BaseViewModel
@Inject
constructor() : ViewModel() {
    /**
     * Function To Parse Error From SafeApiCall Response
     * @return ErrorWrapper Containing The Status Code (If HttpException) And Error Message
     * @param genericClass The Other Classes Aside From ResponseWrapper.ResponseSuccess() Will Be
     * Handled Here
     * @see ErrorWrapper
     * @see ResponseWrapper
     */
    inline fun <reified T> parseErrorResponse(genericClass: T): ErrorWrapper? {
        return genericClass?.let { generic ->
            var error = ErrorWrapper.UnknownError
            when (generic) {
                is ResponseWrapper.ResponseFailure -> {
                    error = ErrorWrapper.ResponseFailure
                    error.code = generic.statusCode
                    error.message = generic.errorMessage
                    error.errorBody = generic.errorBody
                    error.throwable = generic.throwable
                }
                is ResponseWrapper.RemoteNetworkError -> {
                    error = ErrorWrapper.RemoteNetworkError
                    error.message = generic.errorMessage
                    error.throwable = generic.throwable
                }
                is ResponseWrapper.LocalNetworkError -> {
                    error = ErrorWrapper.LocalNetworkError
                    error.message = generic.errorMessage
                    error.throwable = generic.throwable
                }
                is ResponseWrapper.UnknownError -> {
                    error = ErrorWrapper.UnknownError
                    error.message = generic.errorMessage
                    error.throwable = generic.throwable
                }
            }
            error
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}