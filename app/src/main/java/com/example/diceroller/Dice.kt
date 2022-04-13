package com.example.diceroller

class Dice (private val range: IntRange) {
    fun rollDice() {
        val diceResult = range.random()
        val diceTextResult = "You got a $diceResult"
    }
}