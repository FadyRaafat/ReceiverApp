package com.tocaan.receiver.api.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.cic.supcom.api.room.BaseDao
import com.tocaan.receiver.api.models.User

@Dao
abstract class UserDao : BaseDao<User>() {
    @Query("SELECT * FROM user")
    abstract fun getAllUsers(): LiveData<List<User>>

}