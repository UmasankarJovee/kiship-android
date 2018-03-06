package com.joveeinfotech.kinship

import android.Manifest
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.support.v4.app.ActivityCompat
import android.util.Log
import com.joveeinfotech.kinship.model.LocationResult
import com.joveeinfotech.kinship.model.SendingUserProfileEditResult
import com.joveeinfotech.kinship.model.UserAdditionalDetailsResult
import io.reactivex.disposables.Disposable
import java.util.HashMap

class SendingUserProfileEdit : Service(), APIListener{

    var lm: LocationManager? = null
    var locationListener: LocationListener? = null
    var context: Context? = this
    internal var mCompositeDisposable: Disposable? = null

    var networkCall : APICall? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        //mApiInterface= RetrofitClient.getClient()
        networkCall = APICall(this)
        var i =intent?.extras
        var field = i?.getString("field")
        var value = i?.getString("value")
        var field1 =i?.getString("field1")
        var value1=i?.getString("value1")
        if(field1 == "" && value1== "") {
            val queryParams = HashMap<String, String>()
            queryParams.put("user_id", "81")
            queryParams.put("field", field!!)
            queryParams.put("value", value!!)
            Log.e("MAIN ACTIVITY : ", "inside location true ${field}${value}")
            networkCall?.APIRequest("api/v1/profile1", queryParams, SendingUserProfileEditResult::class.java, this, 1, "Sending Location...",false)
        } else{
            val queryParams = HashMap<String, String>()
            queryParams.put("user_id", "81")
            queryParams.put("field", field!!)
            queryParams.put("value", value!!)
            queryParams.put("field1",field1!!)
            queryParams.put("value1",value1!!)
            Log.e("MAIN ACTIVITY : ", "inside location${field1}${value1}")
            networkCall?.APIRequest("api/v1/profile1", queryParams, SendingUserProfileEditResult::class.java, this, 1, "Sending Location...",false)
        }
        //intent?.putExtra("key1","value1")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onSuccess(from: Int, response: Any) {
        when (from) {
            1 -> { // Send Additional Details
                Log.e("API CALL : ", "inside Main activity and onSuccess when")
                val sendingUserProfileEditResult = response as SendingUserProfileEditResult
                if(sendingUserProfileEditResult.status){

                }
            }
        }
    }

    override fun onFailure(from: Int, t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNetworkFailure(from: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBind(intent: Intent): IBinder? {
        // TODO: Return the communication channel to the service.
        throw UnsupportedOperationException("Not yet implemented")
    }

}