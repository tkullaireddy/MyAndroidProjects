package com.example.suduko

import android.text.InputFilter
import android.text.Spanned

class InputFilterMinMax(private val min: Int, private val max: Int) : InputFilter {

    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence? {
        val newText = dest.subSequence(0, dstart).toString() + source + dest.subSequence(dend, dest.length)
        if (newText.isBlank()) return null
        try {
            val value = newText.toInt()
            if (value < min || value > max) return ""
        } catch (e: NumberFormatException) {
            return ""
        }
        return null
    }
}