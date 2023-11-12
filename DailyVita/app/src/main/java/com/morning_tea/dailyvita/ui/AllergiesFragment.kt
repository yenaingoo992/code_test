package com.morning_tea.dailyvita.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.morning_tea.dailyvita.R
import com.morning_tea.dailyvita.databinding.FragmentAllergiesBinding
import com.morning_tea.dailyvita.domain.model.Allergy
import com.morning_tea.dailyvita.ui.viewmodel.AllergiesViewModel
import com.morning_tea.dailyvita.ui.viewmodel.OnboardingState
import com.morning_tea.dailyvita.ui.viewmodel.OnboardingViewModel
import com.tokenautocomplete.FilteredArrayAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AllergiesFragment : Fragment(R.layout.fragment_allergies) {

    companion object {
        const val TAG = "AllergiesFragment"
    }

    private lateinit var binding: FragmentAllergiesBinding

    private val onboardingViewModel: OnboardingViewModel by activityViewModels()
    private val allergiesViewModel: AllergiesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAllergiesBinding.bind(view)

        binding.apply {
            btnBack.setOnClickListener {
                onboardingViewModel.saveAllergies(listOf())
                onboardingViewModel.onPrevious()
            }
            btnNext.setOnClickListener {
                requireContext().hideKeyboard(root)
                onboardingViewModel.saveAllergies(searchView.objects)
                onboardingViewModel.onNext(OnboardingState.AdditionalInfo)
            }
        }

        allergiesViewModel.allergies.observe(viewLifecycleOwner) {
            val allergyAdapter = object : FilteredArrayAdapter<Allergy>(requireContext(), android.R.layout.simple_spinner_dropdown_item, it) {
                override fun keepObject(obj: Allergy, mask: String?): Boolean {
                    return obj.name.lowercase().startsWith(mask?.lowercase() ?: "")
                }
            }
            binding.searchView.setAdapter(allergyAdapter)
        }

        allergiesViewModel.getAllergies()
    }
}