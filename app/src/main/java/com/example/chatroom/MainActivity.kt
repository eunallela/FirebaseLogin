package com.example.chatroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    private lateinit var alu: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alu = FirebaseAuth.getInstance()

        btn.setOnClickListener {
            if (idEmail.text.trim().toString().isNotEmpty() || idPassword.text.trim().toString().isNotEmpty()) {
                createUser(idEmail.text.trim().toString(), idPassword.text.trim().toString())

            } else {
                Toast.makeText(this,"Input Required",Toast.LENGTH_LONG).show()

            }
        }
    }
       fun createUser(email:String,password:String){
            alu.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
                task ->
                if (task.isSuccessful){
                Log.e("Task Message","Successfull");
                    var intent = Intent(this,MainActivity2::class.java)
                    startActivity(intent)
            }
            else{
                Log.e("Task Message","Failed to register")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val user = alu.currentUser;
        if (user !=null){
            var intent = Intent(this,MainActivity3::class.java)
            startActivity(intent)
        }
    }
    }




