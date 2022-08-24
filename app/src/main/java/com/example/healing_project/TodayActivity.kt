package com.example.healing_project

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.healing_project.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_today.*
import kotlinx.android.synthetic.main.activity_today.img_AppLogo
import kotlinx.android.synthetic.main.item_feeling.*
import java.util.*

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

        img_AppLogo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val links= listOf("https://www.youtube.com/watch?v=xym-gmq_rlw","https://www.youtube.com/watch?v=HrO74lj5QBs","https://www.youtube.com/watch?v=Q-iEfzaikBI&t=12333s")
        val random_index = Random().nextInt(3)
        video_button2.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(links[random_index]))
            startActivity(intent)
            Log.d("태그", "내용 : "+random_index)
        }
    }
}
