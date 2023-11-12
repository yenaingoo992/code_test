package com.morning_tea.dailyvita.domain.model

import com.google.gson.annotations.SerializedName

data class Diet(
    val id: Int,
    val name: String,
    @SerializedName("tool_tip")
    val toolTip: String
) {
    var isChecked: Boolean = false
}