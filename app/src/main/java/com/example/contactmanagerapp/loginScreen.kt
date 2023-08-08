package com.example.contactmanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contactmanagerapp.databinding.ActivityLoginScreenBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class loginScreen : AppCompatActivity() {

    lateinit var bindiing: ActivityLoginScreenBinding

    lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindiing = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(bindiing.root)


        bindiing.tVSignIn.setOnClickListener {

            intent = Intent(this, SignUpScreen::class.java)

            startActivity(intent)
        }

        bindiing.buttonLogin.setOnClickListener {

            val username = bindiing.getUserName.text.toString()
            if (username.isNotEmpty()){
                readData(username)
            }

            else{
                Toast.makeText(this, "Enter User Name", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun readData(username:String){

        database = FirebaseDatabase.getInstance().getReference("Users")

        database.child(username).get().addOnSuccessListener {

            bindiing.getUserName.text?.clear()
            if (it.exists()){

                intent = Intent(this, MainActivity::class.java)

                startActivity(intent)
            }
            else{
                Toast.makeText(this, "User Not Exist", Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener(){
            Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
        }

    }
}