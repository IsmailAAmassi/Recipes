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

    override fun getLastUpdateCategoryTable(): Long = ConfigRepositoryObj.getLastUpdateCategoryTable(context)

    override fun setLastUpdateCategoryTable(lastUpdate: Long) =
        sharedVariables.setLong(SharedFlag.SYNC_CATEGORY_TABLE, lastUpdate)

    override fun getLastUpdateRecipeTable(): Long = ConfigRepositoryObj.getLastUpdateRecipeTable(context)

    override fun setLastUpdateRecipeTable(lastUpdate: Long) =
        sharedVariables.setLong(SharedFlag.SYNC_RECIPE_TABLE, lastUpdate)

    override fun getLastUpdateIngredientTable(): Long = ConfigRepositoryObj.getLastUpdateIngredientTable(context)

    override fun setLastUpdateIngredientTable(lastUpdate: Long) =
        sharedVariables.setLong(SharedFlag.SYNC_INGREDIENT_TABLE, lastUpdate)

    override fun getLastUpdateStepTable(): Long = ConfigRepositoryObj.getLastUpdateStepTable(context)

    override fun setLastUpdateStepTable(lastUpdate: Long) =
        sharedVariables.setLong(SharedFlag.SYNC_STEP_TABLE, lastUpdate)

    override fun getLastUpdateTipTable(): Long = ConfigRepositoryObj.getLastUpdateTipTable(context)

    override fun setLastUpdateTipTable(lastUpdate: Long) =
        sharedVariables.setLong(SharedFlag.SYNC_TIP_TABLE, lastUpdate)
}