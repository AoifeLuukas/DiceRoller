package com.example.diceroller

import androidx.annotation.DrawableRes
import java.util.*

// NitPick: The singular form of Dice is Die
data class Dice(val diceType: DiceType, val result: Int, val time: Calendar)

enum class DiceType(
    @DrawableRes val diceImages: List<Int>,
    val diceRange: IntRange,
    @DrawableRes val diceTypeIcon: Int
) {

    D4(
        listOf(
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4,
        ),
        1..4,
        R.drawable.nav_menu_d4_icon
    ),
    D6(
        listOf(
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4,
            R.drawable.dice_5,
            R.drawable.dice_6,
        ),
        1..6,
        R.drawable.nav_menu_d6_icon
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
            R.drawable.dice_2,
        ),
        1..8,
        R.drawable.nav_menu_d8_icon
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
            R.drawable.dice_4,
        ),
        1..10,
        R.drawable.nav_menu_d10_icon
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
            R.drawable.dice_6,
        ),
        1..12,
        R.drawable.nav_menu_d12_icon
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
            R.drawable.dice_2,
        ),
        1..20,
        R.drawable.nav_menu_d20_icon
    ),
}
