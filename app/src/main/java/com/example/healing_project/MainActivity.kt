package com.example.healing_project
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.healing_project.databinding.ActivityMainBinding
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_today.*
import kotlinx.android.synthetic.main.item_feeling.*
import kotlinx.android.synthetic.main.item_schedule.*
import java.io.*
import java.net.Socket
import java.text.SimpleDateFormat
import java.util.*
import java.util.Random
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    lateinit var scheduleRecyclerViewAdapter: RecyclerViewAdapter
    private lateinit var binding : ActivityMainBinding
    /*var connect_btn // ip 받아오는 버튼
            : Button? = null*/
    //lateinit var datas : TodayData

    private val html = ""
    private var mHandler: Handler? = null
    private var socket: Socket? = null
    private val networkReader: BufferedReader? = null
    private val networkWriter: PrintWriter? = null
    private var dos: DataOutputStream? = null
    private var dis: DataInputStream? = null
    private val ip = "192.168.15.127" // IP 번호
    private val port = 9999 // port 번호
    lateinit var storage: FirebaseStorage

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        val links= listOf("https://www.youtube.com/watch?v=xym-gmq_rlw","https://www.youtube.com/watch?v=HrO74lj5QBs","https://www.youtube.com/watch?v=Q-iEfzaikBI&t=12333s")
        val random_index = Random().nextInt(3)
        video_button.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(links[random_index]))
            startActivity(intent)
            // Log.d("태그", "내용 : "+random_index)
        }

        light_button.setOnClickListener {
            connect()
        }

        storage = FirebaseStorage.getInstance()
        val gsReference = storage.getReferenceFromUrl("gs://emotion-b0c2f.appspot.com/User1_id/20220930/face_20220930_165625.jpg")

        //AppGlideModule.kt 파일을 만들어 주고 clear project와 rebuild project를 해줘야 사용이 가능했다. 관련 이야기가 분분하다.
        GlideApp.with(this)
            .load(gsReference)
            .fitCenter()
            .into(imageViewss)

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

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

    }

    fun refreshCurrentMonth(calendar: Calendar) {
        val sdf = SimpleDateFormat("yyyy. MM", Locale.KOREAN)
        tv_current_month.text = sdf.format(calendar.time)
    }

    /*fun onClick(v: View) {
        when (v.id) {
            R.id.light_button -> connect()
        }
    }*/

    // 로그인 정보 db에 넣어주고 연결시켜야 함.
    fun connect() {
        mHandler = Handler()
        Log.w("connect", "connecting")
        // 받아오는거
        val checkUpdate: Thread = object : Thread() {
            override fun run() {
                // ip받기
                val newip = "192.168.15.127"
                var line = 1
                // 서버 접속
                try {
                    socket = Socket(newip, port)
                    Log.w("서버 접속됨", "서버 접속됨")
                } catch (e1: IOException) {
                    Log.w("서버접속못함", "서버접속못함")
                    e1.printStackTrace()
                }
                Log.w("edit 넘어가야 할 값 : ", "안드로이드에서 서버로 연결요청")
                // Buffered가 잘못된듯.
                try {
                    dos = DataOutputStream(socket!!.getOutputStream()) // output에 보낼꺼 넣음
                    dis = DataInputStream(socket!!.getInputStream()) // input에 받을꺼 넣어짐
                    //dos!!.writeUTF("try connect")
                    //dos!!.write(line)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Log.w("버퍼", "버퍼생성 잘못됨")
                }
                Log.w("버퍼", "버퍼생성 잘됨")

                while (true) {
                    dos!!.write(line)
                    // 서버에서 받아옴
                    try {
                        //val line = ""
                        var line2: Int
                        while (true) {
                            //line = (String) dis.readUTF();
                            line2 = dis!!.read()
                            //Log.w("서버에서 받아온 값 ", "" + line2);
                            //Log.w("서버에서 받아온 값 ", "" + line);
                            if (line2 > 0) {
                                Log.w("------서버에서 받아온 값 ", "" + line2)
                                //dos!!.write(line2)
                                dos!!.flush()
                            }
                            if (line2 == 99) {
                                Log.w("------서버에서 받아온 값 ", "" + line2)
                                socket!!.close()
                                break
                            }
                        }
                    } catch (e: Exception) {
                    }
                }
            }
        }
        // 소켓 접속 시도, 버퍼생성
        checkUpdate.start()

    }

}
