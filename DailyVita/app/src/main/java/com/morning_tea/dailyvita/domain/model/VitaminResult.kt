package com.morning_tea.dailyvita.domain.model

import com.google.gson.annotations.SerializedName

data class VitaminResult(
    @SerializedName("health_concerns")
    val healthConcerns: List<HealthConcern>,

    @SerializedName("diets")
    val diets: List<Diet>,

    @SerializedName("is_daily_exposure")
    val isDailyExposure: Boolean,

    @SerializedName("is_smoke")
    val isSmoke: Boolean,

    @SerializedName("alcohol")
    val alcohol: String,

    @SerializedName("allergies")
    val allergies: List<Allergy>
)
