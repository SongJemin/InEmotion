package com.ssongjem.inmotion.ui.main

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.ssongjem.inmotion.R
import com.ssongjem.inmotion.base.BaseActivity
import com.ssongjem.inmotion.databinding.ActivityMainBinding
import com.ssongjem.inmotion.ui.record.RecordActivity
import com.ssongjem.inmotion.ui.write.WriteActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private val PERMISSION : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding()

        binding = getViewDataBinding()
        getViewModel()
        checkPermission()

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

    // 기록하기 화면으로 이동
    override fun moveWrite() {
        var intent = Intent(application, WriteActivity::class.java)
        startActivity(intent)
    }

    // 기록보기 화면으로 이동
    override fun moveRecord() {
        var intent = Intent(application, RecordActivity::class.java)
        startActivity(intent)
    }

    fun checkPermission(){
        if (Build.VERSION.SDK_INT >= 23) {
            // 퍼미션 체크
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.INTERNET,
                    Manifest.permission.RECORD_AUDIO
                ), PERMISSION
            )
        }
    }
}