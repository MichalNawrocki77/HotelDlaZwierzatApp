package com.example.hoteldlazwierzat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.hoteldlazwierzat.ViewModels.RegisterViewModel
import com.example.hoteldlazwierzat.data.Entities.Client
import com.example.hoteldlazwierzat.data.DAO.ClientDAO
import com.example.hoteldlazwierzat.data.Repositories.ClientRepo
import com.example.hoteldlazwierzat.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var passwordErrorMessages = mutableListOf<String>()
    private var usernameErrorMessages = mutableListOf<String>()
    //ViewModel
    private val registerVM by viewModels<RegisterViewModel>()

    //I initialized this one singular message here, since it is assigned in two different functions
    private val passwordNotIdenticalMessage = "Passwords are not identical"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.UserName.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){}

            override fun afterTextChanged(p0: Editable?) {
                if(binding.UserName.text.isNotBlank()){
                    CheckUsernameForLength()
                    updateErrorMessageView()
                }
            }

        })
        binding.UserPassword1.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (binding.UserPassword1.text.isNotBlank()) {
                    if (CheckIfPasswordsAreIdentical()) {
                        CeckPasswordForLength()
                        CheckPasswordForLower()
                        CheckPasswordForUpper()

                        updateErrorMessageView()
                    } else {
                        updateErrorMessageView()
                    }
                }
                else{
                    if(binding.UserPassword2.text.isBlank()) passwordErrorMessages.clear()
                    updateErrorMessageView()
                }
            }

        })
        binding.UserPassword2.addTextChangedListener(object:TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (binding.UserPassword2.text.isNotBlank()) {
                    if (CheckIfPasswordsAreIdentical()) {
                        CeckPasswordForLength()
                        CheckPasswordForLower()
                        CheckPasswordForUpper()

                        updateErrorMessageView()
                    } else {
                        updateErrorMessageView()
                    }
                }else{
                    if(binding.UserPassword1.text.isBlank()) passwordErrorMessages.clear()
                    updateErrorMessageView()
                }
            }
        })
        binding.signUpButton.setOnClickListener(){
            Log.d("REGISTER","Sign up button clicked")
            val newClient = Client(username = binding.UserName.text.toString(),
                                   password = binding.UserPassword1.text.toString())
            registerVM.InsertClient(newClient)
            val loginIntent = Intent(applicationContext,LoginActivity::class.java)
            startActivity(loginIntent)
        }
        //the line beneath HAS to be called after setting onClickListener(), because for some reason setting the listener
        //sets the button back to being clickable
        //The more you know
        binding.signUpButton.isClickable = false
    }
    //Password and login validation
    private fun CheckIfPasswordsAreIdentical() : Boolean{
        //I did not initialized tempErrorMessage like in every other "CheckPassword" function
        // because it's initialized as a class variable (for visibility reason, as it's also used in TextChangedListeners)
        if(binding.UserPassword1.text.toString() == binding.UserPassword2.text.toString()){
            if(passwordErrorMessages.contains(passwordNotIdenticalMessage)) passwordErrorMessages.remove(passwordNotIdenticalMessage)
            return true
        }
        else{
            passwordErrorMessages.clear()
            passwordErrorMessages.add(passwordNotIdenticalMessage)
            return false
        }
    }
    private fun CeckPasswordForLength(): Boolean{
        val tempErrorMessage = "Password must be between 8-16 characters long"
        if(binding.UserPassword1.text.length in 8..16){
            if(passwordErrorMessages.contains(tempErrorMessage)) passwordErrorMessages.remove(tempErrorMessage)
            return true
        } else{
            passwordErrorMessages.add(tempErrorMessage)
            return false
        }
    }
    private fun CheckPasswordForUpper(): Boolean {
        val tempErrorMessage = "Password must contain at least 1 upper case character"
        if (binding.UserPassword1.text.toString().containsUpperCase()){
            if(passwordErrorMessages.contains(tempErrorMessage)) passwordErrorMessages.remove(tempErrorMessage)
            return true
        }else{
            passwordErrorMessages.add(tempErrorMessage)
            return false
        }
    }
    private fun CheckPasswordForLower() : Boolean{
        val tempErrorMessage = "Password must contain at least 1 lower case character"
        if(binding.UserPassword1.text.toString().containsLowerCase()){
            if(passwordErrorMessages.contains(tempErrorMessage)) passwordErrorMessages.remove(tempErrorMessage)
            return true
        }
        else{
            passwordErrorMessages.add(tempErrorMessage)
            return false
        }
    }
    private fun CheckUsernameForLength(): Boolean{
        val tempErrorMessage = "User name must be between 3-24 characters long"
        if(binding.UserName.text.length in 3..24){
            if(usernameErrorMessages.contains(tempErrorMessage)) usernameErrorMessages.remove(tempErrorMessage)
            return true
        }else{
            if(!usernameErrorMessages.contains(tempErrorMessage)) usernameErrorMessages.add(tempErrorMessage)
            return false
        }
    }

    fun String.containsUpperCase() : Boolean{
        for(word in this){
            if(word.isUpperCase()) {
                return true
            }
        }
        return false
    }
    fun String.containsLowerCase() : Boolean{
        for(word in this){
            if(word.isLowerCase()){
                return true
            }
        }
        return false
    }
    private fun updateErrorMessageView(){
        binding.ErrorMessage.text = ""
        var tempErrorMessage = ""
        for(error in usernameErrorMessages) tempErrorMessage += error+"\n"
        for(error in passwordErrorMessages) tempErrorMessage += error+"\n"
        binding.ErrorMessage.text = tempErrorMessage

        binding.signUpButton.isClickable = binding.ErrorMessage.text.isBlank()
    }

    //Registering user
    private fun RegisterUser(){
    }

}