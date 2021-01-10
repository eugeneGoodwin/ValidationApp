package com.vortex.soft.validation

import com.vortex.soft.validation.interfaces.ErrorMessage
import com.vortex.soft.validation.mode.Mode
import com.vortex.soft.validation.validations.Validation

class Validator private constructor(val listener: OnValidateListener) {

    private var mode = Mode.ONLY_FIRST_RULE
    private var validations = ArrayList<Validation>()

    interface OnValidateListener {
        fun onValidateSuccess(values: List<String>)
        fun onValidateFailed(errors: List<String>)
    }

    fun setMode(mode: Mode): Validator {
        this.mode = mode
        return this
    }

    fun validate(vararg validations: Validation) {
        var isOverallValid = true
        var isValid = false
        val values = ArrayList<String>()
        val errors = ArrayList<String>()

        this.validations.clear()
        this.validations.addAll(validations)
        clear()

        for (validation in validations) {
            val value = validation.getValue()
            value.let {
                val isCurrentValueValid = validate(it, validation, errors)
                if (isCurrentValueValid) {
                    isValid = true
                    values.add(it)
                } else {
                    isOverallValid = false
                    isValid = false
                }
            }
            if (mode == Mode.ONLY_FIRST_RULE && isValid == false && isOverallValid == false) break
        }
        if (isValid && isOverallValid) listener.onValidateSuccess(values)
        else  listener.onValidateFailed(errors)
    }

    fun clear() = clearAllErrors()
    private fun clearAllErrors() =  validations.forEach { it.clearError() }

    private fun validate(value: String, validation: Validation, errors: ArrayList<String>): Boolean {
        var isCurrentValueValid = validateRules(value, validation, errors)
        if (isCurrentValueValid) {
            isCurrentValueValid = validateConditions(value, validation, errors)
        }

        return isCurrentValueValid
    }

    private fun validateRules(value: String, validation: Validation, errors: ArrayList<String>): Boolean {
        for (baseRule in validation.baseRules) {
            if (!baseRule.validate(value)) {
                showErrorMessage(validation, baseRule, errors)
                return false
            }
        }
        return true
    }

    private fun validateConditions(value: String, validation: Validation, errors: ArrayList<String>): Boolean {
        for (condition in validation.conditions) {
            if (!condition.validate(value)) {
                showErrorMessage(validation, condition, errors)
                return false
            }
        }
        return true
    }

    private fun showErrorMessage(validation: Validation, errorMessage: ErrorMessage, errors: ArrayList<String>) {
        if (errorMessage.isErrorAvailable) {
            val error = errorMessage.getErrorMessage()
            errors.add(error)
        }
        validation.setError(errors)
    }

    companion object {
        fun with(listener: OnValidateListener): Validator = Validator(listener)
    }
}