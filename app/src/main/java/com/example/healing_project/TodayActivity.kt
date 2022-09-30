package com.example.healing_project

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healing_project.databinding.ActivityMainBinding
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_today.*
import kotlinx.android.synthetic.main.activity_today.img_AppLogo
import java.util.*

class TodayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cAdapter: TodayRecyclerViewAdapter
    lateinit var datas : TodayData
    lateinit var imageIv:ImageView
    lateinit var storage: FirebaseStorage
    var images : ArrayList<Image> = ArrayList()

    companion object{
    }

    // Create a storage reference from our app

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_today)

        //cAdapter = TodayRecyclerViewAdapter()
        //item_list.adapter=cAdapter

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

//모델 클래스 정의: 기타 원하는 데이터를 추가 할 수 있다.
data class Image(val imageUrl: String)
