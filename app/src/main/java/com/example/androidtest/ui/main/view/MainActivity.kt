package com.example.androidtest.ui.main.view


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtest.R
import com.example.androidtest.data.api.ApiHelper
import com.example.androidtest.data.api.ApiInterface
import com.example.androidtest.data.api.ServiceGenerator
import com.example.androidtest.data.model.AllDataResponse

import com.example.androidtest.ui.base.ViewModelFactory
import com.example.androidtest.ui.main.adapter.MainAdapter
import com.example.androidtest.ui.main.viewmodel.MainViewModel
import com.example.androidtest.utils.App
import com.example.androidtest.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    val list = arrayListOf<AllDataResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        App.InternetCheck(object : App.InternetCheck.Consumer {
            override fun accept(internet: Boolean) {
                if (internet){
                    setupViewModel()
                    setupObserver()
                }else{
                    App.showToast(this@MainActivity,getString(R.string.noconnection))
                }

            }

        })

    }

    private fun setupUI() {
        recylerdetails.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf(),this@MainActivity)
        recylerdetails.adapter = adapter

    }

    private fun setupObserver() {

        mainViewModel.getHomeData().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { homedata -> renderList(homedata) }
                    recylerdetails.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recylerdetails.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE

                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(homedata:ArrayList<AllDataResponse>) {
        if (homedata.size>0){
            Log.e("",""+homedata);
            if (list.size>0){
                list.clear()
            }
            list.addAll(homedata)
            adapter.addData(list)
            adapter.notifyDataSetChanged()
        }

    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
                this@MainActivity,
                ViewModelFactory(ApiHelper(ServiceGenerator.getCurrentData()!!.create(ApiInterface::class.java)))
        ).get(MainViewModel::class.java)


    }

    /*fun getmyAds() {
        progressBar.visibility = View.VISIBLE
        val client = ServiceGenerator.getCurrentData()
        val apiService = client?.create(ApiInterface::class.java)
        disposable = apiService!!.getmyAds()!!
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ response -> onResponse(response!!)}, { t -> onFailure(t) })
    }*/






}
