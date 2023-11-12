package com.morning_tea.dailyvita.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.morning_tea.dailyvita.R
import com.morning_tea.dailyvita.databinding.FragmentOnboardingBinding
import com.morning_tea.dailyvita.ui.viewmodel.OnboardingState
import com.morning_tea.dailyvita.ui.viewmodel.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {

    private lateinit var binding: FragmentOnboardingBinding
    private val onboardingViewModel: OnboardingViewModel by activityViewModels()

    private lateinit var fragmentManager : FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                when (fragmentManager.findFragmentById(R.id.fc_onboarding)) {
                    is AdditionalInfoFragment, is GetStartedFragment -> {
                        requireActivity().finish()
                    }
                    else -> {
                        onboardingViewModel.onPrevious()
                    }
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentOnboardingBinding.bind(view)
        fragmentManager = requireActivity().supportFragmentManager
        onboardingViewModel.state.observe(viewLifecycleOwner) {
            handleScreenState(it)
        }

        onboardingViewModel.nextStep.observe(viewLifecycleOwner) {
            binding.stepProgress.progress = it
        }

        onboardingViewModel.popBack.observe(viewLifecycleOwner) {
            fragmentManager.popBackStack()
        }
    }

    private fun handleScreenState(state: OnboardingState) {
        when (state) {
            OnboardingState.GetStarted -> {
                changeFragment(GetStartedFragment(), GetStartedFragment.TAG)
            }

            OnboardingState.HealthConcern -> {
                changeFragment(HealthConcernsFragment(), HealthConcernsFragment.TAG)
            }

            OnboardingState.Diets -> {
                changeFragment(DietsFragment(), DietsFragment.TAG)
            }
            OnboardingState.Allergies -> {
                changeFragment(AllergiesFragment(), AllergiesFragment.TAG)
            }
            OnboardingState.AdditionalInfo -> {
                changeFragment(AdditionalInfoFragment(), AdditionalInfoFragment.TAG)
            }
        }
    }

    private fun changeFragment(fragment: Fragment, tag: String) {
        fragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fc_onboarding, fragment, tag)
            addToBackStack(tag)
        }
    }
}