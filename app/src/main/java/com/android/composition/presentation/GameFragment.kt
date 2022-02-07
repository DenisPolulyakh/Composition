package com.android.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.composition.R
import com.android.composition.data.GameRepositoryImpl
import com.android.composition.databinding.FragmentGameBinding
import com.android.composition.domain.entity.GameResult
import com.android.composition.domain.entity.Level
import com.android.composition.domain.usecases.GetGameSettingsUseCase

class GameFragment : Fragment() {

    private lateinit var level: Level
    private lateinit var gameResult: GameResult
    private val repository = GameRepositoryImpl
    private val getGameSettingsUseCase: GetGameSettingsUseCase = GetGameSettingsUseCase(repository)
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseArgs()

        binding.tvQuestion.setOnClickListener {
            gameResult = GameResult(true, 10, 10, getGameSettingsUseCase(level))
            launchGameFinishedFragment(gameResult)
        }

    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null).commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun parseArgs() {
        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }
    }


    companion object {
        private const val KEY_LEVEL = "level"
        const val NAME = "GameFragment"
        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }
}