package com.example.hoteldlazwierzat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.hoteldlazwierzat.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var passwordErrorMessages = mutableListOf<String>()
    private var usernameErrorMessages = mutableListOf<String>()

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
                     //   passwordErrorMessages.add(passwordNotIdenticalMessage)
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

        }
    }
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


}