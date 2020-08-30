package com.ssongjem.inmotion.util.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ssongjem.inmotion.data.EmotionWord
import com.ssongjem.inmotion.util.localdb.dao.EmotionWordDao

@Database(entities = arrayOf(EmotionWord::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun emotionWordDao() : EmotionWordDao
}