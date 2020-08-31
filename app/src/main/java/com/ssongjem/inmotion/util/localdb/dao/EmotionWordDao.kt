package com.ssongjem.inmotion.util.localdb.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ssongjem.inmotion.data.EmotionWord

@Dao
interface EmotionWordDao {
    @Query("SELECT * FROM EmotionWord")
    fun selectAll() : LiveData<List<EmotionWord>>

    @Query("SELECT * FROM EmotionWord WHERE word = :word")
    fun selectWord(word: String) : EmotionWord

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEmotionWord(emotionWord: EmotionWord)
}