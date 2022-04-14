package com.example.diceroller

import androidx.annotation.DrawableRes

class Dice(private val range: IntRange) {
    fun rollDice() {
        val diceResult = range.random()
        val diceTextResult = "You got a $diceResult"
    }
}

interface UpdateFrag {
    fun updateFrag(diceType: DiceType)
}

enum class DiceType(@DrawableRes val diceImageList: List<Int>, val diceRange: IntRange) {

    D4(
        listOf(
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4
        ),
        1..4
    ),
    D6(
        listOf(
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4,
            R.drawable.dice_5,
            R.drawable.dice_6
        ),
        1..6
    ),
    D8(
        listOf(
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4,
            R.drawable.dice_5,
            R.drawable.dice_6,
            R.drawable.dice_1,
            R.drawable.dice_2
        ),
        1..8
    ),
    D10(
        listOf(
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4,
            R.drawable.dice_5,
            R.drawable.dice_6,
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4
        ),
        1..10
    ),
    D12(
        listOf(
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4,
            R.drawable.dice_5,
            R.drawable.dice_6,
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4,
            R.drawable.dice_5,
            R.drawable.dice_6
        ),
        1..12
    ),
    D20(
        listOf(
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4,
            R.drawable.dice_5,
            R.drawable.dice_6,
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4,
            R.drawable.dice_5,
            R.drawable.dice_6,
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4,
            R.drawable.dice_5,
            R.drawable.dice_6,
            R.drawable.dice_1,
            R.drawable.dice_2
        ),
        1..20
    ),
}

