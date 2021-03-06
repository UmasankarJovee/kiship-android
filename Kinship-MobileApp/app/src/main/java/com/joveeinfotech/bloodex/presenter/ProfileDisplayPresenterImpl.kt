package com.joveeinfotech.bloodex.presenter

import android.content.Context
import android.support.v4.app.FragmentTransaction
import com.joveeinfotech.bloodex.APICall
import com.joveeinfotech.bloodex.APIListener
import com.joveeinfotech.bloodex.`object`.CommonKeys.image_url
import com.joveeinfotech.bloodex.contract.BloodExContract.*
import com.joveeinfotech.bloodex.helper.SharedPreferenceHelper.getStringPreference
import com.joveeinfotech.bloodex.model.UserProfileDisplayResult
import com.joveeinfotech.bloodex.utils.Others.DLog
import java.util.HashMap

/**
 * Created by shanmugarajjoveeinfo on 8/2/18.
 */
class ProfileDisplayPresenterImpl : APIListener, ProfileDisplayPresenter {

    override fun onFailure(from: Int, t: Throwable) {}

    override fun onNetworkFailure(from: Int) {}

    private lateinit var profileDisplayFragmentView : ProfileDisplayView

    lateinit var mContext: Context
    var networkCall : APICall? = null
    var trans : FragmentTransaction? = null

    constructor(view: ProfileDisplayView, context: Context){
        this.trans=trans
        this.mContext=context
        profileDisplayFragmentView=view
        initPresenter()
        loadProfileDetails()
    }

    override fun initPresenter() {
        networkCall = APICall(mContext)
    }

    override fun loadProfileDetails() {
        val queryParams = HashMap<String, String>()
        var access_token = getStringPreference(mContext, "access_token", "")
        queryParams.put("access_token", access_token!!)
        queryParams.put("phone_number", "8189922043")
        networkCall?.APIRequest("api/v1/profile", queryParams, UserProfileDisplayResult::class.java, this, 1, "Setting your Password...")
    }

    override fun onSuccess(from: Int, response: Any) {
        when (from) {
            1 -> { // User Register
                val result = response as UserProfileDisplayResult
                DLog("API CALL : ", "inside Main activity and onSuccess")
                if (true) {

                    var name = "${result.first_name} ${result.last_name}"
                    var address = "${result.street_name},${result.locality},${result.city},${result.district},${result.state}"

                    profileDisplayFragmentView.setProfileDetails("${image_url}${result.image_url}",name,result.total_donated,result.total_request,result.last_donated_date,result.email,
                            result.phone_number,result.blood_group,result.date_of_birth,address)

                    //val imageView : ImageView = ImageView(this)
                    var imageUrl = result.image_url
                    //Picasso.with().load(imageUrl).into(fragment_profile_display_user_profile_image)

                    //Picasso.with(this).load(imageUrl).into(fragment_profile_display_user_profile_image)
                    //srcBitmap = BitmapFactory.

                    //srcBitmap = (imageView.drawable as BitmapDrawable).bitmap
                    // srcBitmap = BitmapFactory.decodeFile("https://130513-375933-1-raikfcquaxqncofqfm.stackpathdns.com/wp-content/uploads/2016/10/Shreya-Ghoshal-2.jpg")
                    //setCircle()


                    //var address = "${result.street_name},${result.locality},${result.city},${result.district},${result.state}"


                    DLog("API CALL : ", "inside Main activity and onSucces and if condition")
                    //Toast.makeText(applicationContext, "You are Registered ${registerResult.status}", Toast.LENGTH_SHORT).show()
                } else {
                    //snackbar(.findViewById(android.R.id.content), "Please wait some minutes")
                }
            }
        }
    }
}