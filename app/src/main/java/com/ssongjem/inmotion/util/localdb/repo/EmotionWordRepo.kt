package com.ssongjem.inmotion.util.localdb.repo

import androidx.lifecycle.LiveData
import com.ssongjem.inmotion.data.EmotionWord
import com.ssongjem.inmotion.util.localdb.dao.EmotionWordDao

class EmotionWordRepo(private val emotionWordDao : EmotionWordDao) {
    // 감정유추단어 리스트 다 가져오기
    val allEmotionWords: LiveData<List<EmotionWord>> = emotionWordDao.selectAll()

    // 감정유추단어 찾기
    fun selectEmotionWord(word : String): EmotionWord {
        return emotionWordDao.selectWord(word)
    }
}