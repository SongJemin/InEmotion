package com.ssongjem.inmotion.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ssongjem.inmotion.base.BaseViewModel
import com.ssongjem.inmotion.data.EmotionWord
import com.ssongjem.inmotion.util.localdb.AppDatabase
import com.ssongjem.inmotion.util.localdb.repo.EmotionWordRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : BaseViewModel<MainNavigator>(application) {

    private val repository : EmotionWordRepo
    var emotionWords : LiveData<List<EmotionWord>>
    var getApplication = application

    init {
        val emotionWordDao = AppDatabase.getDatabase(getApplication).emotionWordDao()
        repository = EmotionWordRepo(emotionWordDao)
        // 감정 유추 단어 리스트 보기
        emotionWords = repository.allEmotionWords
    }

    fun moveWrite() {
        getNavigator()!!.moveWrite()
    }

    fun moveRecord() {
        getNavigator()!!.moveRecord()
    }

    fun saveWord(): List<EmotionWord> {
        var arr: ArrayList<EmotionWord> = ArrayList<EmotionWord>()

        arr.add(EmotionWord("기뻐", 50))
        arr.add(EmotionWord("즐거워", 50))
        arr.add(EmotionWord("행복해", 50))
        arr.add(EmotionWord("좋아", 50))
        arr.add(EmotionWord("만족해", 50))
        arr.add(EmotionWord("신나", 50))
        arr.add(EmotionWord("고마워", 50))
        arr.add(EmotionWord("감사해", 50))
        arr.add(EmotionWord("든든해", 50))
        arr.add(EmotionWord("반가워", 50))
        arr.add(EmotionWord("사랑스러워", 50))
        arr.add(EmotionWord("설레", 50))
        arr.add(EmotionWord("재미있어", 50))
        arr.add(EmotionWord("자신있어", 50))
        arr.add(EmotionWord("편안해", 50))
        arr.add(EmotionWord("홀가분해", 50))
        arr.add(EmotionWord("활기차", 50))
        arr.add(EmotionWord("감동했어", 50))
        arr.add(EmotionWord("뭉클했어", 50))
        arr.add(EmotionWord("감격스러웠어", 50))
        arr.add(EmotionWord("벅차", 50))
        arr.add(EmotionWord("활홀해", 50))
        arr.add(EmotionWord("활기차", 50))
        arr.add(EmotionWord("유쾌해", 50))
        arr.add(EmotionWord("통쾌해", 50))
        arr.add(EmotionWord("따뜻해", 50))
        arr.add(EmotionWord("감미로워", 50))
        arr.add(EmotionWord("포근해", 50))
        arr.add(EmotionWord("푸근해", 50))
        arr.add(EmotionWord("친근해", 50))
        arr.add(EmotionWord("훈훈해", 50))
        arr.add(EmotionWord("정겨워", 50))
        arr.add(EmotionWord("뿌듯해", 50))
        arr.add(EmotionWord("산뜻해", 50))
        arr.add(EmotionWord("만족스러워", 50))
        arr.add(EmotionWord("흡족해", 50))
        arr.add(EmotionWord("개운해", 50))
        arr.add(EmotionWord("후련해", 50))
        arr.add(EmotionWord("흐뭇해", 50))
        arr.add(EmotionWord("느긋해", 50))
        arr.add(EmotionWord("담담해", 50))
        arr.add(EmotionWord("긴장이 풀려", 50))
        arr.add(EmotionWord("안심이 돼", 50))
        arr.add(EmotionWord("가벼워", 50))
        arr.add(EmotionWord("평화로워", 50))
        arr.add(EmotionWord("고요해", 50))
        arr.add(EmotionWord("여유로워", 50))
        arr.add(EmotionWord("진정돼", 50))
        arr.add(EmotionWord("흥미로워", 50))
        arr.add(EmotionWord("끌려", 50))
        arr.add(EmotionWord("용기가나", 50))
        arr.add(EmotionWord("기운이넘쳐", 50))
        arr.add(EmotionWord("당당해", 50))
        arr.add(EmotionWord("힘이 솟아", 50))
        arr.add(EmotionWord("흥분돼", 50))
        arr.add(EmotionWord("들떠", 50))

        arr.add(EmotionWord("놀랐어", -10))
        arr.add(EmotionWord("어색해", -10))
        arr.add(EmotionWord("긴장했어", -10))
        arr.add(EmotionWord("당황스러웠어", -10))
        arr.add(EmotionWord("곤란해", -10))
        arr.add(EmotionWord("귀찮아", -10))
        arr.add(EmotionWord("피곤해", -10))
        arr.add(EmotionWord("지루해", -10))
        arr.add(EmotionWord("부담스러워", -10))
        arr.add(EmotionWord("부끄러워", -10))

        arr.add(EmotionWord("걱정스러워", -20))
        arr.add(EmotionWord("두려워", -20))
        arr.add(EmotionWord("무서워", -20))
        arr.add(EmotionWord("불안해", -20))
        arr.add(EmotionWord("혼란스러워", -20))
        arr.add(EmotionWord("안타까워", -20))
        arr.add(EmotionWord("미안해", -20))
        arr.add(EmotionWord("답답해", -20))
        arr.add(EmotionWord("불편해", -20))
        arr.add(EmotionWord("지긋지긋해", -20))
        arr.add(EmotionWord("막막해", -20))
        arr.add(EmotionWord("신경쓰여", -20))
        arr.add(EmotionWord("오싹해", -20))
        arr.add(EmotionWord("섬뜩해", -20))
        arr.add(EmotionWord("암담해", -20))
        arr.add(EmotionWord("주눅들어", -20))
        arr.add(EmotionWord("겁나", -20))
        arr.add(EmotionWord("떨려", -20))
        arr.add(EmotionWord("서늘해", -20))
        arr.add(EmotionWord("조바심나", -20))
        arr.add(EmotionWord("조마조마해", -20))
        arr.add(EmotionWord("초조해", -20))
        arr.add(EmotionWord("거북해", -20))
        arr.add(EmotionWord("곤혹스러워", -20))
        arr.add(EmotionWord("떨떠름해", -20))
        arr.add(EmotionWord("언짢아", -20))
        arr.add(EmotionWord("난처해", -20))
        arr.add(EmotionWord("멋쩍어", -20))
        arr.add(EmotionWord("갑갑해", -20))
        arr.add(EmotionWord("찝찝해", -20))
        arr.add(EmotionWord("눈물나", -20))
        arr.add(EmotionWord("쓰라려", -20))
        arr.add(EmotionWord("울적해", -20))
        arr.add(EmotionWord("처참해", -20))
        arr.add(EmotionWord("비참해", -20))
        arr.add(EmotionWord("김빠져", -20))
        arr.add(EmotionWord("애석해", -20))
        arr.add(EmotionWord("야속해", -20))
        arr.add(EmotionWord("고독해", -20))
        arr.add(EmotionWord("공허해", -20))
        arr.add(EmotionWord("적적해", -20))
        arr.add(EmotionWord("허탈해", -20))
        arr.add(EmotionWord("허해", -20))
        arr.add(EmotionWord("무력해", -20))
        arr.add(EmotionWord("무기력해", -20))
        arr.add(EmotionWord("꿀꿀해", -20))
        arr.add(EmotionWord("노곤해", -20))
        arr.add(EmotionWord("따분해", -20))
        arr.add(EmotionWord("맥빠져", -20))
        arr.add(EmotionWord("맥풀려", -20))
        arr.add(EmotionWord("지긋지긋해", -20))
        arr.add(EmotionWord("귀찮아", -20))
        arr.add(EmotionWord("힘들어", -20))
        arr.add(EmotionWord("무료해", -20))
        arr.add(EmotionWord("성가셔", -20))
        arr.add(EmotionWord("심심해", -20))
        arr.add(EmotionWord("혐오스러워", -20))
        arr.add(EmotionWord("밥맛떨어져", -20))
        arr.add(EmotionWord("질려", -20))
        arr.add(EmotionWord("멍해", -20))
        arr.add(EmotionWord("끓어올라", -20))
        arr.add(EmotionWord("약올라", -20))
        arr.add(EmotionWord("들끓어", -20))

        arr.add(EmotionWord("외로워", -30))
        arr.add(EmotionWord("실망스러워", -30))
        arr.add(EmotionWord("원망스러워", -30))
        arr.add(EmotionWord("분해", -20))
        arr.add(EmotionWord("미워", -30))
        arr.add(EmotionWord("억울해", -30))
        arr.add(EmotionWord("그리워", -30))
        arr.add(EmotionWord("서운해", -30))
        arr.add(EmotionWord("허전해", -30))
        arr.add(EmotionWord("후회돼", -30))


        arr.add(EmotionWord("속상해", -50))
        arr.add(EmotionWord("괴로워", -50))
        arr.add(EmotionWord("서러워", -50))
        arr.add(EmotionWord("화나", -50))
        arr.add(EmotionWord("짜증나", -50))
        arr.add(EmotionWord("슬퍼", -50))
        arr.add(EmotionWord("우울해", -50))

        return arr
    }

    fun setArrData() {

    }

    // suspend가 붙은 메소드는 무조건 코루틴 범위 안에서 실행되어야 한다.
    suspend fun insertEmotionWord() {
        viewModelScope.launch(Dispatchers.IO) {
            var arr: List<EmotionWord> = saveWord()
            for (i in 0..arr.size - 1) {
                AppDatabase.getDatabase(getApplication).emotionWordDao().insertEmotionWord(arr.get(i))
            }
        }
    }
}