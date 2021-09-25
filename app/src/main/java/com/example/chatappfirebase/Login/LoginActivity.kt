package com.example.chatappfirebase.Login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.chatappfirebase.HomeScreen.UserActivity
import com.example.chatappfirebase.R
import com.example.chatappfirebase.SplachSreen.Activity.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var auth: FirebaseAuth? = null

    val user: FirebaseUser? = auth?.currentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        //check if user login then navigate to user screen
        auth = FirebaseAuth.getInstance()
        val intent = Intent(this@LoginActivity, UserActivity::class.java)
        if (auth!!.getCurrentUser() != null) {
            startActivity(intent)
            finish()
            return
        }


        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            when {
                TextUtils.isEmpty(email) && TextUtils.isEmpty(password) -> {
                    Toast.makeText(
                        applicationContext,
                        "email and password are required",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    auth!!.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) {
                            if (it.isSuccessful) {
                                etEmail.setText("")
                                etPassword.setText("")
                                val intent = Intent(
                                    this@LoginActivity,
                                    UserActivity::class.java
                                )
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "email or password invalid",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
        }

        btnSignUp.setOnClickListener {
            val intent = Intent(
                this@LoginActivity,
                SignUpActivity::class.java
            )
            startActivity(intent)
            finish()
        }


        val pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE)
        if (pref.getBoolean("activity_executed", false)) {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val ed: SharedPreferences.Editor = pref.edit()
            ed.putBoolean("activity_executed", true)
            ed.apply()
        }
    }

    override fun onStart() {
        super.onStart()
        user?.let {
            startActivity(Intent(this, UserActivity::class.java))
            Toast.makeText(
                applicationContext,
                "Welcome back",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}