package com.ssongjem.inmotion.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emotionscore")
class EmotionScore (
    @PrimaryKey var date : String,
    var totalScore : Int
)