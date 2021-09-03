package com.ismailamassi.data.shared

import android.content.Context

object ConfigRepositoryObj {

    fun getAppName(context: Context): String =
        SharedVariables(context).getString(SharedFlag.APP_NAME)

    fun getDBName(context: Context): String = SharedVariables(context).getString(SharedFlag.DB_NAME)

    fun getLastUpdateCategoryTable(context: Context): Long = SharedVariables(context).getLong(SharedFlag.SYNC_CATEGORY_TABLE)

    fun getLastUpdateRecipeTable(context: Context): Long = SharedVariables(context).getLong(SharedFlag.SYNC_RECIPE_TABLE)

    fun getLastUpdateIngredientTable(context: Context): Long = SharedVariables(context).getLong(SharedFlag.SYNC_INGREDIENT_TABLE)

    fun getLastUpdateStepTable(context: Context): Long = SharedVariables(context).getLong(SharedFlag.SYNC_STEP_TABLE)

    fun getLastUpdateTipTable(context: Context): Long = SharedVariables(context).getLong(SharedFlag.SYNC_TIP_TABLE)
}