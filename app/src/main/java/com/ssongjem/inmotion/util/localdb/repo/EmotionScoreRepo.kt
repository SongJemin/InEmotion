package com.ssongjem.inmotion.util.localdb.repo

import com.ssongjem.inmotion.data.EmotionScore
import com.ssongjem.inmotion.util.localdb.dao.EmotionScoreDao

class EmotionScoreRepo(val emotionScoreDao: EmotionScoreDao){
    fun selectTodayScore(date: String): EmotionScore {
        return emotionScoreDao.selectTodayScore(date)
    }

    fun insertTodayScore(emotionScore: EmotionScore){
        emotionScoreDao.insertTodayScore(emotionScore)
    }

    fun updateTodayScore(emotionScore: EmotionScore){
        emotionScoreDao.updateTodayScore(emotionScore)
    }
}