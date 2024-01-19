package com.example.islamiappxml.home.hadith

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.islamiappxml.Constants
import com.example.islamiappxml.R
import com.example.islamiappxml.databinding.FragmentHadethBinding
import com.example.islamiappxml.suraDetails.SuraDetailsActivity


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
        initViews()
    }

    private fun initViews() {
        adapter = HadethRecyclerAdapter(hadethList)
        viewBinding.hadethRecycler.adapter = adapter
        adapter.onItemHadethClickListner = object : HadethRecyclerAdapter.OnItemHadethClickListner{
            override fun onItemHadethClickListner(pos: Int, Item: Hadeth) {
                val intent = Intent(context, HadethDetailsActivity::class.java)
                intent.putExtra(Constants.EXTRA_HADETH_NAME, Item.title)
                intent.putExtra(Constants.EXTRA_HADETH_CONTENT, Item.content)
                startActivity(intent)
            }

        }
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