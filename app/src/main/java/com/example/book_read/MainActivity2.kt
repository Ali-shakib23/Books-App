package com.example.book_read

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.auth.FirebaseAuth


class MainActivity2 : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference
    private lateinit var bookHomeRecyclerview : RecyclerView
    private lateinit var bookList : ArrayList<book>
    private lateinit var adapter: bookHomeAdapter
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bookHomeRecyclerview = findViewById(R.id.recycler)
        bookHomeRecyclerview.layoutManager = LinearLayoutManager(this)
        bookHomeRecyclerview.setHasFixedSize(true)
        bookList = arrayListOf<book>()

        getUserData()


    }
    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("book")

        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){
                        val book = userSnapshot.getValue(book::class.java)
                        bookList.add(book!!)

                    }
                    bookHomeRecyclerview.adapter = bookHomeAdapter(this@MainActivity2,bookList)
                }

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }


//    //this function is called only when the menu is the home screen is opened
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.mymenu, menu)
     return super.onCreateOptionsMenu(menu)
    }
//    //this function is called when any item is presses in the menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            //logout clicked
            mAuth.signOut()
            //this is a navigator
            val intent = Intent(this, login::class.java)
            finish()
            startActivity(intent)
            return true
        }
        else if(item.itemId==R.id.books){
            val intent = Intent(this, secondpage::class.java)
            finish()
            startActivity(intent)
            return true
        }
        else if(item.itemId==R.id.home){
            val intent = Intent(this, MainActivity2::class.java)
            finish()
            startActivity(intent)
            return true
        }
        return true
    }
}