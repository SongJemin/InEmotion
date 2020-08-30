package com.ssongjem.inmotion.ui.write

import android.os.Bundle
import android.speech.SpeechRecognizer
import androidx.lifecycle.ViewModelProvider
import com.ssongjem.inmotion.R
import com.ssongjem.inmotion.base.BaseActivity
import com.ssongjem.inmotion.databinding.ActivityWriteBinding
import kotlinx.android.synthetic.main.activity_write.*

class WriteActivity : BaseActivity<ActivityWriteBinding, WriteViewModel>(), WriteNavigator {

    private lateinit var binding : ActivityWriteBinding
    private lateinit var writeViewModel: WriteViewModel
    private lateinit var speechRecognizer: SpeechRecognizer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding()

        binding = getViewDataBinding()
        getViewModel()

        binding.lifecycleOwner = this
        binding.viewModel = writeViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_write
    }

    override fun getViewModel(): WriteViewModel {
        writeViewModel = ViewModelProvider(this).get(WriteViewModel::class.java)
        writeViewModel.setNavigator(this)
        return writeViewModel
    }

    override fun setVoiceResult(matches: ArrayList<String>) {
        for (i in 0.. matches.size-1){
            tv_write_result.setText(matches.get(i))
        }
    }
}