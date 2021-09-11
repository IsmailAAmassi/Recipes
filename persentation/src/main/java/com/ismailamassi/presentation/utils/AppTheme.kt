package com.ismailamassi.presentation.utils

enum class AppTheme(val id: Int, val label: String) {
    SYSTEM(-1, "system"), LIGHT(1, "light"), NIGHT(2, "night");

    companion object {
        fun getThemeById(id: Int) = when (id) {
            SYSTEM.id -> SYSTEM
            LIGHT.id -> LIGHT
            NIGHT.id -> NIGHT
            else -> SYSTEM
        }

        fun getThemeByLabel(label: String) = when (label) {
            SYSTEM.label -> SYSTEM
            LIGHT.label -> LIGHT
            NIGHT.label -> NIGHT
            else -> SYSTEM
        }
    }
}