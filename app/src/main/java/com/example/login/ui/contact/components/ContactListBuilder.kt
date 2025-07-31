package com.example.login.ui.contact.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.login.ui.contact.interfaces.ContactListStateListener
import com.example.login.viewmodels.ContactViewModel

@Composable
fun ContactListBuilder(contactViewModel: ContactViewModel, listener: ContactListStateListener) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize().padding(16.dp),
    ) { padding ->
        ContactListScreen(padding, contactViewModel)
    }
}

