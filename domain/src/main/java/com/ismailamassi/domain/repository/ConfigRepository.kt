package com.ismailamassi.domain.repository

interface ConfigRepository {
    fun getAppName(): String
    fun setAppName(value: String)

    fun getDBName(): String
    fun setDBName(value: String)
}