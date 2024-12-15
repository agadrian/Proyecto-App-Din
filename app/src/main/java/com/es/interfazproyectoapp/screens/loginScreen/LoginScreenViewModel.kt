package com.es.interfazproyectoapp.screens.loginScreen

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class LoginScreenViewModel : ViewModel() {


    private val _email = MutableStateFlow("")
    val email = _email

    private val _isValidEmail = MutableStateFlow(false)
    val isValidEmail = _isValidEmail


    private val _password = MutableStateFlow("")
    val password = _password

    private val _isValidPassword = MutableStateFlow(false)
    val isValidPassword = _isValidPassword


    private val _isVisiblePassword = MutableStateFlow(false)
    val isVisiblePassword = _isVisiblePassword


    fun onEmailChange(newEmail: String){
        _email.value = newEmail
        _isValidEmail.value = Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()
    }


    fun onPasswordChange(newPassword: String){
        _password.value = newPassword
        _isValidPassword.value = newPassword.length > 8
    }

    fun onPasswordVisibleChange(){
        _isVisiblePassword.value = !_isVisiblePassword.value
    }

    fun validateLogin(correctEmail: String, correctPassword: String): Boolean{
        return (_email.value == correctEmail && _password.value == correctPassword)
    }

    fun clearInputs(){
        _email.value = ""
        _password.value = ""
    }


}