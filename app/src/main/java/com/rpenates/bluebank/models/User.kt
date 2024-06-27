package com.rpenates.bluebank.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class User: RealmObject {
    @PrimaryKey var username: String = ""
    var fullName: String = ""
    var password: String = ""
    var account: BankAccount? = null
}