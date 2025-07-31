package com.example.login.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.login.framework.network.api.state.ContactListState
import com.example.login.repository.ContactRepository
import com.example.login.repository.wrapper.ResponseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel
    @Inject
    constructor(
        private val contactRepository: ContactRepository
    ) : BaseViewModel() {

    private val _contactState: MutableStateFlow<ContactListState> =
        MutableStateFlow(ContactListState.Default)
    val contactState: StateFlow<ContactListState> get() = _contactState

    fun getContactListAsync(
        page: Int,
        perPage: Int,
    ) {
        _contactState.value = ContactListState.Loading
        viewModelScope.launch {
            when (
                val response = contactRepository.getContactList(
                    page = page, perPage = perPage
                )
            ) {
                is ResponseWrapper.ResponseSuccess -> {
                    _contactState.value =
                        ContactListState.Success(
                            response.value,
                        )
                } else -> {
                    val error = parseErrorResponse(response)
                    error?.let { wrapper ->
                        _contactState.value =
                            ContactListState.Failure(
                                throwable = Throwable(""),
                                message = wrapper.message,
                            )
                    }
                }
            }
        }
    }

    fun getLocalContactList(
        page: Int,
        perPage: Int,
    ) {
        _contactState.value = ContactListState.Loading
        viewModelScope.launch {
            when (val response = contactRepository.getLocalContactList(
                page = page, perPage = perPage
            )) {
                is ResponseWrapper.ResponseSuccess -> {
                    _contactState.value = ContactListState.Success(
                        data = response.value
                    )
                }
                else -> {
                    _contactState.value = ContactListState.Failure(null, "")
                }
            }

        }
    }
}
