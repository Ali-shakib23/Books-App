package com.example.book_read

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class bookAdapter( val context: Context,val bookList: ArrayList<book>) :

    RecyclerView.Adapter<bookAdapter.bookViewHolder>()
    {
        //Mainly it is called when the listView is created
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bookViewHolder {
            val view: View =LayoutInflater.from(parent.context).inflate(R.layout.book_layout2, parent, false)
            return bookViewHolder(view)
        }
        override fun onBindViewHolder(holder: bookViewHolder, position: Int) {

            //GET THE CURRENT USER FROM THE LIST OF USERS
            val currentbook = bookList[position]
            holder.textview1.text = currentbook.name
            holder.textview2.text = currentbook.author
            holder.textview3.text = currentbook.type
            holder.textview4.text = currentbook.year


           Glide.with(context).load(currentbook.image).into(holder.imageview)
        }
        override fun getItemCount(): Int {
            return bookList.size
        }
        class bookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val textview1 = itemView.findViewById<TextView>(R.id.name)
            val textview2 = itemView.findViewById<TextView>(R.id.author)
            val textview3 = itemView.findViewById<TextView>(R.id.type)
            val textview4 = itemView.findViewById<TextView>(R.id.year)
            val imageview=itemView.findViewById<ImageView>(R.id.img)


//
        }

    }



