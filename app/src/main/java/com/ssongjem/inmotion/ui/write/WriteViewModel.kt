package com.ssongjem.inmotion.ui.write

import android.app.Application
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ssongjem.inmotion.base.BaseViewModel
import com.ssongjem.inmotion.data.EmotionScore
import com.ssongjem.inmotion.data.EmotionWord
import com.ssongjem.inmotion.util.localdb.AppDatabase
import com.ssongjem.inmotion.util.localdb.repo.EmotionScoreRepo
import com.ssongjem.inmotion.util.localdb.repo.EmotionWordRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList


@RequiresApi(Build.VERSION_CODES.O)
class WriteViewModel(application: Application) : BaseViewModel<WriteNavigator>(application) {

    lateinit var speechRecognizer: SpeechRecognizer
    lateinit var intent: Intent
    private val emotionWordRepo: EmotionWordRepo
    private val emotionScoreRepo: EmotionScoreRepo
    lateinit var matches: ArrayList<String>
    var getApplication = application
    var today: LocalDate
    var inputStr: String = ""

    var score = 50

    init {
        initSetting()
        val emotionWordDao = AppDatabase.getDatabase(getApplication).emotionWordDao()
        val emotionScoreDao = AppDatabase.getDatabase(getApplication).emotionScoreDao()
        emotionWordRepo = EmotionWordRepo(emotionWordDao)
        emotionScoreRepo = EmotionScoreRepo(emotionScoreDao)

        today = LocalDate.now()
    }

    fun scoring() {
        var getTodayContent = getNavigator()!!.getTodayContent()
        Log.d("ssongjem", "getTodayContent data = " + getTodayContent)
        var st = StringTokenizer(getTodayContent, " ")

        while (st.hasMoreTokens()) {
            var word = st.nextToken()
            checkWord(word)
        }
    }

    fun checkWord(word: String) {
        viewModelScope.launch(Dispatchers.IO) {
            // 데이터 있을 경우
            var eWord = emotionWordRepo.selectEmotionWord(word)
            if (eWord != null) {
                score = eWord.score
                Log.d("jemin", "score = " + score)

                Log.d("jemin", "today = " + today.toString())

//                var existToday : Boolean = false
                var emotionScore = emotionScoreRepo.selectTodayScore(today.toString())

                // 오늘 데이터 존재 O
                if (emotionScore != null) {
                    var getScore = emotionScore.totalScore
                    score += getScore
                    emotionScoreRepo.updateTodayScore(EmotionScore(today.toString(), score))
                }
                // 오늘 데이터 존재 X
                else {
                    emotionScoreRepo.insertTodayScore(EmotionScore(today.toString(), score))
                }

                Log.d("jemin", "today is = " + today.toString() + "totalScore = " + score)

                getNavigator()!!.setScoreResult(score)
            }
        }
    }

    fun clickAddBtn() {
        scoring()
    }

    fun clickRefreshBtn() {
        getNavigator()!!.clearField()
        getNavigator()!!.setScoreResult(0)

        viewModelScope.launch(Dispatchers.IO) {
            emotionScoreRepo.updateTodayScore(EmotionScore(today.toString(), 0))
        }
    }

    fun record() {
        Toast.makeText(getApplication(), "Voice Record Start", Toast.LENGTH_LONG).show()
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getApplication)
        speechRecognizer.setRecognitionListener(listener())
        speechRecognizer.startListening(intent)
    }

    fun initSetting() {
        intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getApplication.packageName)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR")
    }

    private fun listener(): RecognitionListener = object : RecognitionListener {
        override fun onReadyForSpeech(p0: Bundle?) {
            Toast.makeText(getApplication, "onReadyForSpeech", Toast.LENGTH_SHORT).show()
        }

        override fun onRmsChanged(p0: Float) {
            Toast.makeText(getApplication, "onRmsChanged", Toast.LENGTH_SHORT).show()
        }

        override fun onBufferReceived(p0: ByteArray?) {
            Toast.makeText(getApplication, "onBufferReceived", Toast.LENGTH_SHORT).show()
        }

        override fun onPartialResults(p0: Bundle?) {
            Toast.makeText(getApplication, "onPartialResults", Toast.LENGTH_SHORT).show()
        }

        override fun onEvent(p0: Int, p1: Bundle?) {
            Toast.makeText(getApplication, "onEvent", Toast.LENGTH_SHORT).show()
        }

        override fun onBeginningOfSpeech() {
            Toast.makeText(getApplication, "onBeginningOfSpeech", Toast.LENGTH_SHORT).show()
        }

        override fun onEndOfSpeech() {
            Toast.makeText(getApplication, "onEndOfSpeech", Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: Int) {
            Toast.makeText(getApplication, "onError", Toast.LENGTH_SHORT).show()
        }

        override fun onResults(result: Bundle?) {
            Toast.makeText(getApplication, "onResults", Toast.LENGTH_SHORT).show()
            matches =
                result!!.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION) as ArrayList<String>
            Log.d("ssongjem", "inputData size = " + matches.size)

            var sb: StringBuilder = StringBuilder()
            for (i in 0..matches.size - 1) {
                sb.append(matches.get(i))
            }
            inputStr = sb.toString()
            getNavigator()!!.setVoiceResult(inputStr)
        }
    }
}