package com.ssongjem.inmotion.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ssongjem.inmotion.R
import com.ssongjem.inmotion.base.BaseActivity
import com.ssongjem.inmotion.databinding.ActivityMainBinding
import com.ssongjem.inmotion.ui.record.RecordActivity
import com.ssongjem.inmotion.ui.write.WriteActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding()

        binding = getViewDataBinding()
        getViewModel()

        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainViewModel {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.setNavigator(this)
        return mainViewModel
    }

    override fun moveWrite() {
        var intent = Intent(application, WriteActivity::class.java)
        startActivity(intent)
    }

    override fun moveRecord() {
        var intent = Intent(application, RecordActivity::class.java)
        startActivity(intent)
    }
}