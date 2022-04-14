package com.example.diceroller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.diceroller.databinding.FragmentD10DiceBinding

class D10DiceFragment : Fragment() {
    private var _binding: FragmentD10DiceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentD10DiceBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.rollButton.setOnClickListener {
            rollD10Dice()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun rollD10Dice() {
        val diceText: TextView = binding.resultText
        val diceRange = 1..10
        val diceResult = diceRange.random()
        val diceTextResult = "You got a $diceResult"
        diceText.text = diceTextResult

//        val diceImageResult = when (diceResult) {
//            1 -> R.drawable.dice_1
//            2 -> R.drawable.dice_2
//            3 -> R.drawable.dice_3
//            4 -> R.drawable.dice_4
//            5 -> R.drawable.dice_5
//            else -> R.drawable.dice_6
//        }
//
//        binding.diceImage.setImageResource(diceImageResult)

    }
}