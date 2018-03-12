package com.joveeinfotech.kinship.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joveeinfotech.kinship.R
import com.joveeinfotech.kinship.model.requestInnerDetails
import com.joveeinfotech.kinship.utils.Others
import com.joveeinfotech.kinship.utils.Others.DLog
import kotlinx.android.synthetic.main.all_donars_list.view.*
import kotlinx.android.synthetic.main.all_requestor_inner_list.view.*
import kotlinx.android.synthetic.main.all_requestor_list.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by shanmugarajjoveeinfo on 8/3/18.
 */
class RequestHistoryListAdapter(val getRequestResults: MutableMap<String, MutableList<requestInnerDetails>>, val rhContext: Context): RecyclerView.Adapter<RequestHistoryListAdapter.ViewHolder>() {

    var ll = getRequestResults as LinkedHashMap

    var gh: Array<String> = ll.keys.toTypedArray()


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        DLog("Shanmugam : ", "RequestHistory5")
        DLog("Shanmugam : ", "RequestHistory6")
        DLog("Shanmugam : ", "RequestHistory7 ${gh[position]} ${gh.size}")
        holder.bind(getRequestResults,gh[position])
    }


    override fun getItemCount(): Int = getRequestResults.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.all_requestor_list, parent, false)

        return ViewHolder(view,rhContext)
    }

    class ViewHolder(view : View, var rhContext: Context) : RecyclerView.ViewHolder(view) {

        var mMap = mutableMapOf<String,MutableList<requestInnerDetails>>()
        //var mList = listOf<String>()
        //var mList: MutableList<donationInnerDetails>? = null
        var requestHistoryInnerArrayList: ArrayList<requestInnerDetails>? = null
        var requestHistoryInnerListAdapter: RequestHistoryInnerListAdapter? = null

        fun bind(getRequestResults: MutableMap<String, MutableList<requestInnerDetails>>,key: String) {

            //val formatter = SimpleDateFormat("MMMMM d yyyy")
            //val date = formatter.format(Date.parse(key))
            itemView.all_requestor_list_cardView_constraintLayout_day_textView.text = "Date : $key"

            var RequestResults: List<requestInnerDetails>? = getRequestResults[key]?.toList()
            /*var i1 =1
            if(donorsResult.date in mMap.keys){
                *//*var mList = mMap[donorsResult.date]
                var df = donationInnerDetails(donorsResult.image_url,donorsResult.name,donorsResult.district)
                mList?.add(donorsResult.image_url,donorsResult.name,donorsResult.district)*//*
                Log.e("DonorsList : ", "inside if")
                mMap[donorsResult.date]?.add(donationInnerDetails(donorsResult.image_url, donorsResult.name, donorsResult.district))
            }else{
                Log.e("DonorsList : ", "inside else")
                var mList2 = mutableListOf<donationInnerDetails>()
                mList2.add(donationInnerDetails(donorsResult.image_url, donorsResult.name, donorsResult.district))
                mMap.put(donorsResult.date,mList2)
            }*/


            //if(i1 == 1){
            itemView.all_requestor_list_cardView_constraintLayout_cardView_recyclerView.setHasFixedSize(true)
            val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(rhContext)
            itemView.all_requestor_list_cardView_constraintLayout_cardView_recyclerView.layoutManager = layoutManager

            //}

            //var setList: List<donationInnerDetails>? = mMap[donorsResult.date]?.toList()
            //var details: List<donationDetails> = getTop20Result.donorList
            requestHistoryInnerArrayList = ArrayList(RequestResults)
            Log.e("RequestHtryListAdapt:","${requestHistoryInnerArrayList!!.size}")
            requestHistoryInnerListAdapter = RequestHistoryInnerListAdapter(requestHistoryInnerArrayList!!, rhContext!!)
            itemView.all_requestor_list_cardView_constraintLayout_cardView_recyclerView.adapter = requestHistoryInnerListAdapter

            //itemView.setBackgroundColor(android.graphics.Color.parseColor(colors[position % 6]))
            //itemView.setOnClickListener{ listener.onItemClick(android) }
        }

        class RequestHistoryInnerListAdapter(val getRequestResults:List<requestInnerDetails>, val rhContext: Context): RecyclerView.Adapter<RequestHistoryInnerListAdapter.ViewHolder>() {

            override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
                holder?.bind(getRequestResults[position],position)
            }

            override fun getItemCount(): Int = getRequestResults.size

            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
                val view = LayoutInflater.from(parent?.context).inflate(R.layout.all_requestor_inner_list, parent, false)
                Log.e("size",getRequestResults.size.toString())
                return ViewHolder(view)
            }

            class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

                fun bind(requestInner : requestInnerDetails,position: Int) {
                    Log.e("DonorsInnerList : ", "inside bind")
                    //Log.e("DonorsInnerListAdapter : ",)
                    itemView.all_requestor_inner_list_constraintLayout_requestorProfile_circleImageView
                    Log.e("InnerList : ",requestInner.image_url)
                    Log.e("InnerList : ",requestInner.name)
                    Log.e("InnerList : ",requestInner.district)
                    itemView.all_requestor_inner_list_constraintLayout_requestorName_textView.text = requestInner.name
                    itemView.all_requestor_inner_list_constraintLayout_hospitalName_textView.text=requestInner.hospital_name
                    itemView.all_requestor_inner_list_constraintLayout_district_textView.text = requestInner.district
                }
            }
        }
    }
}