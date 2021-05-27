package com.tocaan.receiver.api.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    var id: Int?,
    var email: String?,
    var name: String?,
    var phone: String?,
    var username: String?,
    var website: String?

)