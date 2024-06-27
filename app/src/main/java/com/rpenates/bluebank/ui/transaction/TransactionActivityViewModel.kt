package com.rpenates.bluebank.ui.transaction


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rpenates.bluebank.BluebankApp
import com.rpenates.bluebank.models.Transaction
import com.rpenates.bluebank.models.User
import io.realm.kotlin.ext.query
import kotlinx.coroutines.launch

class TransactionActivityViewModel: ViewModel() {

    val TAG = "TransactionActivityViewModel"
    private val realm = BluebankApp.realm
    // private val currentUser = realm.query<User>().first().find()

    fun addTransaction(description: String, amount: Double) {
        val transaction = Transaction().apply {
            this.description = description
            this.amount = amount
        }
        // currentUser?.account?.transactions?.add(transaction)
        viewModelScope.launch {
            realm.write {
                val queryUser = this.query<User>().first().find()
                if (queryUser != null) {
                    queryUser.account?.balance = queryUser.account?.balance!! + amount
                    queryUser.account?.transactions?.add(transaction)
                }
            }
        }
    }
}