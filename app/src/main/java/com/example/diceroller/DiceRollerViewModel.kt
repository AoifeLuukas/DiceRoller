package com.example.diceroller

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class DiceRollerViewModel : ViewModel() {
    private val _currentDice: MutableStateFlow<DiceType> = MutableStateFlow(DiceType.D6)
    val currentDice = _currentDice.asStateFlow()

    private val _listOfDices: MutableStateFlow<List<Dice>> = MutableStateFlow(listOf())
    private val listOfDices = _listOfDices.asStateFlow()

    fun getHistoryOfDices(): List<Dice> = listOfDices.value

    private fun addDiceToList(dice: Dice) {
        _listOfDices.value = listOfDices.value + dice
    }

    fun getCurrentDiceType(): DiceType = currentDice.value

    fun rollDice(): Dice {
        val diceRange = getCurrentDiceType().diceRange
        val diceResult = diceRange.random(Random(System.currentTimeMillis()))

        val newDice = Dice(getCurrentDiceType(), diceResult, java.util.Calendar.getInstance())
        addDiceToList(newDice)

        return newDice
    }

    fun triggerD4() {
        _currentDice.value = DiceType.D4
    }

    fun triggerD6() {
        _currentDice.value = DiceType.D6
    }

    fun triggerD8() {
        _currentDice.value = DiceType.D8
    }

    fun triggerD10() {
        _currentDice.value = DiceType.D10
    }

    fun triggerD12() {
        _currentDice.value = DiceType.D12
    }

    fun triggerD20() {
        _currentDice.value = DiceType.D20
    }
}
