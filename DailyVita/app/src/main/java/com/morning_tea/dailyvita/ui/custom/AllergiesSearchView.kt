package com.morning_tea.dailyvita.ui.custom

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textview.MaterialTextView
import com.morning_tea.dailyvita.R
import com.morning_tea.dailyvita.domain.model.Allergy
import com.tokenautocomplete.TokenCompleteTextView

class AllergiesSearchView(context: Context, attrs: AttributeSet): TokenCompleteTextView<Allergy>(context, attrs) {

    override fun defaultObject(completionText: String): Allergy? {
        return null
    }

    override fun getViewForObject(obj: Allergy): View {
        val layoutInflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val tv = layoutInflater.inflate(R.layout.layout_allergy_token, parent as ViewGroup, false) as MaterialTextView
        tv.text = obj.name
        return tv
    }

    override fun shouldIgnoreToken(token: Allergy): Boolean {
        return objects.contains(token)
    }
}