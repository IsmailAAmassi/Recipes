package com.ismailamassi.domain.model.category

data class CategoryDto(
    var id: Long,
    var title: String,
    var avatar: String,
    var recipesCount: Int
)