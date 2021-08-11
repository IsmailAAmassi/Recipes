package com.ismailamassi.data.shared

import android.content.Context

object ConfigRepositoryObj {

    fun getAppName(context: Context): String =
        SharedVariables(context).getString(SharedFlag.APP_NAME)

    fun getDBName(context: Context): String = SharedVariables(context).getString(SharedFlag.DB_NAME)
}