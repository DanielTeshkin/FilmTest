package com.example.test1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test1.DataClasses.Model


class FilmAdapter(private val elements:ArrayList<Model>): RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    class FilmViewHolder(val view: View):RecyclerView.ViewHolder(view) {
             fun bind(model: Model){
                 val title = view.findViewById<TextView>(R.id.text1)
                 val imageView = view.findViewById<ImageView>(R.id.imageView)
                 val description = view.findViewById<TextView>(R.id.texta)
                 title.text = model.text
                 description.text = model.text2
                 val i =model.image
                 Glide.with(view.context).load(i).centerCrop().into(imageView)
             }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val holder = LayoutInflater.from(parent.context).inflate(R.layout.form, parent, false)
        return FilmViewHolder(holder)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(elements[position])
    }

    override fun getItemCount(): Int {
        return elements.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addData(listItems: ArrayList<Model>) {
        val size = elements.size
                elements.addAll(listItems)
        val sizeNew = elements.size
        notifyDataSetChanged()
        notifyItemRangeChanged(size, sizeNew)
    }
}