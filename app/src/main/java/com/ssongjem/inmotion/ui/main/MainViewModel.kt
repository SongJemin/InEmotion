package com.ssongjem.inmotion.ui.main

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.ssongjem.inmotion.base.BaseViewModel
import com.ssongjem.inmotion.data.EmotionWord
import com.ssongjem.inmotion.util.localdb.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : BaseViewModel<MainNavigator>(application) {

    private val inmotionDB = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "inmotionDB"
    ).build()

    fun moveWrite() {
        getNavigator()!!.moveWrite()
    }

    fun moveRecord() {
        getNavigator()!!.moveWrite()
    }

    fun saveWord(): List<EmotionWord> {
        var arr: ArrayList<EmotionWord> = ArrayList<EmotionWord>()
        arr.add(EmotionWord("기쁘다", 50))
        arr.add(EmotionWord("즐겁다", 50))
        arr.add(EmotionWord("화난다", -40))
        arr.add(EmotionWord("슬프다", -50))

        return arr
    }

    // suspend가 붙은 메소드는 무조건 코루틴 범위 안에서 실행되어야 한다.
    suspend fun insertEmotionWord() {
        viewModelScope.launch(Dispatchers.IO) {
            var arr: List<EmotionWord> = saveWord()
            for (i in 0..arr.size - 1) {
                inmotionDB.emotionWordDao().insertEmotionWord(arr.get(i))
            }
        }
    }
}