package com.lig.happyplaces.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.AsyncTask
import java.lang.Exception
import java.lang.StringBuilder
import java.util.*

class GetAddressFromLatLng(context: Context, private val latitude: Double, private val longitude: Double)
    : AsyncTask<Void, String, String>() {


    private val geocoder : Geocoder = Geocoder(context, Locale.getDefault())
    private lateinit var mAddressListener: AddressListener

    override fun doInBackground(vararg params: Void?): String {
      try {
          val addressList: List<Address>? = geocoder.getFromLocation(latitude, longitude, 1)
          if(addressList !=null && addressList.isNotEmpty()){
              val address: Address = addressList[0]
              val sb = StringBuilder() // use stringBuilder to construct a string
              for(i in 0..address.maxAddressLineIndex){
                  sb.append(address.getAddressLine(i)).append(" ") // add all the details of address
              }
              sb.deleteCharAt(sb.length -1) //delete space end
              return sb.toString()
          }

      }catch (e: Exception){
          e.printStackTrace()
      }
        return ""
    }

    override fun onPostExecute(resultString: String?) {
        if(resultString == null){
            mAddressListener.onError()
        }else{
            mAddressListener.onAddressFound(resultString)
        }
        super.onPostExecute(resultString)
    }

    fun setAddressListener(addressListener: AddressListener){
        mAddressListener = addressListener
    }

    fun getAddressExecute(){
        execute()
    }

    interface AddressListener{
        fun onAddressFound(address: String?)
        fun onError()
    }

}