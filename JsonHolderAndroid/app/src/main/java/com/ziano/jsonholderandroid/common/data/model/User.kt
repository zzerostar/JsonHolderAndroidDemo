package com.ziano.jsonholderandroid.common.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/16
 * @desc
 */
@Entity(tableName = User.TABLE_NAME)
@TypeConverters(UserConverter::class)
data class User(
    @PrimaryKey
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
) {

    companion object {
        const val TABLE_NAME = "user"
    }
}

data class Address(
    val street: String, val suite: String, val city: String, val zipcode: String, val geo: Geo
)

data class Geo(
    val lat: String, val lng: String
)

data class Company(
    val name: String, val catchPhrase: String, val bs: String
)

