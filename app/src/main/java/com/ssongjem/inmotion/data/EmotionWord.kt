package com.ssongjem.inmotion.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "emotionword")
class EmotionWord(
    @PrimaryKey var word : String,
    var score : Int
){
//    @PrimaryKey(autoGenerate = true) var id:Int = 0

    override fun toString(): String {
        return "word = " + word + ", " + "score = " + score + "\n"
    }
}