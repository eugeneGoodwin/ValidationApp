package com.vortex.soft.validation.interfaces

interface ErrorMessage {

    val isErrorAvailable: Boolean
        get() = getErrorMessage().isNotEmpty()

    fun getErrorMessage(): String
}