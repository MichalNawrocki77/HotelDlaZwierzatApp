package com.example.hoteldlazwierzat

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class AccountFragment : Fragment() {

    private lateinit var btn_login: Button
    private lateinit var btn_signUp: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        btn_login = view.findViewById(R.id.button_login)
        btn_signUp = view.findViewById(R.id.button_signup)
        btn_login.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
        btn_signUp.setOnClickListener {
            val intent = Intent(activity, RegisterActivity::class.java)
            startActivity(intent)
        }

        return view
    }

}