package com.ssongjem.inmotion.util.localdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ssongjem.inmotion.data.EmotionScore

@Dao
interface EmotionScoreDao {

    @Query("SELECT * FROM emotionscore WHERE date = :date")
    fun selectTodayScore(date : String) : EmotionScore

    @Insert
    fun insertTodayScore(emotionScore: EmotionScore)

    @Update
    fun updateTodayScore(emotionScore: EmotionScore)
}