package com.ismailamassi.domain.repository

interface ConfigRepository {
    fun getAppName(): String
    fun setAppName(value: String)

    fun getDBName(): String
    fun setDBName(value: String)

    fun getLastUpdateCategoryTable(): Long
    fun setLastUpdateCategoryTable(lastUpdate: Long)

    fun getLastUpdateRecipeTable(): Long
    fun setLastUpdateRecipeTable(lastUpdate: Long)

    fun getLastUpdateIngredientTable(): Long
    fun setLastUpdateIngredientTable(lastUpdate: Long)

    fun getLastUpdateStepTable(): Long
    fun setLastUpdateStepTable(lastUpdate: Long)

    fun getLastUpdateTipTable(): Long
    fun setLastUpdateTipTable(lastUpdate: Long)
}