package com.example.diceroller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import com.example.diceroller.databinding.FragmentDiceBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.observeOn

class DiceFragment : Fragment() {
    var diceType:MutableStateFlow<DiceType> = MutableStateFlow(DiceType.D6)
    private var _binding: FragmentDiceBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RolledDiceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDiceBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.rollButton.setOnClickListener {
            recieveDice()
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(RolledDiceViewModel::class.java)
        subscribeToObservables()

    }

    private fun subscribeToObservables() {
        lifecycleScope.launchWhenStarted {
            viewModel.currentDice.collectLatest {

                val updatedText = "Come on, roll the ${it.name}"
                binding.resultText.text = updatedText

                val emptyDiceDrawable = R.drawable.empty_dice
                binding.diceImage.setImageResource(emptyDiceDrawable)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun recieveDice() {
        val newDice = viewModel.rollDice()
        val diceText: TextView = binding.resultText
//        val diceRange = diceType.value.diceRange
//        val diceResult = diceRange.random()
        val diceTextResult = "You got a ${newDice.result}"
        diceText.text = diceTextResult

        val diceImageResult = viewModel.getCurrentDiceType().diceImageList[newDice.result-1]
        binding.diceImage.setImageResource(diceImageResult)

    }
}