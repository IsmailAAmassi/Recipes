package com.ismailamassi.data.mapper

import com.ismailamassi.data.db.tip.TipData
import com.ismailamassi.domain.model.tip.TipDto


fun TipDto.toData() = TipData(
    id = id,
    label = label ?: ""
)

fun TipData.toDto() = TipDto(
    id = id,
    label = label
)
fun List<TipDto>.toListData() = this.map { it.toData() }

fun List<TipData>.toListDto() = this.map { it.toDto() }
