package com.vortex.soft.validation.validations

class LambdaValidation(val lambda: () -> String, val errorLambda: (String) -> Unit) : Validation() {

    override fun getValue() = lambda()
    override fun setError( errors: ArrayList<String>) {
        errors.firstOrNull()?.let { errorLambda(it) }
    }

    override fun clearError() {}
}