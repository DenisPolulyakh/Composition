package com.android.composition.domain.usecases

import com.android.composition.domain.entity.GameSettings
import com.android.composition.domain.entity.Level
import com.android.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}