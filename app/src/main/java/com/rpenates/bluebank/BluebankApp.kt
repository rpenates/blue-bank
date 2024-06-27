package com.rpenates.bluebank

import android.app.Application
import com.rpenates.bluebank.models.BankAccount
import com.rpenates.bluebank.models.Transaction
import com.rpenates.bluebank.models.User
import com.rpenates.bluebank.utils.SharedPreferencesHelper
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class BluebankApp: Application() {

    val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    companion object {
        lateinit var realm: Realm
    }

    override fun onCreate() {
        super.onCreate()
        initRealm()
        checkFirstRun()
    }

    fun initRealm() {
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    User::class,
                    Transaction::class,
                    BankAccount::class
                )
            )
        )
    }

    fun checkFirstRun() {
        if (SharedPreferencesHelper.isFirstLaunch(this)) {
            // Insert default user
            val user = User()
            user.fullName = BuildConfig.defaultName
            user.username = BuildConfig.defaultUsername
            user.password = BuildConfig.defaultPassword
            user.account = BankAccount().apply {
                balance = BuildConfig.defaultBalance
            }

            applicationScope.launch {
                withContext(Dispatchers.IO) {
                    realm.write {
                        copyToRealm(user)
                    }
                }
            }
            SharedPreferencesHelper.setFirstLaunch(this, false)

        }
    }

    override fun onTerminate() {
        super.onTerminate()
        applicationScope.cancel()
    }
}