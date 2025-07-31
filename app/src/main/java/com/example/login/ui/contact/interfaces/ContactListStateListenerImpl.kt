package com.example.login.ui.contact.interfaces

import com.example.login.ui.contact.ContactActivity
import com.example.login.viewmodels.ContactViewModel

class ContactListStateListenerImpl : ContactListStateListener {
    private var activity: ContactActivity? = null
    private var contactViewModel: ContactViewModel? = null


    override fun registerActivity(activity: ContactActivity) {
        this.activity = activity
    }

    override fun registerContactViewModel(contactViewModel: ContactViewModel) {
        this.contactViewModel = contactViewModel
    }

    override fun loadContactList(
        page: Int,
        perPage: Int,
    ) {
        val vm = contactViewModel ?: return

        vm.getContactListAsync(
            page = page,
            perPage = perPage,
        )
    }
}
