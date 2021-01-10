package com.vortex.soft.validation.validations

import com.vortex.soft.validation.conditions.Condition
import com.vortex.soft.validation.rules.BaseRule
import kotlin.reflect.KProperty1
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.starProjectedType

class MemberClassValidation<T>(val ref: T, val property: KProperty1<T, *>, val error: (String) -> Unit) : Validation() {

    init {
        if(!property.returnType.isSubtypeOf(String::class.starProjectedType)) throw IllegalArgumentException("Return type not String")
    }
    override fun getValue() = property.get(ref) as String

    override fun setError( errors: ArrayList<String>) {
        errors.firstOrNull()?.let { error(it) }
    }
    override fun clearError() {}

    public override fun add(baseRule: BaseRule): MemberClassValidation<T> { super.add(baseRule); return this }
    public override fun add(condition: Condition): MemberClassValidation<T> { super.add(condition); return this }
}