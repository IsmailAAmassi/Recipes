package com.ismailamassi.data.repository

import android.content.Context
import com.ismailamassi.data.shared.ConfigRepositoryObj
import com.ismailamassi.data.shared.SharedFlag
import com.ismailamassi.data.shared.SharedVariables
import com.ismailamassi.domain.repository.ConfigRepository
import javax.inject.Inject

class ConfigRepositoryImpl @Inject constructor(
    private val context: Context
) : ConfigRepository {

    var sharedVariables: SharedVariables = SharedVariables(context)

    override fun getAppName(): String = ConfigRepositoryObj.getAppName(context)

    override fun setAppName(value: String) = sharedVariables.setString(SharedFlag.APP_NAME, value)

    override fun getDBName(): String = ConfigRepositoryObj.getDBName(context)

    override fun setDBName(value: String) = sharedVariables.setString(SharedFlag.DB_NAME, value)
}