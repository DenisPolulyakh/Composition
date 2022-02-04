package com.android.composition.domain.entity

import com.android.composition.domain.entity.GameSettings

data class GameResult(
    val winner: Boolean,
    val countOfRightAnswers: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
)