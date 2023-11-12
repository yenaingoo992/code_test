package com.morning_tea.dailyvita.ui.custom

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.morning_tea.dailyvita.R
import com.morning_tea.dailyvita.databinding.LayoutQuestionTextViewBinding

class QuestionTextView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var binding: LayoutQuestionTextViewBinding

    init {
        binding = LayoutQuestionTextViewBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.QuestionTextView)
        binding.apply {
            val title = attributes.getString(R.styleable.QuestionTextView_title)
            val isRequired = attributes.getBoolean(R.styleable.QuestionTextView_isRequired, false)
            if (isRequired) {
                val coloredText = getColoredAsterisk("$title *")
                tvTitle.text = coloredText
            }
        }
        attributes.recycle()
    }

    private fun getColoredAsterisk(text: String): CharSequence {
        val builder = SpannableStringBuilder(text)

        // Find the position of the asterisk in the text
        val asteriskPosition = text.indexOf('*')

        // Set the color of the asterisk (for example, red)
        val asteriskColor = Color.RED

        // Apply the color to the asterisk
        builder.setSpan(
            ForegroundColorSpan(asteriskColor),
            asteriskPosition,
            asteriskPosition + 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return builder
    }
}