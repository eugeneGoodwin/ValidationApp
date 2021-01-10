package com.vortex.soft.validation.rules.common

import com.vortex.soft.validation.rules.BaseRule

class MaxDigitRule : BaseRule {

    private var maxDigit: Int = 0

    constructor(max: Int) :
            super(String.format("Value must be more then %s", max)) {
        this.maxDigit = max
    }

    constructor(maxLength: Int, errorMessage: String) :
            super(errorMessage) {
        this.maxDigit = maxLength
    }

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value.toIntOrNull()?.let { it <= maxDigit } ?: false
        }
    }
}