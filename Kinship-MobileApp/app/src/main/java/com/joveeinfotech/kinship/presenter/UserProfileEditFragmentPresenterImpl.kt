package com.joveeinfotech.kinship.presenter

import android.content.Context
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.widget.Toast
import com.joveeinfotech.kinship.APICall
import com.joveeinfotech.kinship.APIListener
import com.joveeinfotech.kinship.contract.KinshipContract.*
import com.joveeinfotech.kinship.model.*
import java.util.HashMap

/**
 * Created by shanmugarajjoveeinfo on 27/2/18.
 */
class UserProfileEditFragmentPresenterImpl(var transaction: FragmentTransaction?, var view: UserProfileEditFragmentView, var context: Context):APIListener,UserProfileEditFragmentPresenter {

    override fun onFailure(from: Int, t: Throwable) {}

    override fun onNetworkFailure(from: Int) {}

    var networkCall : APICall? = null
    var addressNetworkCall:APICall?=null
    init {
        initPresenter()
        loadProfileDetails()
    }
    override fun initPresenter() {
        networkCall = APICall(context)
    }
    override fun loadProfileDetails() {
        val queryParams = HashMap<String, String>()
        queryParams.put("phone_number", "8220127939")
        networkCall?.APIRequest("api/v1/profile", queryParams, UserProfileDisplayResult::class.java, this, 4, "Fetching...")
    }
    override fun loadCountries() {
        addressNetworkCall= APICall(context)
        val queryParams = HashMap<String, String>()
        queryParams.put("input", "country")
        Log.e("MAIN ACTIVITY : ", "inside button")
        addressNetworkCall?.APIRequest("api/v1/address", queryParams, CountryResult::class.java, this, 1, "Fetching...")
    }
    override fun sendCountryReceiveState(country: String) {
        val queryParams = HashMap<String, String>()
        queryParams.put("input", "state")
        //queryParams.put("subFieldName", country!!)
        Log.e("MAIN ACTIVITY : ", "inside button")
        addressNetworkCall?.APIRequest("api/v1/address", queryParams, StateResult::class.java, this, 2, "Fetching...")
    }
    override fun sendStateReceiveDistrict(state: String) {
        val queryParams = HashMap<String, String>()
        queryParams.put("input", "district")
        //queryParams.put("subFieldName", state!!)
        Log.e("MAIN ACTIVITY : ", "inside button")
        addressNetworkCall?.APIRequest("api/v1/address", queryParams, DistrictResult::class.java, this, 3, "Fetching...")
    }
    override fun userAddressDetails(country: String, state: String, district: String, city: String, locality: String, street: String) {
        if (country.trim().isNotEmpty() && state.trim().isNotEmpty() && district.trim().isNotEmpty() && city.trim().isNotEmpty() && locality.trim().isNotEmpty() && street.trim().isNotEmpty()){
            sendAddress(country, state, district, city, locality, street)
        } else {
            //showDialog(0) // Please fill the all the fields
            Toast.makeText(context,"Please fill the all the fields", Toast.LENGTH_LONG).show()
        }
    }
    private fun sendAddress(country: String, state: String, district: String, city: String, locality: String, street: String) {
        val queryParams = HashMap<String, String>()
        queryParams.put("country", country)
        queryParams.put("state", state)
        queryParams.put("district", district)
        queryParams.put("city", city)
        queryParams.put("locality","sdfg")
        queryParams.put("street", street)
        Log.e("MAIN ACTIVITY : ", "inside button")
        addressNetworkCall?.APIRequest("api/v6/address", queryParams, SendAddressResult::class.java, this, 4, "Sending your address...")
    }

    fun sendAddress1() {
        val queryParams = HashMap<String, String>()
        queryParams.put("country", "India")
        queryParams.put("state", "Tamilnadu")
        queryParams.put("district","Madurai")
        queryParams.put("city","Madurai")
        queryParams.put("locality", "KK Nagar")
        queryParams.put("street_name", "Vinayagar")
        Log.e("MAIN ACTIVITY : ", "inside button")
        addressNetworkCall?.APIRequest("api/v1/address", queryParams, SendAddressResult::class.java, this, 4, "Sending your address...")
    }

    override fun sendImageString(imageString: String) {
        val queryParams = HashMap<String, String>()
        queryParams.put("imageString", imageString)
        networkCall?.APIRequest("api/v1/persons", queryParams, UserProfileDisplayResult::class.java, this, 5, "Fetching...")
    }
    override fun onSuccess(from: Int, response: Any) {
        when (from) {
            1 -> { // Get Country
                /*val countryList = response as List<CountryResult>
                mCountryResult = ArrayList(countryList)
                Log.e("API CALL : ", "inside Main activity and onSuccess")

                val dataAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,mCountryResult)
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner_counry.adapter=dataAdapter

                spinner_counry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>? ) {

                    }

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        country=mCountryResult?.get(position).toString()
                        Toast.makeText(applicationContext,country,Toast.LENGTH_LONG).show()
                    }
                }*/

                Log.e("API CALL : ", "inside CountryResult API CALL and onSuccess when")
                val countryList = response as CountryResult
                // view.setCountries(countryList)
            }
            2 -> { // Get State
                Log.e("API CALL : ", "inside StateResult API CALL and onSuccess when")
                val stateList = response as StateResult
                //view.setStates(stateList)
            }
            3 -> { // Get District
                Log.e("API CALL : ", "inside DistrictResult API CALL and onSuccess when")
                val districtList = response as DistrictResult
                //view.setDistricts(districtList)
            }
            4 -> {
                val result = response as UserProfileDisplayResult
                Log.e("API CALL : ", "inside UserProfileDisplayResult API CALL and onSuccess when")
                if (true) {

                    view.setProfileDetails(result.image_url, "${result.first_name} ${result.last_name}", result.phone_number, result.date_of_birth, result.email, "${result.street_name},${result.locality},${result.city},${result.district},${result.state}")

                    //val imageView : ImageView = ImageView(this)
                    var imageUrl = result.image_url
                    //Picasso.with().load(imageUrl).into(fragment_profile_display_user_profile_image)

                    //Picasso.with(this).load(imageUrl).into(fragment_profile_display_user_profile_image)
                    //srcBitmap = BitmapFactory.

                    //srcBitmap = (imageView.drawable as BitmapDrawable).bitmap
                    // srcBitmap = BitmapFactory.decodeFile("https://130513-375933-1-raikfcquaxqncofqfm.stackpathdns.com/wp-content/uploads/2016/10/Shreya-Ghoshal-2.jpg")
                    //setCircle()


                    //var address = "${result.street_name},${result.locality},${result.city},${result.district},${result.state}"


                    Log.e("API CALL : ", "inside Main activity and onSucces and if condition")
                }

            }
            5 -> {
                Log.e("API CALL : ", "inside sendImageString  API CALL and onSuccess when")
            }
            6 -> { // Send Address
                Log.e("API CALL : ", "inside Main activity and onSuccess when")
                val addressResult = response as SendAddressResult
                if (addressResult.status) {
                    if(true){
                       /* trans?.replace(R.id.activity_user_details_frame_layout, UserAdditionalDetailsFragment.newInstance())
                        trans?.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
                        trans?.commit()*/
                    }
                    Toast.makeText(context, "Successfully Stored", Toast.LENGTH_LONG).show()
                    //val i=Intent(applicationContext,UserAdditionalDetails::class.java)
                    //startActivity(i)
                }
            }
        }
    }
}