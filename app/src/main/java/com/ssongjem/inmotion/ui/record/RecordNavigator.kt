package com.ssongjem.inmotion.ui.record

import com.github.mikephil.charting.data.LineData

interface RecordNavigator {
    fun addEntry(datas : LineData)

    fun setLineChart(labelList : ArrayList<String>, valList : ArrayList<Int>)

    fun clickRangeWeek()

    fun clickRangeMonth()

}