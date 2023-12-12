package com.example.islamiappxml.home.sebha

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.islamiappxml.Constants
import com.example.islamiappxml.R
import com.example.islamiappxml.databinding.FragmentSebhaBinding


class SebhaFragment : Fragment() {
    lateinit var viewBinding: FragmentSebhaBinding
    var counter : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentSebhaBinding.inflate(inflater, container, false)
        return viewBinding.root
    }
//    val imgSebhaBody = viewBinding.ivBodySebha
//    val counterTv = viewBinding.tsCounter
//    val zekrTv = viewBinding.zekr

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewBinding.tsCounter.text = "$counter"
        viewBinding.zekr.text=Constants.SUBHAN_ALLAH
        viewBinding.ivBodySebha.setOnClickListener {
            onSebhaClicked()
        }
    }

    private fun onSebhaClicked() {
        viewBinding.ivBodySebha.rotation = viewBinding.ivBodySebha.rotation+5
        counter++
        viewBinding.tsCounter.text = "$counter"

        if(counter == 34){
            when (viewBinding.zekr.text) {
                Constants.SUBHAN_ALLAH -> {
                    viewBinding.zekr.text= Constants.ALHAMEDUILLAH
                    counter = 0
                    viewBinding.tsCounter.text="$counter"
                }
                Constants.ALHAMEDUILLAH -> {
                    viewBinding.zekr.text= Constants.ALLAH_AKBAR
                    counter = 0
                    viewBinding.tsCounter.text="$counter"
                }
                Constants.ALLAH_AKBAR -> {
                    viewBinding.zekr.text = Constants.ASTGFER_ALLAH
                    counter = 0
                    viewBinding.tsCounter.text = "$counter"
                }
                Constants.ASTGFER_ALLAH -> {
                    viewBinding.zekr.text = Constants.SUBHAN_ALLAH
                    counter = 0
                    viewBinding.tsCounter.text = "$counter"
                }
            }
        }
        }
    }


