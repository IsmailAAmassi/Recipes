package com.ismailamassi.data.repository

import com.ismailamassi.data.db.step.StepDao
import com.ismailamassi.domain.repository.StepRepository
import javax.inject.Inject

class StepRepositoryImpl @Inject constructor(
    private val stepDao: StepDao
) : StepRepository {
}