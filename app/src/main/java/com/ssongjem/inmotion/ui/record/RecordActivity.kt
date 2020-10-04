package com.ssongjem.inmotion.ui.record

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.ssongjem.inmotion.R
import com.ssongjem.inmotion.base.BaseActivity
import com.ssongjem.inmotion.databinding.ActivityRecordBinding
import kotlinx.android.synthetic.main.activity_record.*


class RecordActivity : BaseActivity<ActivityRecordBinding, RecordViewModel>(), RecordNavigator {

    private lateinit var binding : ActivityRecordBinding
    private lateinit var recordViewModel: RecordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding()

        binding = getViewDataBinding()
        getViewModel()

        binding.lifecycleOwner = this
        binding.viewModel = recordViewModel

        var flag = true; // true : 일주일, false = 한달

        setChart(flag)
    }

    fun setChart(flag : Boolean) {

        val xAxis = lc_record_chart.xAxis
        recordViewModel.setChart(xAxis, flag)

        lc_record_chart.apply {
            axisRight.isEnabled = false // y축의 오른쪽 데이터 비활성화
            axisLeft.axisMaximum = 500f // y축의 왼쪽 데이터 최대값은 500으로
            legend.apply {
                textSize = 15f
                verticalAlignment = Legend.LegendVerticalAlignment.TOP // 수직 조정 -> 위로
                horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER // 수평 조정 -> 가운데로
                orientation = Legend.LegendOrientation.HORIZONTAL // 범례와 차트 정렬 -> 수평
                setDrawInside(false) // 차트 안에 그릴 것인가?
            }
        }

        val lineData = LineData()
        lc_record_chart.data = lineData
        recordViewModel.addEntry(lineData)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_record
    }

    override fun getViewModel(): RecordViewModel {
        recordViewModel = ViewModelProvider(this).get(RecordViewModel::class.java)
        recordViewModel.setNavigator(this)
        return recordViewModel
    }

    override fun addEntry(datas : LineData) {
        lc_record_chart.apply {
            notifyDataSetChanged()
            lc_record_chart.apply {
                notifyDataSetChanged()
                moveViewToX(data.entryCount.toFloat())
                Log.d("ssongjem", "data.entryCount.toFloat() = " + data.entryCount.toFloat())
                setVisibleXRangeMaximum(100f)
                setPinchZoom(false)
                isDoubleTapToZoomEnabled = false
                description.text = "날짜"
                setBackgroundColor(resources.getColor(R.color.white))
                description.textSize = 15f
                setExtraOffsets(8f, 16f, 8f, 16f)
                axisLeft.setLabelCount(5, true)
                getXAxis().setSpaceMax(0.5f);
                getXAxis().setSpaceMin(0.5f);
            }
        }
    }

    override fun setLineChart(labelList: ArrayList<String>, valList: ArrayList<Int>) {

    }

    override fun clickRangeWeek() {
        val xAxis = lc_record_chart.xAxis
        recordViewModel.setChart(xAxis, true)
    }

    override fun clickRangeMonth() {
        val xAxis = lc_record_chart.xAxis
        recordViewModel.setChart(xAxis, false)
    }
}