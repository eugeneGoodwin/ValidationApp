package com.vortex.soft.validation.validations

import android.text.InputFilter
import android.widget.EditText
import com.vortex.soft.validation.conditions.Condition
import com.vortex.soft.validation.filters.MaxFilter
import com.vortex.soft.validation.filters.RegexFilter
import com.vortex.soft.validation.rules.BaseRule
import com.vortex.soft.validation.rules.common.MaxLengthRule
import com.vortex.soft.validation.rules.regex.RegexRule

class EditTextValidation(val editText: EditText) : Validation() {

    override fun getValue() = editText.text.toString()
    override fun setError(errors: ArrayList<String>) {
        errors.firstOrNull()?.let { editText.error = it }
    }

    override fun clearError() {
        editText.error = null
    }

    fun addRegexRuleWithFilter(regexRule: RegexRule): EditTextValidation {
        addFilter(RegexFilter(regexRule))
        add(regexRule)
        return this
    }

    fun addMaxLengthRuleWithFilter(maxRule: MaxLengthRule): EditTextValidation {
        addFilter(MaxFilter(maxRule))
        add(maxRule)
        return this
    }

    private fun addFilter(inputFilter: InputFilter) {
        var filters = editText.filters.toMutableList()
        filters.add(inputFilter)
        editText.filters = filters.toTypedArray()
    }

    public override fun add(baseRule: BaseRule): EditTextValidation { super.add(baseRule); return this }
    public override fun add(condition: Condition): EditTextValidation { super.add(condition); return this }
}