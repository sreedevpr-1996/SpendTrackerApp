package com.example.spendtracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendtracker.database.tables.UserEntity
import com.example.spendtracker.repositories.STUserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class STCreateUserViewModel @Inject constructor(private val userRepo: STUserRepo) : ViewModel() {
    private val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
    private lateinit var lastNameValidationFailed: String
    private lateinit var firstNameValidationFailed: String
    private lateinit var balanceValidationFailed: String
    private lateinit var emailValidationFailed: String
    val failedValidations = mutableListOf<String>()
    private val _snackBarMessage = MutableSharedFlow<String>()
    val snackBarMessage = _snackBarMessage.asSharedFlow()
    fun createUser(userEntity: UserEntity?, onComplete: () -> Unit) {
        if (userEntity != null)
            viewModelScope.launch {
                userRepo.insertUser(userEntity)
                onComplete()
            }
    }

    fun createUserEntity(
        firstName: String,
        lastName: String,
        email: String,
        balance: String,
    ): UserEntity? {
        if (checkForValidation(firstName, lastName, email, balance))
            return UserEntity(
                firstName = firstName,
                lastName = lastName,
                email = email,
                balance = balance.toDoubleOrNull() ?: 0.0,
                timestamp = System.currentTimeMillis()
            )
        else {
            val validationMessage = buildValidationMessage()
            showSnackBar(validationMessage)

            return null
        }
    }

    fun showSnackBar(message: String) {
        viewModelScope.launch {
            _snackBarMessage.emit(message)
        }
    }

    private fun buildValidationMessage(): String {
        return when (failedValidations.size) {
            1 -> failedValidations[0]
            2 -> "${failedValidations[0]} and ${failedValidations[1]}"
            else -> {
                val allButLast = failedValidations.dropLast(1).joinToString(", ")
                "$allButLast and ${failedValidations.last()}"
            }
        }
    }

    private fun checkForValidation(
        firstName: String,
        lastName: String,
        email: String,
        balance: String,
    ): Boolean {
        failedValidations.clear()
        val isFirstNameValid = validateFirstName(firstName)
        val isLastNameValid = validateLastName(lastName)
        val isEmailValid = validateEmail(email)
        val isBalanceValid = validateBalance(balance)
        return isFirstNameValid && isLastNameValid && isEmailValid && isBalanceValid
    }


    private fun validateBalance(balance: String): Boolean {
        if (balance.toDoubleOrNull() != null)
            return true
        else {
            failedValidations.add(balanceValidationFailed)
            return false
        }
    }

    private fun validateEmail(email: String): Boolean {
        if (email.isEmpty() || !emailRegex.matches(email)) {
            failedValidations.add(emailValidationFailed)
            return false
        }
        return true

    }

    private fun validateLastName(lastName: String): Boolean {
        if (lastName.isEmpty()) {
            failedValidations.add(lastNameValidationFailed)
            return false
        }
        return true
    }

    private fun validateFirstName(firstName: String): Boolean {
        if (firstName.isEmpty()) {
            failedValidations.add(firstNameValidationFailed)
            return false
        }
        return true
    }

    fun setErrorMessages(
        emailValidationFailed: String,
        firstNameValidationFailed: String,
        lastNameValidationFailed: String,
        balanceValidationFailed: String,
    ) {
        this.emailValidationFailed = emailValidationFailed
        this.balanceValidationFailed = balanceValidationFailed
        this.firstNameValidationFailed = firstNameValidationFailed
        this.lastNameValidationFailed = lastNameValidationFailed
    }

}