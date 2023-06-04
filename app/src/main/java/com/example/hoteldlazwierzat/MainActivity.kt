package com.example.hoteldlazwierzat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.hoteldlazwierzat.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navigationView: BottomNavigationView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        navigationView = binding.bottomNavigation
        supportFragmentManager.beginTransaction().replace(R.id.fl_wrapper, HomeFragment()).commit()
        navigationView.selectedItemId = R.id.nav_home

        navigationView.setOnNavigationItemSelectedListener { item ->
            var fragment: Fragment? = null
            var previewIntent = Intent(applicationContext,PreviewActivity::class.java)
            when (item.itemId) {
                R.id.nav_home -> fragment = HomeFragment()
                R.id.nav_calendar -> fragment = CalendarFragment()
                R.id.nav_account -> fragment = AccountFragment()
                R.id.nav_preview -> startActivity(previewIntent)
                    //fragment = PreviewFragment()
            }


            if (fragment != null) {
                supportFragmentManager.beginTransaction().replace(R.id.fl_wrapper, fragment).commit()
                return@setOnNavigationItemSelectedListener true
            }
            return@setOnNavigationItemSelectedListener false
        }
    }
}