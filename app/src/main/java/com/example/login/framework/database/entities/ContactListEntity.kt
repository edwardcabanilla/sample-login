package com.example.login.framework.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.login.framework.network.api.response.Contact
import com.example.login.framework.network.api.response.ContactResponse

@Entity(tableName = "CONTACT_LIST", indices = [ Index(value = ["id"], unique = true) ])
data class ContactListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val contactList: List<Contact>
)

fun ContactListEntity.asContactResponse(): ContactResponse {
    return ContactResponse(
        page = page,
        per_page = perPage,
        total = total,
        total_pages = totalPages,
        data = contactList
    )
}

fun ContactResponse.asContactEntity(): ContactListEntity{
    return ContactListEntity(
        0,
        page = page,
        perPage = per_page,
        total = total,
        totalPages = total_pages,
        contactList = data
    )
}
