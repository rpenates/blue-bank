package com.rpenates.bluebank.models

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class BankAccount: RealmObject {
    var balance: Double = 0.0
    var transactions: RealmList<Transaction> = realmListOf()
}