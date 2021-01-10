package com.vortex.soft.validation.filters

import android.text.InputFilter
import android.text.Spanned
import com.vortex.soft.validation.rules.common.MaxLengthRule

class MaxFilter(val maxRule: MaxLengthRule) : InputFilter {
    override fun filter(
        source: CharSequence, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int
    ): CharSequence? {
        val replacement = source.subSequence(start, end).toString()
        val newVal = dest.toString().substring(0, dstart) + replacement + dest.toString().substring(dend, dest.toString().length)
        return if(maxRule.validate(newVal)) null else dest.toString().substring(dstart, dend)
    }
}