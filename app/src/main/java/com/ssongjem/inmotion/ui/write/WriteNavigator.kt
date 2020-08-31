package com.ssongjem.inmotion.ui.write

interface WriteNavigator {

    fun setVoiceResult(inputStr: String)

    fun setScoreResult(score : Int)

    fun clearField()

    fun getTodayContent() : String
}