package com.example.book_read

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class signupPage : AppCompatActivity() {
    private lateinit var usernameText: EditText
    private lateinit var newEmailText: EditText
    private lateinit var PasswordText: EditText
    private lateinit var confirmPasswordText: EditText
    private lateinit var signUpAccount: Button
    private lateinit var mAuth:FirebaseAuth
    private lateinit var mDbref:DatabaseReference
    //email standards that needed to be entered in the email field
    private val emailPattern = ("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_page)

        // variables that are assigned to each field by its id
        usernameText=findViewById(R.id.newUsername_id)
        newEmailText=findViewById(R.id.newEmail)
        PasswordText=findViewById(R.id.newPassword)
        confirmPasswordText=findViewById(R.id.confirmPasswordId)
        mAuth = FirebaseAuth.getInstance()

        signUpAccount=findViewById(R.id.signUpAccount)


        signUpAccount.setOnClickListener {

            //assinging variables to the input that are entered in the fields
            val username=usernameText.text.toString().trim()
            val email=newEmailText.text.toString().trim()
            val password=PasswordText.text.toString().trim()
            val confirmPassword=confirmPasswordText.text.toString().trim()

            //checking if any of the fields are empty
            if (username.isEmpty() && email.isEmpty() && password.isEmpty() && confirmPassword.isEmpty()){
                Toast.makeText(applicationContext, "Fill all the fields",
                    Toast.LENGTH_SHORT).show()
            }
            //checking if the email matches the standards
            else if (!email.matches(emailPattern.toRegex())) {
                Toast.makeText(applicationContext, "inValid email address",
                    Toast.LENGTH_SHORT).show()
            }

           //checking if the password is less than 8 char, then an error message will occur
           else if (password.length<8){
                PasswordText.error="weak password"
            }

            //checking if the inputs of password and confirmed one are equal or not
            // if not an error message occurs
            else if (password!=confirmPassword){
                confirmPasswordText.error="Does not match"
            }

            //if all the above are handeld, then the button will take us to the login page
            else{
              signUp(username,email,password)
             }
        }

        //this function is for the those who already have an account
        //it takes the user from the signup page to the login Page
        val logBtn= findViewById<Button>(R.id.loginbtn)
        logBtn.setOnClickListener{
            val intent= Intent (this,login::class.java)
            startActivity(intent)
        }
    }
    private fun signUp(username: String, email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(username, email, mAuth.currentUser?.uid!!)
                    val intent = Intent(this, MainActivity2::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Some error occurred", Toast.LENGTH_SHORT).show()

                }
            }
    }

    private fun addUserToDatabase(username: String, email: String, uid: String) {
        mDbref = FirebaseDatabase.getInstance().getReference()

        mDbref.child("user").child(uid).setValue(user(username, email, uid))
    }
}