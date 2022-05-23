package com.example.diceroller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diceroller.databinding.ItemRolledDiceBinding
import java.text.DateFormat

class DiceViewAdapter : RecyclerView.Adapter<DiceViewAdapter.DiceViewHolder>() {
    private var items: List<Dice> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiceViewHolder =
        DiceViewHolder(
            ItemRolledDiceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: DiceViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    fun setData(items: List<Dice>) {
        items.forEachIndexed { index, item ->
            if (this.items.getOrNull(index) != item) notifyItemChanged(index)
        }
        this.items = items
    }

    class DiceViewHolder(private val itemRolledDiceBinding: ItemRolledDiceBinding) :
        RecyclerView.ViewHolder(itemRolledDiceBinding.root) {

        fun bind(item: Dice) = with(itemRolledDiceBinding) {
            rolledDiceText.text =
                root.context.getString(R.string.history_roll_result_message, item.result)
            val dateTimeOnLocale = DateFormat.getDateTimeInstance().format(item.time.time)
            rolledDiceTime.text = dateTimeOnLocale

            rolledDiceIcon.setImageResource(item.diceType.diceTypeIcon)
        }
    }
}
