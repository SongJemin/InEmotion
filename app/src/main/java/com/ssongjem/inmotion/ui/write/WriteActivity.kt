package com.ssongjem.inmotion.ui.write

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ssongjem.inmotion.R
import com.ssongjem.inmotion.base.BaseActivity
import com.ssongjem.inmotion.databinding.ActivityWriteBinding

class WriteActivity : BaseActivity<ActivityWriteBinding, WriteViewModel>(), WriteNavigator {

    private lateinit var binding : ActivityWriteBinding
    private lateinit var writeViewModel: WriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding()

        binding = getViewDataBinding()
        getViewModel()

        binding.lifecycleOwner = this
        binding.viewModel = writeViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): WriteViewModel {
        writeViewModel = ViewModelProvider(this).get(WriteViewModel::class.java)
        writeViewModel.setNavigator(this)
        return writeViewModel
    }
}