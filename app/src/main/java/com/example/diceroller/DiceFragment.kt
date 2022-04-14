package com.example.diceroller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.diceroller.databinding.FragmentDiceBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DiceFragment : Fragment(), UpdateFrag {
    var diceType:MutableStateFlow<DiceType> = MutableStateFlow(DiceType.D6)
    private var _binding: FragmentDiceBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as MainActivity).fragmentUpdate(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDiceBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.rollButton.setOnClickListener {
            rollDice()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun rollDice() {
        val diceText: TextView = binding.resultText
        val diceRange = diceType.value.diceRange
        val diceResult = diceRange.random()
        val diceTextResult = "You got a $diceResult"
        diceText.text = diceTextResult

        val diceImageResult = diceType.value.diceImageList[diceResult-1]
        binding.diceImage.setImageResource(diceImageResult)

    }

    override fun updateFrag(diceType: DiceType) {
        this.diceType.value = diceType
    }
}