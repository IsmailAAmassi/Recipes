package com.ismailamassi.data.repository

import com.ismailamassi.data.db.tip.TipDao
import com.ismailamassi.domain.repository.TipRepository
import javax.inject.Inject

class TipRepositoryImpl @Inject constructor(
    private val tipDao: TipDao
) : TipRepository {
}