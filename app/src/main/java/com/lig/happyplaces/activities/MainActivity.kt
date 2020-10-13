package com.lig.happyplaces.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lig.happyplaces.R
import com.lig.happyplaces.database.DatabaseHandler
import com.lig.happyplaces.models.HappyPlaceModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getHappyPlacesListFromLocalDb()
        fabAddHappyPlace.setOnClickListener {
            val intent = Intent(this, AddHappyPlaceActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getHappyPlacesListFromLocalDb(){
        val dbHandler = DatabaseHandler(this)
        val getHappyPlaceList: ArrayList<HappyPlaceModel> = dbHandler.getHappyPlaceList()

        if(getHappyPlaceList.size>0){
            for(i in getHappyPlaceList){
                Log.e("Title : ", i.title)
            }
        }
    }
}