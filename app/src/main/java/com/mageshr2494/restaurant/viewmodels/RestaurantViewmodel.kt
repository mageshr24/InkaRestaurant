package com.mageshr2494.restaurant.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mageshr2494.restaurant.datasource.entities.StartersItem
import com.mageshr2494.restaurant.getJsonDataFromAsset

class RestaurantViewmodel() : ViewModel() {

    fun loadStartersData(context :Context): List<StartersItem> {

        val starterJsonData = getJsonDataFromAsset(context, "starter.json")
        val gson = Gson()
        val listPersonType = object : TypeToken<List<StartersItem>>() {}.type
        var starters: List<StartersItem> = gson.fromJson(starterJsonData, listPersonType)
        return starters
    }
}