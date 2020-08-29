package com.ssongjem.inmotion.ui.record

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ssongjem.inmotion.R
import com.ssongjem.inmotion.base.BaseActivity
import com.ssongjem.inmotion.databinding.ActivityRecordBinding

class RecordActivity : BaseActivity<ActivityRecordBinding, RecordViewModel>(), RecordNavigator {

    private lateinit var binding : ActivityRecordBinding
    private lateinit var recordViewModel: RecordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding()

        binding = getViewDataBinding()
        getViewModel()

        binding.lifecycleOwner = this
        binding.viewModel = recordViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_record
    }

    override fun getViewModel(): RecordViewModel {
        recordViewModel = ViewModelProvider(this).get(RecordViewModel::class.java)
        recordViewModel.setNavigator(this)
        return recordViewModel
    }
}