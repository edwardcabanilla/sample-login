package com.example.login.ui.contact.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.login.framework.network.api.state.ContactListState
import com.example.login.viewmodels.ContactViewModel

@Composable
fun ContactListScreen(padding: PaddingValues, contactViewModel: ContactViewModel) {
    val contactState = contactViewModel.contactState.collectAsState().value
    var currentPage by remember { mutableIntStateOf(0) }
    val itemsPerPage = 5

    Column(modifier = Modifier.fillMaxSize().padding(padding)) {
        Text(
            text = "Contact List",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        when (contactState) {
            is ContactListState.Success -> {
                val contacts = contactState.data.data
                val totalPages = (contacts.size + itemsPerPage - 1) / itemsPerPage

                val pagedContacts = contacts.drop(currentPage * itemsPerPage).take(itemsPerPage)

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(pagedContacts.size) { index ->
                        val contact = pagedContacts[index]
                        ContactItem(contact)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { if (currentPage > 0) currentPage-- },
                        enabled = currentPage > 0
                    ) {
                        Text("Prev")
                    }

                    Text("Page ${currentPage + 1} of $totalPages")

                    Button(
                        onClick = { if (currentPage < totalPages - 1) currentPage++ },
                        enabled = currentPage < totalPages - 1
                    ) {
                        Text("Next")
                    }
                }
            }
            else -> {}
        }
    }
}