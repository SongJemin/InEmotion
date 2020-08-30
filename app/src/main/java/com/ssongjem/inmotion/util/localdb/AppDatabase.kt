package com.ssongjem.inmotion.util.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ssongjem.inmotion.data.EmotionWord
import com.ssongjem.inmotion.util.localdb.dao.EmotionWordDao

@Database(entities = arrayOf(EmotionWord::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun emotionWordDao() : EmotionWordDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "emotionword_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}