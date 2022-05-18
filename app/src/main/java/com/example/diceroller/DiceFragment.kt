package com.example.diceroller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.diceroller.databinding.FragmentDiceBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest

class DiceFragment : Fragment() {
    var diceType: MutableStateFlow<DiceType> = MutableStateFlow(DiceType.D6)
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

        binding.rollButton.setOnClickListener { onRollClicked() }
    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            viewModel.currentDice.collectLatest {

                // TODO this should be a string resource
                val updatedText = "Come on, roll the ${it.name}"
                binding.resultText.text = updatedText

                val diceTypeDrawable = it.diceTypeIcon
                binding.diceImage.setImageResource(diceTypeDrawable)
                binding.diceImage.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
            }
        }
    }

    private fun onRollClicked() {
        val newDice = viewModel.rollDice()
        val diceText: TextView = binding.resultText

        // TODO this should be a string resource
        val diceTextResult = "You got a ${newDice.result}"
        diceText.text = diceTextResult

        val diceImageResult = viewModel.getCurrentDiceType().diceImageList[newDice.result - 1]
        binding.diceImage.setImageResource(diceImageResult)
        binding.diceImage.setColorFilter(
            ContextCompat.getColor(
                requireContext(),
                com.google.android.material.R.color.mtrl_btn_transparent_bg_color
            )
        )
    }
}
