package com.morning_tea.dailyvita.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.morning_tea.dailyvita.R
import com.morning_tea.dailyvita.databinding.FrgmentGetStartedBinding
import com.morning_tea.dailyvita.ui.viewmodel.OnboardingState
import com.morning_tea.dailyvita.ui.viewmodel.OnboardingViewModel

class GetStartedFragment : Fragment(R.layout.frgment_get_started) {

    companion object {
        const val TAG = "OnboardingFragment"
    }

    private lateinit var binding: FrgmentGetStartedBinding
    private val onboardingViewModel: OnboardingViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FrgmentGetStartedBinding.bind(view)

        binding.apply {
            btnGetStarted.setOnClickListener {
                onboardingViewModel.onNext(OnboardingState.HealthConcern)
            }
        }
    }
}