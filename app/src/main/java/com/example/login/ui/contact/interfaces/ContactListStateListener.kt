package com.example.login.ui.contact.interfaces

import com.example.login.ui.contact.ContactActivity
import com.example.login.viewmodels.ContactViewModel

interface ContactListStateListener {

    fun registerActivity(activity: ContactActivity)

    fun registerContactViewModel(contactViewModel: ContactViewModel)

    fun loadContactList(
        page: Int = 0,
        perPage: Int = 0,
    )
}
