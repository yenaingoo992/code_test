package com.morning_tea.dailyvita.domain.model

data class Allergy(
    val id: Int,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}