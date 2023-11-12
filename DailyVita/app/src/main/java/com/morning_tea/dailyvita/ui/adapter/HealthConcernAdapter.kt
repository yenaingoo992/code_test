package com.morning_tea.dailyvita.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.morning_tea.dailyvita.R
import com.morning_tea.dailyvita.databinding.ItemHealthConcernBinding
import com.morning_tea.dailyvita.domain.model.HealthConcern

class HealthConcernAdapter : ListAdapter<HealthConcern, HealthConcernAdapter.ItemViewHolder>(HealthConcernDiff) {

    inner class ItemViewHolder(private val binding: ItemHealthConcernBinding): RecyclerView.ViewHolder(binding.root) {

        fun onBind(healthConcern: HealthConcern) {
            binding.apply {
                concern = healthConcern
                concernChip.isSelected = true
                concernChip.chipBackgroundColor = ContextCompat.getColorStateList(root.context, R.color.bg_chip_state_list)
                concernChip.setTextColor(ContextCompat.getColorStateList(root.context, R.color.bg_chip_text_list))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemHealthConcernBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    object HealthConcernDiff : DiffUtil.ItemCallback<HealthConcern>() {

        override fun areItemsTheSame(oldItem: HealthConcern, newItem: HealthConcern): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HealthConcern, newItem: HealthConcern): Boolean {
            return oldItem == newItem
        }

    }
}