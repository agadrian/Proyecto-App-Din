package com.es.interfazproyectoapp.screens.registerScreen

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class RegisterScreenViewModel : ViewModel() {

    private val _name = MutableStateFlow("")
    val name = _name

    private val _email = MutableStateFlow("")
    val email = _email

    private val _password = MutableStateFlow("")
    val password = _password


    private val _isValidName = MutableStateFlow(false)
    val isValidName= _isValidName

    private val _isValidEmail = MutableStateFlow(false)
    val isValidEmail = _isValidEmail

    private val _isValidPassword = MutableStateFlow(false)
    val isValidPassword = _isValidPassword



    fun onNameChange(newName: String) {
        _name.value = newName
        _isValidName.value = newName.isNotEmpty()
    }

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        _isValidEmail.value = Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        _isValidPassword.value = newPassword.length > 8
    }

    private val _isVisiblePassword = MutableStateFlow(false)
    val isVisiblePassword = _isVisiblePassword

    fun onPasswordVisibleChange(){
        _isVisiblePassword.value = !_isVisiblePassword.value
    }



    fun clearInputs() {
        _name.value = ""
        _email.value = ""
        _password.value = ""
    }


    fun validateForm(): Boolean {
        return _isValidName.value && _isValidEmail.value && _isValidPassword.value
    }

}