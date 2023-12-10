package com.example.islamiappxml.home.hadith

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.islamiappxml.R
import com.example.islamiappxml.databinding.FragmentHadethBinding


class HadethFragment : Fragment() {

    lateinit var viewBinding: FragmentHadethBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    viewBinding = FragmentHadethBinding.inflate(inflater,container,false)
    return viewBinding.root
    }
    lateinit var adapter :HadethRecyclerAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readHadethFile()
        adapter = HadethRecyclerAdapter(hadethList)
        viewBinding.hadethRecycler.adapter = adapter
    }
    val hadethList = mutableListOf<Hadeth>()
    fun readHadethFile() {
        val fileContent = requireActivity().assets.open("ahadeth.txt").bufferedReader().use {
            it.readText()
        }
        val allHadethList = fileContent.split("#");
        allHadethList.forEach { hadethContent ->
            val title = hadethContent.trim().substringBefore('\n')
            val content = hadethContent.trim().substringAfter('\n')
            val hadeth = Hadeth(title, content)
            hadethList.add(hadeth)
        }

    }


}