package com.mageshr2494.restaurant.datasource.dao

import androidx.room.*
import com.mageshr2494.restaurant.datasource.entities.StartersItem

@Dao
interface MyCartDao {

    @Query("SELECT * FROM starters")
    fun getMyCart(): List<StartersItem>

    @Query("SELECT * FROM starters WHERE id = :id")
    fun getStarter(id: Int): StartersItem

    @Query("SELECT count FROM starters WHERE id = :id")
    fun getStarterCount(id: Int): Int

    @Query("SELECT SUM(count*price) FROM starters")
    fun getMyCartAmount(): Int

    @Query("SELECT SUM(count) FROM starters")
    fun getTotalCount(): Int

    @Query("UPDATE starters SET count = :count WHERE id = :id")
    fun update(id: Int, count: Int): Int

    @Query("DELETE FROM starters WHERE id = :id")
    fun delete(id: Int): Int

    @Insert
    fun insert(item: StartersItem): Long
}