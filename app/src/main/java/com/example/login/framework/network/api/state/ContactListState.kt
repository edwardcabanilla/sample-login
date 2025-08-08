package com.example.login.framework.network.api.state

import com.example.login.framework.network.api.response.ContactResponse

sealed class ContactListState {
    data class Success(
        val data: ContactResponse,
    ) : ContactListState()

    data class Failure constructor(val throwable: Throwable?, val message: String) :
        ContactListState()

    data object Loading : ContactListState()

    data object Default : ContactListState()
}

