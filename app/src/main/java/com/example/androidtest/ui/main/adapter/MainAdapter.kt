package com.example.androidtest.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtest.R
import com.example.androidtest.data.model.AllDataResponse
import com.example.androidtest.utils.App

import kotlinx.android.synthetic.main.item_layout.view.*

class  MainAdapter(
    private val users: ArrayList<AllDataResponse>,val context: Context
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: AllDataResponse,context: Context) {
            itemView.txtname.text = user.name?:""
            if (user.callingCodes.isNotEmpty()){
                itemView.txtcodes.text = user.callingCodes[0]?:""
            }
            if (user.currencies.isNotEmpty()){
                itemView.txtcurrency.text = user.currencies[0].name?:""
            }

            itemView.container.setOnClickListener(View.OnClickListener {
                if (user.languages.isNotEmpty()){
                    App.showToast(context,user.languages[0].name?:"")
                }

            })


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position],context)

    fun addData(list: List<AllDataResponse>) {
        users.addAll(list)
    }

}