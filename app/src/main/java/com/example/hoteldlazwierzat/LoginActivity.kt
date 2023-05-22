package com.example.hoteldlazwierzat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import com.example.hoteldlazwierzat.ViewModels.LoginViewModel
import com.example.hoteldlazwierzat.data.Entities.Client
import com.example.hoteldlazwierzat.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding

    private val loginVM by viewModels<LoginViewModel>()

    private val userNameErrorMessage = "Username must be between 3 and 24 characters long"
    private val passwordErrorMessage = "Password must be between 8-16 characters long\n" +
                                        "Password must contain at least 1 lower case character\n" +
                                        "Password must contain at least 1 upper case character"
    //index 0 is error message for username
    //index 1 is error message for password
    private val errorMessages = mutableListOf("","")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.UserName.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                binding.btnLogin.isClickable = CheckUsernameForLength() && binding.UserPassword.text.isNotBlank()
            }
        })
        binding.UserPassword.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){}

            override fun afterTextChanged(p0: Editable?) {
                binding.btnLogin.isClickable = CheckUsernameForLength() && binding.UserPassword.text.isNotBlank()
            }
        })
        binding.btnLogin.setOnClickListener(){
            CoroutineScope(Dispatchers.IO).launch {
                if (CheckIfUserExists()) {
                    if (CheckIfPasswordsMatch()) {
                        loginVM.loggedClient = loginVM.GetClientByUserName(binding.UserName.text.toString())
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                applicationContext,
                                "successfully logged into ${loginVM.loggedClient!!.username}",
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.loggedClient.text = loginVM.loggedClient.toString()
                        }
                    }
                }
            }
        }
        //the line beneath HAS to be called after setting onClickListener(), because for some reason setting the listener
        //sets the button back to being clickable
        //The more you know
        binding.btnLogin.isClickable = false
    }
    private fun CheckUsernameForLength(): Boolean{
        if(binding.UserName.text.length in 3..24){
            errorMessages[0] = ""
            UpdateErrorMessage()
            return true
        }else {
            errorMessages[0] = userNameErrorMessage
            UpdateErrorMessage()
            return false
        }
    }
    private fun UpdateErrorMessage(){
        binding.ErrorMessage.text = ""
        var temp =""
        for(error in errorMessages) temp +=error + "\n"
        binding.ErrorMessage.text=temp
    }
    private suspend fun CheckIfUserExists() : Boolean{
        var allUsernames = loginVM.GetAllUsernames()
        if(allUsernames.contains(binding.UserName.text.toString())){
            return true
        }else{
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    applicationContext,
                    "User not found",
                    Toast.LENGTH_SHORT
                ).show()
            }
            return false
        }
    }
    private suspend fun CheckIfPasswordsMatch() : Boolean{
        var tempUser = loginVM.GetClientByUserName(binding.UserName.text.toString()) ?: null
        if(tempUser!!.password == binding.UserPassword.text.toString()){
            errorMessages[1] = ""
            withContext(Dispatchers.Main) {
                UpdateErrorMessage()
            }
            return true
        }else{
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    applicationContext,
                    "Invalid password",
                    Toast.LENGTH_SHORT
                ).show()
                errorMessages[1] = passwordErrorMessage
                UpdateErrorMessage()
            }
            return false
        }
    }
}