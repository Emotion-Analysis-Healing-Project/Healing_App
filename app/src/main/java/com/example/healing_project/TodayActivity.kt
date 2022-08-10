package com.example.healing_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.healing_project.databinding.ActivityMainBinding

import kotlinx.android.synthetic.main.activity_today.*
class TodayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cAdapter: TodayRecyclerViewAdapter
    lateinit var datas : TodayData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_today)

        cAdapter = TodayRecyclerViewAdapter()
        item_list.adapter=cAdapter

    }
}
