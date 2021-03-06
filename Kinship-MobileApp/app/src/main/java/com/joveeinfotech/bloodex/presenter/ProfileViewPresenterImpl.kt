package com.joveeinfotech.bloodex.presenter

import android.content.Context
import com.joveeinfotech.bloodex.APICall
import com.joveeinfotech.bloodex.APIListener
import com.joveeinfotech.bloodex.helper.SharedPreferenceHelper
import com.joveeinfotech.bloodex.`object`.CommonKeys.image_url
import com.joveeinfotech.bloodex.contract.BloodExContract.*
import com.joveeinfotech.bloodex.model.profileview
import com.joveeinfotech.bloodex.utils.Others
import com.joveeinfotech.bloodex.utils.Others.DLog
import com.joveeinfotech.bloodex.view.ProfileView

/**
 * Created by shanmugarajjoveeinfo on 27/3/18.
 */
class ProfileViewPresenterImpl: APIListener, ProfileViewPresenter {

    override fun onFailure(from: Int, t: Throwable) {}

    override fun onNetworkFailure(from: Int) {}

    private var profileViewActivity:ProfileViewActivity
    var context:Context
    var networkCall : APICall? = null
    var blood_group:String?=null
    var person_id:String?=null
    constructor(view: ProfileView, context: Context, person_id:String){
        profileViewActivity=view
        this.context=context
        this.person_id=person_id
        initpresenter(person_id)
    }
    override fun initpresenter(person_id: String) {
        Others.DLog("message", "2")
        networkCall = APICall(context)
        Click(person_id)
    }

    override fun Click(person_id: String) {
        Others.DLog("message", "3")
        val queryParams = HashMap<String, String>()
        var access_token = SharedPreferenceHelper.getStringPreference(context, "access_token", "")
        queryParams.put("access_token", access_token!!)
        queryParams.put("person_id", person_id)
        //Log.e("MAIN ACTIVITY : ","inside button" )
        Others.DLog("message", "4")
        networkCall?.APIRequest("api/v1/donorDetails",queryParams, profileview::class.java,this, 1, "Fetching...")
    }
    override fun onSuccess(from: Int, response: Any) {
        when(from) {
            1 -> { // Home Page Contents
                val result = response as profileview
                DLog("API CALL : ", "inside Main activity and onSuccess")
                if (true) {
                    Others.DLog("message", "${result.first_name}${result.last_name}${result.image}")

                    if(result.blood_group=="1") blood_group="A+"
                    else if(result.blood_group=="2")blood_group="A-"
                    else if(result.blood_group=="3")blood_group="B+"
                    else if(result.blood_group=="4")blood_group="B-"
                    else if(result.blood_group=="5")blood_group="AB+"
                    else if(result.blood_group=="6")blood_group="AB-"
                    else if(result.blood_group=="7")blood_group="O+"
                    else blood_group="O-"
                    profileViewActivity.setViewData(result.first_name,result.last_name,"${image_url}${result.image}","${blood_group}",result.email,result.occupation,result.facebook_id,result.total_donated,result.total_request,result.last_donated_date)
                    //Log.e("API CALL : ", "inside Main activity and onSucces and if condition")
                    //Toast.makeText(applicationContext, "You are Registered ${registerResult.status}", Toast.LENGTH_SHORT).show()
                } else {
                    //snackbar(this,)
                    //snackbar(this.findViewById(android.R.id.content), "Please wait some minutes")
                    //Log.e("API CALL : ","inside Main activity and onSuccess else condition")
                    //Log.d(TAG, "Something missing")
                }
            }
        }
    }
}