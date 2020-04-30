package com.nebiyu.nutrition

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list.view.*

class MainAdapter(var list: ArrayList<DataModel>): RecyclerView.Adapter<CustomViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.list, parent, false)
        return CustomViewHolder(cellForRow, list)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {


        if (list[position].name != ""){

            holder?.view?.title_txt.text = list[position].name
        }
        else{
            holder?.view?.title_txt.text = "Food " + position
        }



    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class CustomViewHolder(val view: View, var list: ArrayList<DataModel>): RecyclerView.ViewHolder(view) {

    init {
        view.setOnClickListener {
            val position = adapterPosition

            val intent = Intent(view.context, NutDetail::class.java)

            intent.putExtra("Name", list[position].name)
            intent.putExtra("Energy", list[position].energy)
            intent.putExtra("Protein", list[position].protein)
            intent.putExtra("Carbohydrates", list[position].carbohydrates)
            intent.putExtra("Carbohydrates_sugar", list[position].carbohydrates_sugar)
            intent.putExtra("Fat", list[position].fat)
            intent.putExtra("Fat_saturated", list[position].fat_saturated)
            intent.putExtra("Fibres", list[position].fat)
            intent.putExtra("Sodium", list[position].sodium)

            view.context.startActivity(intent)
        }
    }

}