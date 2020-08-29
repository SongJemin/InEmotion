package com.ssongjem.inmotion.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(application: Application) : AndroidViewModel(application) {
    private var navigator: WeakReference<N>? = null

    val mIsLoading = MutableLiveData<Boolean>(false)

    override fun onCleared() {
        super.onCleared()
    }

    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }

    fun getNavigator(): N? {
        return navigator!!.get()
    }
}