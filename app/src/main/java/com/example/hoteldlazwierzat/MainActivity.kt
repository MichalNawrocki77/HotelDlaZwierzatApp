package com.example.hoteldlazwierzat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        navigationView = findViewById(R.id.bottom_navigation)
        supportFragmentManager.beginTransaction().replace(R.id.fl_wrapper, HomeFragment()).commit()
        navigationView.selectedItemId = R.id.nav_home

        navigationView.setOnNavigationItemSelectedListener { item ->
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_home -> fragment = HomeFragment()
                R.id.nav_calendar -> fragment = CalendarFragment()
                R.id.nav_account -> fragment = AccountFragment()
            }


            if (fragment != null) {
                supportFragmentManager.beginTransaction().replace(R.id.fl_wrapper, fragment).commit()
                return@setOnNavigationItemSelectedListener true
            }
            return@setOnNavigationItemSelectedListener false
        }
    }
}