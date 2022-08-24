package com.example.healing_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    var auth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()
        btn_signup.setOnClickListener {
            signinAndSignup()
        }
    }

    fun signinAndSignup() {
        if (et_password_input.text.toString() == et_password_input_comfirm.text.toString()) {
            auth?.createUserWithEmailAndPassword(et_email_input.text.toString(),et_password_input.text.toString())
                ?.addOnCompleteListener {
                        task ->
                    if(task.isSuccessful) {
                        // Creating a user account
                        moveMainPage(task.result?.user)
                    } else if(task.exception?.message.isNullOrEmpty()) {
                        // Show the error message
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                    }
                }
        }
        else {
            Toast.makeText(this, "비밀번호가 같지 않습니다", Toast.LENGTH_LONG).show()
        }

    }

    // 로그인이 성공하면 다음 페이지로 넘어가는 함수
    fun moveMainPage(user: FirebaseUser?) {
        // 파이어베이스 유저 상태가 있을 경우 다음 페이지로 넘어갈 수 있음
        if(user != null) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}