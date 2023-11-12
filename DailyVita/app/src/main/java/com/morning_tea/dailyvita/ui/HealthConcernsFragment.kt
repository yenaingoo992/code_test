package com.morning_tea.dailyvita.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.morning_tea.dailyvita.R
import com.morning_tea.dailyvita.databinding.FragmentHealthConcernsBinding
import com.morning_tea.dailyvita.ui.adapter.HealthConcernAdapter
import com.morning_tea.dailyvita.ui.viewmodel.HealthConcernsViewModel
import com.morning_tea.dailyvita.ui.viewmodel.OnboardingState
import com.morning_tea.dailyvita.ui.viewmodel.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HealthConcernsFragment : Fragment(R.layout.fragment_health_concerns) {

    companion object {
        const val TAG = "HealthConcernsFragment"
    }

    private lateinit var binding: FragmentHealthConcernsBinding

    private val onboardingViewModel: OnboardingViewModel by activityViewModels()
    private val healthConcernsViewModel: HealthConcernsViewModel by viewModels()

    private val healthConcernAdapter: HealthConcernAdapter by lazy {
        HealthConcernAdapter()
    }

    private val itemTouchHelperCallback : ItemTouchHelper.Callback = object : ItemTouchHelper.Callback() {

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            return makeMovementFlags(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                0
            )
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val adapter = recyclerView.adapter as HealthConcernAdapter
            val draggedItemIndex = viewHolder.adapterPosition
            val targetIndex = target.adapterPosition
            healthConcernsViewModel.swipe(draggedItemIndex, targetIndex)
            adapter.notifyItemMoved(draggedItemIndex, targetIndex)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        }

    }

    private val itemTouchHelper: ItemTouchHelper by lazy {
        ItemTouchHelper(itemTouchHelperCallback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHealthConcernsBinding.bind(view)

        binding.apply {
            rvConcerns.adapter = healthConcernAdapter
            btnNext.setOnClickListener {
                val concerns = healthConcernsViewModel.getSelectedConcerns()
                if (concerns.isEmpty()) {
                    binding.errorConcerns.isVisible = true
                    binding.errorConcerns.text = getString(R.string.error_required)
                } else {
                    binding.errorConcerns.isVisible = false
                    onboardingViewModel.saveSelectedConcerns(concerns)
                    onboardingViewModel.onNext(OnboardingState.Diets)
                }
            }
            btnBack.setOnClickListener {
                onboardingViewModel.saveSelectedConcerns(listOf())
                onboardingViewModel.onPrevious()
            }
            itemTouchHelper.attachToRecyclerView(rvConcerns)
        }

        healthConcernsViewModel.healthConcern.observe(viewLifecycleOwner) {
            it.forEach { concern ->
                addChipToGroup(text = concern.name) { chip ->
                    try {
                        healthConcernsViewModel.onHealthConcernClick(concern)
                        chip.isSelected = !chip.isSelected
                    } catch (e: IllegalArgumentException) {
                        Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        healthConcernsViewModel.selectedConcerns.observe(viewLifecycleOwner) {
            healthConcernAdapter.submitList(it)
        }

        healthConcernsViewModel.getHealthConcerns()
    }

    private fun addChipToGroup(text: String, onClick: (Chip) -> Unit) {
        val chip = Chip(context)
        chip.text = text
        chip.isChipIconVisible = false
        chip.isCloseIconVisible = false
        // necessary to get single selection working
        chip.isClickable = true
        chip.isCheckable = false
        chip.chipBackgroundColor =
            ContextCompat.getColorStateList(requireContext(), R.color.bg_chip_state_list)
        chip.setTextColor(
            ContextCompat.getColorStateList(
                requireContext(),
                R.color.bg_chip_text_list
            )
        )
        binding.cgConcerns.addView(chip as View)
        chip.setOnClickListener {
            onClick(it as Chip)
        }
    }
}