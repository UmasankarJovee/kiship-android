package com.joveeinfotech.kinship.presenter

import android.content.Context
import android.util.Log
import com.joveeinfotech.kinship.APICall
import com.joveeinfotech.kinship.APIListener
import com.joveeinfotech.kinship.adapter.DonorsListAdapter
import com.joveeinfotech.kinship.adapter.DonorsListAdapter1
import com.joveeinfotech.kinship.contract.KinshipContract.*
import com.joveeinfotech.kinship.model.DonationHistoryResult
import com.joveeinfotech.kinship.model.donationDetails
import com.joveeinfotech.kinship.model.donationInnerDetails
import java.util.HashMap

/**
 * Created by shanmugarajjoveeinfo on 8/2/18.
 */
class DonorsFragmentPresenterImpl : APIListener, DonorsFragmentPresenter {

    override fun onFailure(from: Int, t: Throwable) {}
    override fun onNetworkFailure(from: Int) {}

    private var donorsFragmentView : DonorsFragmentView? = null

    var mContext: Context
    var networkCall : APICall? = null

    var phone_number : String? = null

    private var mdonorsArrayList: ArrayList<donationDetails>? = null

    var donorsListAdapter : DonorsListAdapter1? = null

    constructor(view: DonorsFragmentView, context: Context){
        donorsFragmentView=view
        mContext=context
        initPresenter()
    }

    override fun initPresenter() {
        networkCall = APICall(mContext)
        loadDonorsList()
    }

    override fun loadDonorsList() {
        val queryParams = HashMap<String, String>()
        queryParams.put("getTop20", "getTop20")
        networkCall?.APIRequest("api/v1/donorList", queryParams, DonationHistoryResult::class.java, this, 1, "Sending your other details...")
    }

    override fun onSuccess(from: Int, response: Any) {

        var mMap = mutableMapOf<String,MutableList<donationInnerDetails>>()
        //var mSet = mutableSetOf<String,MutableSet<donationInnerDetails>>()
        //var mList = listOf<String>()
        //var mList: MutableList<donationInnerDetails>? = null
        var mdonorsInnerArrayList : ArrayList<donationInnerDetails>? = null
        var donorsInnerListAdapter : DonorsListAdapter.ViewHolder.DonorsInnerListAdapter? = null

        when (from) {
            1 -> { // Send Additional Details
                Log.e("API CALL : ", "inside Main activity and onSuccess when")
                val getTop20Result = response as DonationHistoryResult
                var details: List<donationDetails> = getTop20Result.donorList
                mdonorsArrayList = ArrayList(details)

                var len = mdonorsArrayList!!.size
                Log.e("DonorsFragmentPresent: ","$len")
                var i=0
                for(i in mdonorsArrayList!!){
                    if(i.date in mMap.keys){
                        var mList1: MutableList<donationInnerDetails>? = mMap[i.date]
                        //var df = donationInnerDetails(i.image_url,i.name,i.district)
                        //mList?.add(i.image_url,donorsResult.name,donorsResult.district)
                        Log.e("DonorsList : ","inside if")
                        //mList1?.add(donationInnerDetails(i.image_url,i.name,i.district))
                        mMap[i.date]?.add(donationInnerDetails(i.image_url,i.name,i.district))
                        Log.e("dgdgfs","${i.date} ${i.image_url} ${i.name} ${i.district}")
                        //mMap.put(i.date,mList1!!)
                        //mMap.replace(i.date,mList1)
                    }else{
                        Log.e("DonorsList : ","inside else")
                        var mList2 = mutableListOf<donationInnerDetails>()
                        mList2.add(donationInnerDetails(i.image_url,i.name,i.district))
                        Log.e("dgdgfs","${i.date} ${i.image_url} ${i.name} ${i.district}")
                        mMap.put(i.date,mList2)
                    }
                }

                donorsListAdapter = DonorsListAdapter1(mMap, mContext!!)
                //Log.e("")
                donorsFragmentView?.setAdapterOfDonors(donorsListAdapter)

               /* for(key in mMap){
                    donorsListAdapter
                }*/

            }
        }
    }
}