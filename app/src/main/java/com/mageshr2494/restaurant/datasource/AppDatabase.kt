package com.mageshr2494.restaurant.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mageshr2494.restaurant.datasource.dao.MyCartDao
import com.mageshr2494.restaurant.datasource.entities.StartersItem

@Database(entities = arrayOf(StartersItem::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun MyCartDao(): MyCartDao

    companion object {
        private var appDatabase: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            appDatabase ?: synchronized(this) {
                appDatabase ?: buildDatabase(context).also {
                    appDatabase = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "restaurant")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }
}