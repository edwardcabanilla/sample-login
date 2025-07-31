package com.example.login.framework.database.room.converter

import androidx.room.TypeConverter
import com.example.login.framework.network.api.response.Contact
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ContactListConverter {

    @TypeConverter
    fun fromContactList(value: String?): List<Contact>? {
        if (value == null) return null
        val listType = object : TypeToken<List<Contact>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toContactListString(list: List<Contact>?): String? {
        return Gson().toJson(list)
    }
}