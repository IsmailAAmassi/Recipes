package com.ismailamassi.presentation.utils

enum class AppTheme(val id: Int) {
    SYSTEM(-1), LIGHT(1), NIGHT(2);

  companion object{
      fun getThemeById(id: Int) = when (id) {
          SYSTEM.id -> SYSTEM
          LIGHT.id -> LIGHT
          NIGHT.id -> NIGHT
          else -> SYSTEM
      }
  }
}