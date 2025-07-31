package com.example.login.repository

import androidx.room.withTransaction
import com.example.login.framework.database.entities.asContactEntity
import com.example.login.framework.database.entities.asContactResponse
import com.example.login.framework.database.room.SampleDatabase
import com.example.login.framework.network.api.ApiService
import com.example.login.framework.network.api.response.ContactResponse
import javax.inject.Inject

class ContactRepository @Inject constructor(
    private val api: ApiService,
    private val db: SampleDatabase
) : BaseRepository() {

    suspend fun getContactListAsync(page: Int, perPage: Int) = safeApiCall {
        val response = api.getContactListAsync(page, perPage)
        response.apply {
            insertContact()
        }
    }

    private suspend fun ContactResponse.insertContact() = safeCatching {
        db.apply {
            withTransaction {
                contactListDao.deleteLocalContactList(
                    page = page, perPage = per_page
                )
                contactListDao.insertContactList(
                    contactList = asContactEntity()
                )
            }
        }
    }

    suspend fun getLocalContactList(page: Int, perPage: Int) = safeApiCall {
        db.contactListDao.getLocalContactList(page = page, perPage = perPage)
            .asContactResponse()
    }
}