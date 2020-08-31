package com.ssongjem.inmotion.ui.write

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ssongjem.inmotion.base.BaseViewModel
import com.ssongjem.inmotion.data.EmotionWord
import com.ssongjem.inmotion.util.localdb.AppDatabase
import com.ssongjem.inmotion.util.localdb.repo.EmotionWordRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


class WriteViewModel(application: Application) : BaseViewModel<WriteNavigator>(application) {

    lateinit var speechRecognizer: SpeechRecognizer
    lateinit var intent: Intent
    private val repository: EmotionWordRepo
    lateinit var matches: ArrayList<String>
    var emotionScore: LiveData<Int>? = null
    var getApplication = application

    var score = 50

    init {
        initSetting()
        val emotionWordDao = AppDatabase.getDatabase(getApplication).emotionWordDao()
        repository = EmotionWordRepo(emotionWordDao)
    }

    fun scoring() {
        Log.d("ssongjem", "inputData size = " + matches.size)
        for (i in 0..matches.size - 1) {
            var inputData = matches.get(i)
            Log.d("ssongjem", "inputData[" + i + "] = " + inputData)
            var st = StringTokenizer(inputData, " ")

            while (st.hasMoreTokens()) {
                var word = st.nextToken()
                checkWord(word)
            }
        }
    }

    fun checkWord(word: String) {
        viewModelScope.launch(Dispatchers.IO) {
            // 데이터 있을 경우
            var eWord = repository.selectEmotionWord(word)
            if (eWord != null) {
                score = eWord.score
                Log.d("jemin", "score = " + score)
                getNavigator()!!.setScoreResult(score)
            }
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
            getNavigator()!!.setVoiceResult(matches)

            scoring()
        }
    }
}