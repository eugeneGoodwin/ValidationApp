package com.vortex.soft.validation.filters

import android.text.InputFilter
import android.text.Spanned
import com.vortex.soft.validation.rules.regex.RegexRule

class RegexFilter(val regexRule: RegexRule) : InputFilter {
    override fun filter(
        source: CharSequence, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int
    ): CharSequence? {
        val replacement = source.subSequence(start, end).toString()
        return if(regexRule.validate(replacement)) null else dest.toString().substring(dstart, dend)
    }
}