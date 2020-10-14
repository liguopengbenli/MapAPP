package com.lig.happyplaces.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lig.happyplaces.R
import com.lig.happyplaces.models.HappyPlaceModel
import kotlinx.android.synthetic.main.activity_happy_place_detail.*
import kotlinx.android.synthetic.main.item_happy_place.*

class HappyPlaceDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_happy_place_detail)

        var happyPlaceDetailsModel : HappyPlaceModel? = null
        if(intent.hasExtra(MainActivity.EXTRA_PLACE_DETAILS)){
           happyPlaceDetailsModel = intent.getSerializableExtra(MainActivity.EXTRA_PLACE_DETAILS) as HappyPlaceModel
        }

        if(happyPlaceDetailsModel != null){
            setSupportActionBar(toolbar_happy_place_detail)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = happyPlaceDetailsModel.title

            toolbar_happy_place_detail.setNavigationOnClickListener {
                onBackPressed()
            }

            iv_place_image_detail.setImageURI(Uri.parse(happyPlaceDetailsModel.image))
            tv_description_detail.text = happyPlaceDetailsModel.description
            tv_location_detail.text = happyPlaceDetailsModel.location

        }
    }
}