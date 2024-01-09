package com.example.nexos_credibanco.di

import android.app.Application
import com.example.nexos_credibanco.database.TransactionDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        TransactionDatabase.getInstance(this)
    }
}