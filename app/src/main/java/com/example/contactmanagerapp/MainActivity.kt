package com.example.contactmanagerapp

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.contactmanagerapp.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database:DatabaseReference

    lateinit var binding: ActivityMainBinding

    lateinit var dialog:Dialog
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialog = Dialog(this)

        dialog.setContentView(R.layout.alertbox)
        dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.bg_alert))
        val bTnOk = dialog.findViewById<Button>(R.id.bTnAlertOk)

        bTnOk.setOnClickListener {
            dialog.dismiss()
        }

        binding.bTnContactAdd.setOnClickListener {

            val contactName = binding.eTContactnam.text.toString()
            val contactEmail = binding.eTContactE.text.toString()
            val contactNo = binding.eTContactN.text.toString()

            if (contactName.isNotEmpty() || contactEmail.isNotEmpty() || contactNo.isNotEmpty()) {
                val userContacts = Contacts(contactName, contactEmail, contactNo)
                database = FirebaseDatabase.getInstance().getReference("Contacts")


                database.child(contactName).setValue(userContacts).addOnSuccessListener {

                    binding.eTContactnam.text?.clear()
                    binding.eTContactE.text?.clear()
                    binding.eTContactN.text?.clear()

//                Toast.makeText(this, "Contact Added", Toast.LENGTH_LONG).show()
                    dialog.show()
                }.addOnFailureListener() {

                    Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
                }
            } else{

                Toast.makeText(this, "Please First Enter Data", Toast.LENGTH_LONG).show()
            }
        }
    }
}