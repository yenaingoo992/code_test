package com.morning_tea.dailyvita.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.morning_tea.dailyvita.R
import com.morning_tea.dailyvita.databinding.FragmentDietsBinding
import com.morning_tea.dailyvita.ui.adapter.DietAdapter
import com.morning_tea.dailyvita.ui.viewmodel.DietsViewModel
import com.morning_tea.dailyvita.ui.viewmodel.OnboardingState
import com.morning_tea.dailyvita.ui.viewmodel.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DietsFragment : Fragment(R.layout.fragment_diets) {

    companion object {
        const val TAG = "DietsFragment"
    }

    private lateinit var binding: FragmentDietsBinding

    private val onboardingViewModel: OnboardingViewModel by activityViewModels()
    private val dietsViewModel: DietsViewModel by viewModels()

    private val dietAdapter: DietAdapter by lazy {
        DietAdapter {
            dietsViewModel.onDietSelected(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDietsBinding.bind(view)

        binding.apply {
            rvDiets.adapter = dietAdapter
            btnBack.setOnClickListener {
                onboardingViewModel.saveSelectedDiets(listOf())
                onboardingViewModel.onPrevious()
            }
            btnNext.setOnClickListener {
                onboardingViewModel.saveSelectedDiets(dietsViewModel.getSelectedDiets())
                onboardingViewModel.onNext(OnboardingState.Allergies)
            }
        }

        dietsViewModel.diets.observe(viewLifecycleOwner) {
            dietAdapter.submitList(it)
        }

        dietsViewModel.getDiets()
    }
}