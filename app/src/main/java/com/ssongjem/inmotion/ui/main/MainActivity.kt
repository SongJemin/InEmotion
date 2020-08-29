package com.ssongjem.inmotion.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ssongjem.inmotion.R
import com.ssongjem.inmotion.base.BaseActivity
import com.ssongjem.inmotion.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = getViewDataBinding()
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.setNavigator(this)

        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel
    }

    override fun getLayoutId(): Int {
        TODO("Not yet implemented")
    }

    override fun getViewModel(): MainViewModel {
        TODO("Not yet implemented")
    }
}