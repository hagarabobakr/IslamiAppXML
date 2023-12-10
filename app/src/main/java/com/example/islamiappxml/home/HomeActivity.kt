package com.example.islamiappxml.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.islamiappxml.R
import com.example.islamiappxml.databinding.ActivityHomeBinding
import com.example.islamiappxml.home.hadith.HadethFragment
import com.example.islamiappxml.home.quran.QuranFragment
import com.example.islamiappxml.home.radio.RadioFragment
import com.example.islamiappxml.home.sebha.SebhaFragment

class HomeActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
        viewBinding.contentHome.homeBottomNav.selectedItemId = R.id.nav_quran
    }
fun initViews(){
    viewBinding.contentHome.homeBottomNav
        .setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_quran -> {
                    showFragment(QuranFragment())
                }
                R.id.nav_hadeth -> {
                    showFragment(HadethFragment())
                }
                R.id.nav_tasbeh -> {
                    showFragment(SebhaFragment())
                }
                R.id.nav_radio -> {
                    showFragment(RadioFragment())
                }
            }
            return@setOnItemSelectedListener true;
        }
}



fun showFragment(fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.fragment_container, fragment)
        .commit()
}
}