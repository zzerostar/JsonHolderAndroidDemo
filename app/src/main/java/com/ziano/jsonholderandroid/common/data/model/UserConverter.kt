package com.ziano.jsonholderandroid.common.data.model

import androidx.room.TypeConverter
import org.json.JSONObject

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/16
 * @desc
 */

class UserConverter {

    @TypeConverter
    fun fromAddress(value: Address): String {
        val json = JSONObject()
        json.put("street", value.street)
        json.put("suite", value.suite)
        json.put("city", value.city)
        json.put("zipcode", value.zipcode)
        json.put("lat", value.geo.lat)
        json.put("lng", value.geo.lng)
        return json.toString()
    }

    @TypeConverter
    fun toAddress(value: String): Address {
        val json: JSONObject = JSONObject(value)
        val geo = Geo(json.getString("lat"), json.getString("lng"))
        return Address(json.getString("street"), json.getString("suite"), json.getString("city"), json.getString("zipcode"), geo)
    }
    @TypeConverter
    fun fromCompany(value: Company): String {
        val json = JSONObject().apply {
            put("name", value.name)
            put("catchPhrase", value.catchPhrase)
            put("bs", value.bs)
        }
        return json.toString()
    }

    @TypeConverter
    fun toCompany(value: String): Company {
        val json = JSONObject(value)
        return Company(json.getString("name"), json.getString("catchPhrase"), json.getString("bs"))
    }
}