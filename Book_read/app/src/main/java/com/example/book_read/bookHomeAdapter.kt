package com.example.book_read

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


 class bookHomeAdapter (val context: Context, val bookList: ArrayList<book>):
    RecyclerView.Adapter<bookHomeAdapter.bookViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bookViewHolder {
        val view: View =LayoutInflater.from(parent.context).inflate(R.layout.book_layout, parent, false)
        return bookViewHolder(view)
    }
    override fun onBindViewHolder(holder: bookViewHolder, position: Int) {

        //GET THE CURRENT USER FROM THE LIST OF USERS
        val currentbook = bookList[position]


        Glide.with(context).load(currentbook.image).into(holder.imageview)


        //when the user clicks on a name from the names list
        //then it goes to the chat screen
//        holder.itemView.setOnClickListener {
//            val intent = Intent(context, ChatActivity::class.java)
//
//            //here we pass the name and uid of the user to the chat activity class
//            intent.putExtra("name", currentUser.name)
//            intent.putExtra("uid", currentUser.uid)
//
//            context.startActivity(intent)
//        }
    }
    override fun getItemCount(): Int {
        return bookList.size
    }
    class bookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val imageview=itemView.findViewById<ImageView>(R.id.image)


//
    }
}