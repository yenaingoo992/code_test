package com.morning_tea.dailyvita.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.morning_tea.dailyvita.databinding.ItemDietBinding
import com.morning_tea.dailyvita.domain.model.Diet
import it.sephiroth.android.library.xtooltip.Tooltip

class DietAdapter(val onItemSelected: (Diet) -> Unit) :
    ListAdapter<Diet, DietAdapter.ItemViewHolder>(DietDiff) {

    inner class ItemViewHolder(private val binding: ItemDietBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val builder get() = Tooltip.Builder(binding.root.context)
            .anchor(binding.tooltip)
            .arrow(true)
            .maxWidth(500)
            .showDuration(1000L)
            .fadeDuration(1000L)
            .overlay(true)

        fun onBind(diet: Diet) {
            binding.apply {
                this.diet = diet
                chkDiet.isChecked = diet.isChecked

                chkDiet.setOnClickListener {
                    diet.isChecked = chkDiet.isChecked
                    onItemSelected(diet)
                }
                this.tooltip.setOnLongClickListener {
                    builder.text(diet.toolTip).create().show(binding.tooltip, Tooltip.Gravity.RIGHT, false)
                    return@setOnLongClickListener true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemDietBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    object DietDiff : DiffUtil.ItemCallback<Diet>() {

        override fun areItemsTheSame(oldItem: Diet, newItem: Diet): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Diet, newItem: Diet): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.toolTip == newItem.toolTip && oldItem.isChecked == newItem.isChecked
        }

    }
}