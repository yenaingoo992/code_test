package com.morning_tea.dailyvita.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.morning_tea.dailyvita.R
import com.morning_tea.dailyvita.databinding.FragmentAdditionalInfoBinding
import com.morning_tea.dailyvita.ui.viewmodel.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AdditionalInfoFragment : Fragment(R.layout.fragment_additional_info) {

    companion object {
        const val TAG = "AdditionalInfoFragment"
    }

    private lateinit var binding: FragmentAdditionalInfoBinding

    private val onboardingViewModel: OnboardingViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAdditionalInfoBinding.bind(view)

        binding.apply {
            btnGetResult.setOnClickListener {
                val isSunExposureValid = rgDailyExposure.isValid()
                errorExposure.isVisible = !isSunExposureValid
                var isDailyExposure = false
                if (isSunExposureValid) {
                    isDailyExposure = rdExposureYes.isChecked
                }
                val isSmokeValid = rgSmoke.isValid()
                errorSmoke.isVisible = !isSmokeValid
                var isSmoke = false
                if (isSmokeValid) {
                    isSmoke = rdSmokeYes.isChecked
                }
                val isAlcoholicValid = rgAlcoholic.isValid()
                errorAlcohol.isVisible = !isAlcoholicValid
                var alcohol = ""
                if (isAlcoholicValid) {
                    alcohol = when (rgAlcoholic.checkedRadioButtonId) {
                        R.id.rd_zero_to_one -> {
                            rdZeroToOne.text.toString()
                        }

                        R.id.rd_two_to_five -> {
                            rdTwoToFive.text.toString()
                        }

                        else -> {
                            rdFivePlus.text.toString()
                        }
                    }
                }
                onboardingViewModel.getVitaminResult(
                    isDailyExposure = isDailyExposure,
                    isSmoke = isSmoke,
                    alcohol = alcohol
                )
            }
        }

        onboardingViewModel.result.observe(viewLifecycleOwner) {
            Timber.i(it)
        }
    }
}

fun RadioGroup.isValid(): Boolean = this.checkedRadioButtonId != -1