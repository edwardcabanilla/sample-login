package com.example.login.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.login.framework.database.entities.ContactListEntity

@Dao
interface ContactListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContactList(contactList: ContactListEntity)

    @Query("SELECT * FROM CONTACT_LIST WHERE page = :page AND perPage = :perPage")
    fun getLocalContactList(page: Int, perPage: Int): ContactListEntity

    @Query("DELETE FROM CONTACT_LIST WHERE page = :page AND perPage = :perPage")
    fun deleteLocalContactList(page: Int, perPage: Int)
}
