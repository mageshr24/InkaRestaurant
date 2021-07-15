package com.mageshr2494.restaurant.datasource.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "starters")
data class StartersItem(
    @PrimaryKey
    val id: Int,
    val day: Boolean,
    val description: String,
    val itemName: String,
    val night: Boolean,
    val price: Int,
    val count: Int
)