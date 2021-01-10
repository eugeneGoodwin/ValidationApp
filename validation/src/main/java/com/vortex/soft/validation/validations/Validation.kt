package com.vortex.soft.validation.validations

import com.vortex.soft.validation.conditions.Condition
import com.vortex.soft.validation.rules.BaseRule

abstract class Validation {

    val baseRules: MutableList<BaseRule> = ArrayList()
    val conditions: MutableList<Condition> = ArrayList()

    abstract fun getValue(): String
    abstract fun setError(errors: ArrayList<String>)
    abstract fun clearError()

    protected open fun add(baseRule: BaseRule): Validation {
        baseRules.add(baseRule)
        return this
    }

    protected open fun add(condition: Condition): Validation {
        conditions.add(condition)
        return this
    }
}