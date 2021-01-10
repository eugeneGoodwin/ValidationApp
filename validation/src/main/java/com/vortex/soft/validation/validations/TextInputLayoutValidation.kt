package com.vortex.soft.validation.validations

import android.text.InputFilter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.vortex.soft.validation.conditions.Condition
import com.vortex.soft.validation.filters.MaxFilter
import com.vortex.soft.validation.filters.RegexFilter
import com.vortex.soft.validation.rules.BaseRule
import com.vortex.soft.validation.rules.common.MaxLengthRule
import com.vortex.soft.validation.rules.regex.RegexRule

class TextInputLayoutValidation(val textInput: TextInputEditText, val textInputLayout: TextInputLayout) : Validation() {

    override fun getValue() = textInput.text.toString()
    override fun setError(errors: ArrayList<String>) {
        textInputLayout.isErrorEnabled = true
        errors.firstOrNull()?.let { textInputLayout.error = it }
    }

    override fun clearError() {
        textInputLayout.error = null
        textInputLayout.isErrorEnabled = false
    }

    fun addRegexRuleWithFilter(regexRule: RegexRule): TextInputLayoutValidation {
        addFilter(RegexFilter(regexRule))
        add(regexRule)
        return this
    }

    fun addMaxLengthRuleWithFilter(maxRule: MaxLengthRule): TextInputLayoutValidation {
        addFilter(MaxFilter(maxRule))
        add(maxRule)
        return this
    }

    private fun addFilter(inputFilter: InputFilter) {
        var filters = textInput.filters.toMutableList()
        filters.add(inputFilter)
        textInput.filters = filters.toTypedArray()
    }

    public override fun add(baseRule: BaseRule): TextInputLayoutValidation { super.add(baseRule); return this }
    public override fun add(condition: Condition): TextInputLayoutValidation { super.add(condition); return this }
}