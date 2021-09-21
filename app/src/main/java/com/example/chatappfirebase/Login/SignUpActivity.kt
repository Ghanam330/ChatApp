package com.example.chatappfirebase.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatappfirebase.HomeScreen.HomeScreen
import com.example.chatappfirebase.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var databaseRefrance : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()
    }

    private fun register(userName:String,email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful) {
                    val user :FirebaseUser? =auth.currentUser
                    val userId =user!!.uid

                    databaseRefrance = FirebaseDatabase.getInstance().getReference("Users").child(userId)

                    val hashMap:HashMap<String, String> = HashMap()
                    hashMap["userId"] = userId
                    hashMap["userName"] = userName
                    hashMap["profileImage"] = ""

                    databaseRefrance.setValue(hashMap).addOnCompleteListener {

                        if(it.isSuccessful){
                            val intent = Intent(this@SignUpActivity, HomeScreen::class.java)
                            startActivity(intent)
                        }
                    }

                }
            }
    }
}
