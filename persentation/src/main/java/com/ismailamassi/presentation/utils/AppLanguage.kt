package com.ismailamassi.presentation.utils

enum class AppLanguage(val id: String) {
    ENGLISH("en"), ARABIC("ar");

    companion object {
        fun getLanguageById(id: String) =
            when (id) {
                ENGLISH.id -> ENGLISH
                ARABIC.id -> ARABIC
                else -> ENGLISH
            }

    }
}