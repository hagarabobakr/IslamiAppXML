package com.example.islamiappxml.home.hadith

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.example.islamiappxml.Constants
import com.example.islamiappxml.R
import com.example.islamiappxml.databinding.ActivityHadethDetailsBinding

class HadethDetailsActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityHadethDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHadethDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val hadethTitle= intent.getStringExtra(Constants.EXTRA_HADETH_NAME)
        val hadethContent =intent.getStringExtra(Constants.EXTRA_HADETH_CONTENT)
        viewBinding.title.text= hadethTitle
        viewBinding.hadethContent.text= hadethContent
        initListeners()
        viewBinding.hadethContent.movementMethod = ScrollingMovementMethod()
    }

    private fun initListeners() {
        viewBinding.arrowBackIcon.setOnClickListener{
            finish()
        }
    }
}