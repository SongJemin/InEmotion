package com.ssongjem.inmotion.ui.record

import android.app.Application
import android.graphics.Color
import android.util.Log
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.ssongjem.inmotion.base.BaseViewModel


class RecordViewModel(application: Application) : BaseViewModel<RecordNavigator>(application) {

    var axisMaximumVal = 7f

    fun clickWeekBtn() {
        getNavigator()!!.clickRangeWeek()
    }

    fun clickMonthBtn() {
        getNavigator()!!.clickRangeMonth()
    }

    fun setChart(xAxis : XAxis, flag : Boolean) {
        val labels = ArrayList<String>()

        labels.add("09-28")
        labels.add("09-29")
        labels.add("09-30")
        labels.add("10-01")
        labels.add("10-02")
        labels.add("10-03")
        labels.add("10-04")

        if(flag) axisMaximumVal = 7f
        else axisMaximumVal = 30f

        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM // X축 데이터의 위치를 아래로
            textSize = 10f
            setDrawGridLines(false) // 배경 그리드 라인 세팅
            granularity = 1f // x축 데이터 표시 간격
            axisMaximum = axisMaximumVal // x축 데이터의 최소 표시값
            Log.d("ssongjem", "axisMaximum = " + axisMaximum)
            isGranularityEnabled = true // x축 간격을 제한하는 세분화 기능
            valueFormatter = (MyValueFormatter(labels))
        }
    }

    class MyValueFormatter(private val xValsDateLabel: ArrayList<String>) : ValueFormatter() {
        override fun getFormattedValue(value: Float): String {
            return value.toString()
        }
        override fun getAxisLabel(value: Float, axis: AxisBase): String {
            if (value.toInt() >= 0 && value.toInt() <= xValsDateLabel.size - 1) {
                return xValsDateLabel[value.toInt()]
            } else {
                return ("").toString()
            }
        }
    }

    fun addEntry(lineData: LineData){
        val data = lineData

        data?.let {
            var set : ILineDataSet? = data.getDataSetByIndex(0)
            // 임의의 데이터셋(0번부터 시작)
            if(set == null){
                set = createSet()
                data.addDataSet(set)
            }
            data.addEntry(Entry(set!!.entryCount.toFloat(), 150.0F), 0)
            data.notifyDataChanged()
            getNavigator()!!.addEntry(data)
            data.addEntry(Entry(set!!.entryCount.toFloat(), 311.0F), 0)
            data.notifyDataChanged()
            getNavigator()!!.addEntry(data)
            data.addEntry(Entry(set!!.entryCount.toFloat(), 231.0F), 0)
            data.notifyDataChanged()
            getNavigator()!!.addEntry(data)
            data.addEntry(Entry(set!!.entryCount.toFloat(), 241.0F), 0)
            data.notifyDataChanged()
            getNavigator()!!.addEntry(data)
            data.addEntry(Entry(set!!.entryCount.toFloat(), 121.0F), 0)
            data.notifyDataChanged()
            getNavigator()!!.addEntry(data)
            data.addEntry(Entry(set!!.entryCount.toFloat(), 301.0F), 0)
            data.notifyDataChanged()
            data.addEntry(Entry(set!!.entryCount.toFloat(), 470.0F), 0)
            data.notifyDataChanged()
            getNavigator()!!.addEntry(data)
        }
    }

    fun createSet() : LineDataSet {
        val set = LineDataSet(null, "점수")
        set.apply {
            axisDependency = YAxis.AxisDependency.LEFT
            valueTextSize = 10f
            lineWidth = 1f
            circleRadius = 3f
            fillAlpha = 0
            highLightColor = Color.BLACK
            setDrawValues(true)
        }
        return set
    }
}