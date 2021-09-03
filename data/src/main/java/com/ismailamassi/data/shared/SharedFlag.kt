package com.ismailamassi.data.shared

import com.ismailamassi.data.api.ApiTablesNames

enum class SharedFlag(val key: String) {
    APP_NAME("app_name"),
    DB_NAME("db_name"),
    SYNC_CATEGORY_TABLE("sync_${ApiTablesNames.CATEGORY_TABLE}_table"),
    SYNC_RECIPE_TABLE("sync_${ApiTablesNames.RECIPE_TABLE}_table"),
    SYNC_INGREDIENT_TABLE("sync_${ApiTablesNames.INGREDIENT_TABLE}_table"),
    SYNC_STEP_TABLE("sync_${ApiTablesNames.STEP_TABLE}_table"),
    SYNC_TIP_TABLE("sync_${ApiTablesNames.TIP_TABLE}_table"),
}