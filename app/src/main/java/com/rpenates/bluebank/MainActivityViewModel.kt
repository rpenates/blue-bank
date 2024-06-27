package com.rpenates.bluebank

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rpenates.bluebank.models.Transaction
import com.rpenates.bluebank.models.User
import io.realm.kotlin.ext.query

class MainActivityViewModel:ViewModel() {

    val TAG = "MainActivityViewModel"
    private val realm = BluebankApp.realm

    val currentUser = MutableLiveData<User?>()
    val transactionList = MutableLiveData<List<Transaction>>()


    fun loadUser() {
        val user = realm.query<User>().first().find()
        currentUser.value = user
        Log.i(TAG, "User: $user")
    }
}