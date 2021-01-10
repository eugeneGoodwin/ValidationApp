package com.vortex.soft.validation.rules.regex

class CirillicRule : RegexRule {

    constructor() : super(CIRILLIC_REGEX, "Value does not match cirillic regex")
    constructor(errorMessage: String) : super(CIRILLIC_REGEX, errorMessage)

    companion object {
        private const val CIRILLIC_REGEX = """[-\u0400-\u04FF']*"""
    }
}