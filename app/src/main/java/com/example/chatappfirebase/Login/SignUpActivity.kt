package com.example.chatappfirebase.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.chatappfirebase.HomeScreen.UserActivity
import com.example.chatappfirebase.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()
        //check if user login then navigate to user screen
        auth = FirebaseAuth.getInstance()
        val intent = Intent(this@SignUpActivity, UserActivity::class.java)
        if (auth!!.getCurrentUser() != null) {
            startActivity(intent)
            finish()
            return
        }

        btnSignUp.setOnClickListener {
            val userName = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            when {
                TextUtils.isEmpty(userName) -> {
                    Toast.makeText(applicationContext,"username is required",Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(email) -> {
                    Toast.makeText(applicationContext,"email is required",Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(password) -> {
                    Toast.makeText(applicationContext,"password is required",Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(confirmPassword) -> {
                    Toast.makeText(applicationContext,"confirm password is required",Toast.LENGTH_SHORT).show()
                }
                password != confirmPassword -> {
                    Toast.makeText(applicationContext,"password not match",Toast.LENGTH_SHORT).show()
                }
                else -> {
                    register(userName,email,password)
                }
            }


        }

        btnLogin.setOnClickListener {
            val intent = Intent(this@SignUpActivity,
                LoginActivity::class.java)
            startActivity(intent)
            finish()
        }



    }

    private fun register(userName:String,email:String,password:String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    val user: FirebaseUser? = auth.currentUser
                    val userId:String = user!!.uid

                    databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId)

                    val hashMap: HashMap<String, String> = HashMap()
                    hashMap["userId"] = userId
                    hashMap["userName"] = userName
                    hashMap["profileImage"] = ""

                    databaseReference.setValue(hashMap).addOnCompleteListener(this){
                        if (it.isSuccessful){
                            //open home activity
                            etName.setText("")
                            etEmail.setText("")
                            etPassword.setText("")
                            etConfirmPassword.setText("")
                            val intent = Intent(this@SignUpActivity,
                               UserActivity ::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
    }
}
