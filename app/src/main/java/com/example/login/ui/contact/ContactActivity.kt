package com.example.login.ui.contact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.login.framework.network.api.state.ContactListState
import com.example.login.ui.contact.components.ContactListBuilder
import com.example.login.ui.contact.interfaces.ContactListStateListener
import com.example.login.ui.contact.interfaces.ContactListStateListenerImpl
import com.example.login.ui.theme.LoginTheme
import com.example.login.viewmodels.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactActivity : ComponentActivity(), ContactListStateListener by ContactListStateListenerImpl() {

    private val contactViewModel by viewModels<ContactViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginTheme {
                ContactListBuilder(
                    contactViewModel = contactViewModel,
                    listener = this
                )
            }
        }
        registerActivity(this)
        registerContactViewModel(contactViewModel)
        initializeApi()
        lifecycleScope.launch { collectWeatherState() }
    }

    private fun initializeApi() {
        loadContactList()
    }

    private suspend fun collectWeatherState() {
        contactViewModel.contactState.collectLatest { state ->
            when (state) {
                is ContactListState.Success -> {
                    contactViewModel.getLocalContactList(
                        page = state.data.page,
                        perPage = state.data.per_page
                    )
                }
                else -> { }
            }
        }
    }
}