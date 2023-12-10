package com.example.islamiappxml.suraDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.islamiappxml.Constants
import com.example.islamiappxml.databinding.ActivitySuraDetailsBinding

class SuraDetailsActivity : AppCompatActivity() {
    lateinit var adapter: VersesRecyclerAdapter
    lateinit var viewBinding: ActivitySuraDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySuraDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        val title: String = intent.getStringExtra(Constants.EXTRA_TITLE) ?: ""
        val index: Int = intent.getIntExtra(Constants.EXTRA_INDEX, 0)

        viewBinding.titleTv.text = title
        viewBinding.backBtn.setOnClickListener {
            finish()
        }

        val verses = readSuraFile(index)
        adapter = VersesRecyclerAdapter(verses)
        viewBinding.content.versesRv.adapter = adapter

    }

    private fun readSuraFile(suraIndex: Int): List<String> {
        // read sura file from assets line by line
        val fileName = "" + (suraIndex + 1) + ".txt"
        val fileContent = assets.open(fileName).bufferedReader().use {
            it.readText()
        }
        return fileContent.split("\n")
    }
}