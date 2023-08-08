package com.example.contactmanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contactmanagerapp.databinding.ActivityMainBinding
import com.example.contactmanagerapp.databinding.ActivitySignUpScreenBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpScreen : AppCompatActivity() {

    lateinit var database: DatabaseReference

    lateinit var binding: ActivitySignUpScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivitySignUpScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bTnSignUp.setOnClickListener {

            val name = binding.eTname.text.toString()
            val email = binding.eTEmail.text.toString()
            val password = binding.eTPass.text.toString()
            val phoneNo = binding.eTPhone.text.toString()
//
//            val no =  phoneNo.toInt()

            if (name.isNotEmpty() || email.isNotEmpty() || password.isNotEmpty() || phoneNo.isNotEmpty()) {
                val use = UserData(name, email, password, phoneNo)
                database = FirebaseDatabase.getInstance().getReference("Users")

                database.child(name).setValue(use).addOnSuccessListener {

                    binding.eTname.text?.clear()
                    binding.eTEmail.text?.clear()
                    binding.eTPass.text?.clear()
                    binding.eTPhone.text?.clear()

//                Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                    intent = Intent(this, MainActivity::class.java)

                    startActivity(intent)
                }.addOnFailureListener() {

                    Toast.makeText(this, "Failed Registered", Toast.LENGTH_LONG).show()
                }

            }

            else{

                Toast.makeText(this, "Please Sign Up", Toast.LENGTH_LONG).show()
            }
        }


        binding.bTnLogin.setOnClickListener {

            intent = Intent(this, loginScreen::class.java)

            startActivity(intent)
        }
    }
}