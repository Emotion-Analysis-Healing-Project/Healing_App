package com.example.healing_project
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_feeling.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var scheduleRecyclerViewAdapter: RecyclerViewAdapter
    //lateinit var datas : TodayData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        //datas = intent.getSerializableExtra("data") as TodayData

        //Glide.with(this).load(datas.image).into(feeling)
        //time_log.text = datas.time
    }

    fun initView() {

        scheduleRecyclerViewAdapter = RecyclerViewAdapter(this)

        rv_schedule.layoutManager = GridLayoutManager(this, BaseCalendar.DAYS_OF_WEEK)
        rv_schedule.adapter = scheduleRecyclerViewAdapter
        rv_schedule.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        rv_schedule.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        tv_prev_month.setOnClickListener {
            scheduleRecyclerViewAdapter.changeToPrevMonth()
        }

        tv_next_month.setOnClickListener {
            scheduleRecyclerViewAdapter.changeToNextMonth()
        }
    }

    fun refreshCurrentMonth(calendar: Calendar) {
        val sdf = SimpleDateFormat("yyyy. MM", Locale.KOREAN)
        tv_current_month.text = sdf.format(calendar.time)
    }
}
