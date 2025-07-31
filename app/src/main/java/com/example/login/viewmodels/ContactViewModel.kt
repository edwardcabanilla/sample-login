package com.example.login.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.login.framework.dagger.modules.repository.IoDispatcher
import com.example.login.framework.network.api.state.ContactListState
import com.example.login.repository.ContactRepository
import com.example.login.repository.wrapper.ResponseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _contactState = MutableStateFlow<ContactListState>(ContactListState.Default)
    val contactState: StateFlow<ContactListState> get() = _contactState

    fun getContactListAsync(
        page: Int,
        perPage: Int,
    ) {
        _contactState.value = ContactListState.Loading
        viewModelScope.launch(dispatcher) {
            try {
                when (val response = contactRepository.getContactListAsync(page, perPage)) {
                    is ResponseWrapper.ResponseSuccess -> {
                        _contactState.value = ContactListState.Success(response.value)
                    }
                    else -> {
                        val error = parseErrorResponse(response)
                        _contactState.value = ContactListState.Failure(
                            throwable = Throwable(error?.message ?: "Unknown error"),
                            message = error?.message ?: "Unknown error"
                        )
                    }
                }
            } catch (e: Exception) {
                _contactState.value = ContactListState.Failure(
                    throwable = e,
                    message = e.localizedMessage ?: "Exception occurred"
                )
            }
        }
    }

    fun getLocalContactList(
        page: Int,
        perPage: Int,
    ) {
        _contactState.value = ContactListState.Loading
        viewModelScope.launch {
            when (val response = contactRepository.getLocalContactList(page, perPage)) {
                is ResponseWrapper.ResponseSuccess -> {
                    _contactState.value = ContactListState.Success(response.value)
                }
                else -> {
                    _contactState.value = ContactListState.Failure(
                        throwable = Throwable("Local fetch failed"),
                        message = "Failed to get local contacts"
                    )
                }
            }
        }
    }
}