package com.task.themeal.modules.meal.data.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.task.themeal.AppDatabase

actual class DatabaseDriverFactory (private val context: Context){
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, "meal.db")
    }
}