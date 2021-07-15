package com.mageshr2494.restaurant

import android.content.Context
import android.util.Log
import com.mageshr2494.restaurant.datasource.AppDatabase
import com.mageshr2494.restaurant.datasource.entities.StartersItem

class CartHandle {

    companion object {

//        fun getStarterItemCount(context: Context, id: Int): Int {
//            var count = AppDatabase.getDatabase(context).MyCartDao().getStarterCount(id)
//            return count
//        }

        fun addStarterItem(context: Context, startersItem: StartersItem): Long {
            var insert = AppDatabase.getDatabase(context).MyCartDao().insert(startersItem)
            return insert
        }

        fun updateStarterItem(context: Context, id: Int, count: Int): Int {
            var update = AppDatabase.getDatabase(context).MyCartDao().update(id, count)
            Log.v("update count: ", "" + count)
            Log.v("update id: ", "" + id)
            Log.v("update : ", "" + update)
            return update
        }

        fun deleteStarterItem(context: Context, id: Int): Int {
            var delete = AppDatabase.getDatabase(context).MyCartDao().delete(id)
            Log.v("delete : ", "" + delete)
            return delete
        }

        fun getStarterItemCount(context: Context, id: Int): Int {
            var count = AppDatabase.getDatabase(context).MyCartDao().getStarterCount(id)
            return count
        }

        fun getCartTotalItem(context: Context): Int {
            var totalCount = AppDatabase.getDatabase(context).MyCartDao().getTotalCount()
            return totalCount
        }

        fun getMyCartAmount(context: Context): Int {
            var totalAmount = AppDatabase.getDatabase(context).MyCartDao().getMyCartAmount()
            return totalAmount
        }

        fun getMyCart(context: Context): List<StartersItem> {
            var totalAmount = AppDatabase.getDatabase(context).MyCartDao().getMyCart()
            return totalAmount
        }
    }
}