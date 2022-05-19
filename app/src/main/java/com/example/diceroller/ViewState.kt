package com.example.diceroller

sealed interface ViewState {

    sealed class RollDie : ViewState {
        abstract val diceType: DiceType

        data class ReadyToRoll(
            override val diceType: DiceType,
        ) : RollDie()

        data class Rolled(
            override val diceType: DiceType,
            val rollValue: Int,
        ) : RollDie()
    }

    object History : ViewState
}
