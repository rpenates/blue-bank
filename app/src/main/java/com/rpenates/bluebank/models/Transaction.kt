package com.rpenates.bluebank.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Transaction : RealmObject {
    @PrimaryKey var id: ObjectId = ObjectId()
    var transactionDate: String = ""
    var amount: Double? = null
}