package com.example.diceroller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.diceroller.databinding.FragmentDiceBinding
import kotlinx.coroutines.flow.collect

class DiceFragment : Fragment() {
    private lateinit var binding: FragmentDiceBinding
    private lateinit var viewModel: DiceRollerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DiceRollerViewModel::class.java]

        observeState()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDiceBinding.inflate(inflater, container, false).run {
        binding = this
        this.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rollButton.setOnClickListener { viewModel.onRollClicked() }
    }

    private fun observeState() {

        fun handleReadyToRoll(it: ViewState.RollDie.ReadyToRoll) = binding.run {
            resultText.text = getString(R.string.prompt_roll_die, it.diceType)
            diceImage.setImageResource(it.diceType.diceTypeIcon)
            diceImage.setColorFilter(
                ContextCompat.getColor(requireContext(), R.color.white)
            )
        }

        fun handleLatestRoll(it: ViewState.RollDie.Rolled) = binding.run {
            resultText.text =
                getString(R.string.roll_result_message, it.rollValue)

            diceImage.setImageResource(it.diceType.diceImages[it.rollValue - 1])
            diceImage.setColorFilter(
                ContextCompat.getColor(requireContext(), R.color.transparent)
            )
        }

        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect {
                when (it) {
                    is ViewState.RollDie.ReadyToRoll -> handleReadyToRoll(it)
                    is ViewState.RollDie.Rolled -> handleLatestRoll(it)
                    ViewState.History ->
                        findNavController().navigate(
                            DiceFragmentDirections.actionDiceFragmentToRolledDicesListFragment()
                        )
                }
            }
        }
    }
}
