package com.android.composition.domain.repository

import com.android.composition.domain.entity.GameSettings
import com.android.composition.domain.entity.Level
import com.android.composition.domain.entity.Question

interface GameRepository {
    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings

}