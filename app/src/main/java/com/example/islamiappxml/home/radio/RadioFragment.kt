package com.example.islamiappxml.home.radio

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.islamiappxml.R
import com.example.islamiappxml.databinding.FragmentRadioBinding
import com.example.islamiappxml.model.radioRespons.ApiManager
import com.example.islamiappxml.model.radioRespons.RadioResponse
import com.example.islamiappxml.model.radioRespons.RadiosItem
import com.example.islamiappxml.player.PlayerService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RadioFragment : Fragment() {
    lateinit var service: PlayerService
    var bound = false
    private val connection = object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, mBinder: IBinder?) {
            val binder = mBinder as PlayerService.MyBinder
            service = binder.getService()
            bound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            bound = false
        }

    }

    lateinit var viewBinding : FragmentRadioBinding
    val radioAdapter = RadioAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentRadioBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.radioRecyclerView.adapter = radioAdapter
        radioAdapter.onPlayClickListner = OnItemRadioClick { pos, radio ->
            startRadioService(radio)
        }
        radioAdapter.onStopClickListner = OnItemRadioClick { pos, radio ->
            stopRadioService()
        }
        getChannelsFromApi()
    }

    private fun stopRadioService() {
        if (bound)
            service.pauseMadiaPlayer()
    }

    private fun startRadioService(radio: RadiosItem?) {
        if (bound)
            service.srartMadiaPlayer(radio?.url!!,radio?.name!!)

    }

    private fun getChannelsFromApi() {
        ApiManager.getApi().getRadioChanels()
            .enqueue(object : Callback<RadioResponse>{
                override fun onResponse(
                    call: Call<RadioResponse>,
                    response: Response<RadioResponse>
                ) {
                    val channels = response.body()?.radios
                    radioAdapter.changeData(channels?: listOf())
                }

                override fun onFailure(call: Call<RadioResponse>, t: Throwable) {
                    Toast.makeText(activity, t.localizedMessage?:"error", Toast.LENGTH_SHORT).show()
                }

            })
    }

    override fun onStart() {
        super.onStart()
        startService()
        bindService()
    }

    override fun onStop() {
        super.onStop()
        activity?.unbindService(connection)
    }

    private fun bindService() {
        val intent = Intent(requireContext(),PlayerService::class.java)
        activity?.bindService(intent,connection,Context.BIND_AUTO_CREATE)
    }

    private fun startService() {
        val intent = Intent(requireContext(),PlayerService::class.java)
        activity?.startService(intent)
    }

}