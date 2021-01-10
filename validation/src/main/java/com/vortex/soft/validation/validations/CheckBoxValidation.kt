package com.vortex.soft.validation.validations

import android.widget.CheckBox
import com.vortex.soft.validation.rules.common.IsCheckedRule

class CheckBoxValidation(val checkBox: CheckBox, val error: (String) -> Unit) : Validation() {

    override fun getValue() = if(checkBox.isChecked) "true" else "false"
    override fun setError( errors: ArrayList<String>) {
        errors.firstOrNull()?.let { error(it) }
    }

    override fun clearError() {}

    fun add(isCheckedRule: IsCheckedRule): CheckBoxValidation {
        super.add(isCheckedRule)
        return this
    }
}