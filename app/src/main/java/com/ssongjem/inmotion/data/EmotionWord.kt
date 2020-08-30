package com.ssongjem.inmotion.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class EmotionWord(
    var word : String,
    var score : Int
){
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}