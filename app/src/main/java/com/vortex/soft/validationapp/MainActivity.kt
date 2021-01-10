package com.vortex.soft.validationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vortex.soft.validation.Validator
import com.vortex.soft.validation.rules.common.MaxLengthRule
import com.vortex.soft.validation.rules.common.NotEmptyRule
import com.vortex.soft.validation.rules.regex.CirillicRule
import com.vortex.soft.validation.rules.regex.LatinicRule
import com.vortex.soft.validation.validations.EditTextValidation
import com.vortex.soft.validation.validations.MemberClassValidation
import com.vortex.soft.validation.validations.TextInputLayoutValidation
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var validation: TextInputLayoutValidation
    lateinit var validation2: EditTextValidation
    lateinit var validation3: MemberClassValidation<TestClass>

    lateinit var test: TestClass

    val validatorEditText = Validator.with(object : Validator.OnValidateListener {
        override fun onValidateSuccess(values: List<String>) {
            Toast.makeText(applicationContext, "Validate EditText successfully", Toast.LENGTH_LONG).show()
        }

        override fun onValidateFailed(errors: List<String>) {
            Toast.makeText(applicationContext, "Validate EditText failed", Toast.LENGTH_LONG).show()
        }
    })

    val validatorMemberClass = Validator.with(object : Validator.OnValidateListener {
        override fun onValidateSuccess(values: List<String>) {
            Toast.makeText(applicationContext, "Validate Member successfully", Toast.LENGTH_LONG).show()
        }

        override fun onValidateFailed(errors: List<String>) {
            Toast.makeText(applicationContext, "Validate Member failed", Toast.LENGTH_LONG).show()
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //validate TextInput
        validation = TextInputLayoutValidation(testInputEditText, testTextInputLayout)
            .add(NotEmptyRule("error empty"))
            .addRegexRuleWithFilter(CirillicRule())
            .add(MaxLengthRule(8))

        buttonValidInputLayout.setOnClickListener { Validator.with(object : Validator.OnValidateListener {
            override fun onValidateSuccess(values: List<String>) {
                Toast.makeText(applicationContext, "Validate TextInput successfully", Toast.LENGTH_LONG).show()
            }

            override fun onValidateFailed(errors: List<String>) {
                Toast.makeText(applicationContext, "Validate TextInput failed", Toast.LENGTH_LONG).show()
            }
        }).validate(validation)
        }

        //validate EditText
        validation2 = EditTextValidation(testEditText)
            .add(NotEmptyRule("error empty"))
            .addRegexRuleWithFilter(LatinicRule())
            .addMaxLengthRuleWithFilter(MaxLengthRule(5))

        buttonValidEditText.setOnClickListener { validatorEditText.validate(validation2) }

        //validate member class
        test = TestClass("dsdjkfhsdjksfdj")
        validation3 = MemberClassValidation(test, TestClass::testField, { testError.text = it })
            .add(NotEmptyRule("error empty"))
            .add(MaxLengthRule(9))
            .add(CirillicRule("no cirillic"))

        testNameClass.text = TestClass::class.simpleName
        testMemberClass.text = TestClass::testField.name.plus(":")
        testValueMemberClass.text = test.testField
        buttonValidMemberClass.setOnClickListener { validatorMemberClass.validate(validation3) }
    }
}