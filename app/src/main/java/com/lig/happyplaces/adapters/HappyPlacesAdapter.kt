package com.lig.happyplaces.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lig.happyplaces.R
import com.lig.happyplaces.activities.AddHappyPlaceActivity
import com.lig.happyplaces.activities.MainActivity
import com.lig.happyplaces.models.HappyPlaceModel
import kotlinx.android.synthetic.main.activity_happy_place_detail.view.*
import kotlinx.android.synthetic.main.item_happy_place.view.*
import kotlinx.android.synthetic.main.item_happy_place.view.iv_place_image

open class HappyPlacesAdapter(
    private val context: Context,
    private var list: ArrayList<HappyPlaceModel>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var monClickListener: HappyOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_happy_place,
                parent,
                false
            )
        )
    }

    fun setMyOnClickListener(onClickListener: HappyOnClickListener){
        this.monClickListener = onClickListener // the adapter can't implement conClickListener that's why we use this glue
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        if(holder is MyViewHolder){
            holder.itemView.iv_place_image.setImageURI(Uri.parse((model.image)))
            holder.itemView.tvTitle.text = model.title
            holder.itemView.tvDescription.text = model.description

            holder.itemView.setOnClickListener {
                if(monClickListener != null){
                    monClickListener!!.onClick(position, model)
                }
            }
        }
    }

    //move to the add page to edit the item
    fun notifyEditItem(activity: Activity, position: Int, requestCode: Int){
        val intent = Intent(context, AddHappyPlaceActivity::class.java)
        intent.putExtra(MainActivity.EXTRA_PLACE_DETAILS, list[position])
        activity.startActivityForResult(intent, requestCode)
        notifyItemChanged(position)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface HappyOnClickListener {
        fun onClick(position: Int, model: HappyPlaceModel)
    }

}