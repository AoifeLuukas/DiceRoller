package com.example.diceroller

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.diceroller.databinding.ItemRolledDiceBinding
import java.text.DateFormat

class DiceViewAdapter (
    private val items: List<Dice>
    ): RecyclerView.Adapter<DiceViewAdapter.DiceViewHolder>() {

    class DiceViewHolder(private val itemRolledDiceBinding: ItemRolledDiceBinding) :
        RecyclerView.ViewHolder(itemRolledDiceBinding.root) {

        fun bindDice(item: Dice) {
            with(itemRolledDiceBinding) {

                val rolledDiceResultText = "You rolled a ${item.result}"
                rolledDiceText.text = rolledDiceResultText

                val localeDateTimeFormat: DateFormat = DateFormat.getDateTimeInstance()
                val dateTimeOnLocale = localeDateTimeFormat.format(item.time.time)
                rolledDiceTime.text = dateTimeOnLocale

                rolledDiceIcon.setImageResource(item.diceType.diceTypeIcon)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiceViewHolder =
        DiceViewHolder(
            ItemRolledDiceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
    false
            )
        )

    override fun onBindViewHolder(holder: DiceViewHolder, position: Int) {
        holder.bindDice(items[position])
    }

    override fun getItemCount(): Int = items.size
}