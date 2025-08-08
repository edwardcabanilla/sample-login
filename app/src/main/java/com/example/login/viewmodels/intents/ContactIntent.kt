package com.example.login.viewmodels.intents

sealed class ContactIntent {
    data class LoadRemoteContacts(
        val page: Int,
        val perPage: Int
    ) : ContactIntent()

    data class LoadLocalContacts(
        val page: Int,
        val perPage: Int
    ) : ContactIntent()

    data object RefreshContacts : ContactIntent()
}