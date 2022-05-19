package com.example.diceroller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Calendar
import kotlin.random.Random

private const val REPLAY_LAST: Int = 1

class DiceRollerViewModel : ViewModel() {
    private val currentDice: MutableStateFlow<DiceType> = MutableStateFlow(DiceType.D6)

    private val _rollHistoryState: MutableStateFlow<List<Dice>> = MutableStateFlow(listOf())
    val rollHistoryState: StateFlow<List<Dice>> get() = _rollHistoryState.asStateFlow()

    private val _rollDieState: MutableSharedFlow<ViewState> = MutableSharedFlow(REPLAY_LAST)
    val viewState: SharedFlow<ViewState> get() = _rollDieState.asSharedFlow()

    init {
        viewModelScope.launch { _rollDieState.emit(ViewState.RollDie.ReadyToRoll(DiceType.D6)) }
    }

    fun onRollClicked() {
        suspend fun rollDice(): Dice = currentDice.map {
            Dice(
                diceType = currentDice.value,
                result = it.diceRange.random(Random(System.currentTimeMillis())),
                time = Calendar.getInstance(),
            )
        }.first()

        viewModelScope.launch {
            val rollResult = rollDice()
            _rollHistoryState.getAndUpdate { it + rollResult }
            _rollDieState.emit(
                ViewState.RollDie.Rolled(currentDice.value, rollResult.result)
            )
        }
    }

    fun onChangeDieIntent(selectedDie: DiceType) {
        viewModelScope.launch {
            _rollDieState.emit(ViewState.RollDie.ReadyToRoll(selectedDie))
            currentDice.emit(selectedDie)
        }
    }

    fun onViewRollHistoryIntent() {
        viewModelScope.launch {
            _rollDieState.emit(ViewState.History)
        }
    }
}
