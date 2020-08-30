package com.ssongjem.inmotion.util.localdb.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ssongjem.inmotion.data.EmotionWord

@Dao
interface EmotionWordDao {
    @Query("SELECT * FROM EmotionWord")
    fun selectAll() : LiveData<List<EmotionWord>>

    @Insert
    fun insertEmotionWord(emotionWord: EmotionWord)
}