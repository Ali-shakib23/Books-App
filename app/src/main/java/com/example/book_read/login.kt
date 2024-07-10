package com.example.book_read

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class login : AppCompatActivity() {
    private lateinit var emailT: EditText
    private lateinit var passwordT: EditText
    private lateinit var loginbtn: Button
    private val emailPattern = ("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")
    private lateinit var mAuth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // variables that are assigned to each field by its id
        emailT = findViewById(R.id.email)
        passwordT = findViewById(R.id.password)
        loginbtn = findViewById(R.id.loginbtn)
        mAuth = FirebaseAuth.getInstance()


        //validating email and password
        loginbtn.setOnClickListener {
            val email = emailT.text.toString().trim()
            val password = passwordT.text.toString().trim()

            // email text and password is empty , an error message occurs
            if (email.isEmpty()){
                emailT.error="required"
            }
            else if (password.isEmpty()){
                passwordT.error="required"
            }
//            //if password is shorter than 8 characters
            else if(password.length<8){
                passwordT.error="weak password"
            }
//            ///this checks if the input email  matches the email standard
            else if (!email.matches(emailPattern.toRegex())) {
                Toast.makeText(applicationContext, "InValid email address",
                    Toast.LENGTH_SHORT).show()
            }
            //here, if the fields are validated , the button will take us to the homepage
            else {
                    login(email, password)
                }
        }


        //moving to the sign up page
        val signBtn = findViewById<Button>(R.id.signupbtn)

        signBtn.setOnClickListener {
            val intent = Intent(this, signupPage::class.java)
            startActivity(intent)
        }
    }
        private fun login(email: String, password: String) {
            //logic for login function of user
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val intent = Intent(this,MainActivity2::class.java)
                        finish()
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
