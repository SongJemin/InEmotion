package com.ssongjem.inmotion.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ssongjem.inmotion.base.BaseViewModel
import com.ssongjem.inmotion.data.EmotionWord
import com.ssongjem.inmotion.util.localdb.AppDatabase
import com.ssongjem.inmotion.util.localdb.repo.EmotionWordRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : BaseViewModel<MainNavigator>(application) {

    private val repository : EmotionWordRepo
    var emotionWords : LiveData<List<EmotionWord>>
    var getApplication = application

    init {
        val emotionWordDao = AppDatabase.getDatabase(getApplication).emotionWordDao()
        repository = EmotionWordRepo(emotionWordDao)
        // 감정 유추 단어 리스트 보기
        emotionWords = repository.allEmotionWords
    }

    fun moveWrite() {
        getNavigator()!!.moveWrite()
    }

    fun moveRecord() {
        getNavigator()!!.moveRecord()
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
                AppDatabase.getDatabase(getApplication).emotionWordDao().insertEmotionWord(arr.get(i))
            }
        }
    }
}