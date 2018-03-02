package com.joveeinfotech.kinship.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.joveeinfotech.kinship.R
import com.joveeinfotech.kinship.contract.KinshipContract.*
import com.joveeinfotech.kinship.model.DistrictResult
import com.joveeinfotech.kinship.model.SearchHospitalResult
import com.joveeinfotech.kinship.presenter.SomeOneRequestFragmentPresenterImpl
import com.joveeinfotech.kinship.utils.CustomToast
import kotlinx.android.synthetic.main.fragment_some_one_request.*
import kotlinx.android.synthetic.main.fragment_some_one_request.view.*
import kotlinx.android.synthetic.main.fragment_user_request.*
import kotlinx.android.synthetic.main.fragment_user_request.view.*

class SomeOneRequestFragment : Fragment(), SomeOneRequestFragmentView {

    var mContext : Context? = null
    var view1 : View? = null

    var search_blood_group : String? = null
    var relationship : String? = null
    var search_unit : String? = null
    var search_district : String? = null
    var search_hospital : String? = null

    var someOneRequestFragmentPresenter : SomeOneRequestFragmentPresenterImpl? = null

    override fun onAttach(context: Context) {
        this.mContext = context
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        view1 = inflater.inflate(R.layout.fragment_some_one_request, container, false)

        someOneRequestFragmentPresenter = SomeOneRequestFragmentPresenterImpl(this,mContext)

        var categories = ArrayList<String>()
        categories.add("Select Blood Group")
        categories.add("AB+")
        categories.add("AB-")
        categories.add("A+")
        categories.add("A-")
        categories.add("B+")
        categories.add("B-")
        categories.add("O+")
        categories.add("O-")

        var categories_of_relation = ArrayList<String>()
        categories.add("Select Relation")
        categories.add("Relation")
        categories.add("Friend")
        categories.add("Unknown")

        val dataAdapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_item, categories)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        view1?.spinner_some_one_search_blood_group?.adapter = dataAdapter

        val dataAdapter_of_relation = ArrayAdapter(mContext, android.R.layout.simple_spinner_item, categories_of_relation)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        view1?.spinner_some_one_relationship?.adapter = dataAdapter

        view1?.spinner_some_one_search_blood_group?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                search_blood_group = categories.get(position).toString()
                //Toast.makeText(applicationContext,blood_group,Toast.LENGTH_LONG).show()
            }
        }

        view1?.spinner_some_one_relationship?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                relationship = categories_of_relation.get(position).toString()
                //Toast.makeText(applicationContext,blood_group,Toast.LENGTH_LONG).show()
            }
        }

        view1?.button_user_search_submit?.setOnClickListener{
            if(editText_some_one_name.text.isNotEmpty() && editText_some_one_phone_number.text.isNotEmpty()
                    && search_blood_group!!.isNotEmpty() && editText_some_one_search_units.text.isNotEmpty()
                    && search_district!!.isNotEmpty() && search_hospital!!.isNotEmpty()
                    && relationship!!.isNotEmpty()) {
                someOneRequestFragmentPresenter?.sendUserRequestDetails(editText_some_one_name.text.toString(),
                        editText_some_one_phone_number.text.toString(), search_blood_group!!,
                        editText_some_one_search_units.text.toString(),
                        search_district!!, search_hospital!!, relationship!!)
            }else{
                CustomToast().alertToast(mContext!!,"Fill the all fields")
            }
        }

        return view1
    }

    override fun setDistricts(districtResult: DistrictResult) {
        val dataAdapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_item, districtResult.district)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_user_search_district.adapter=dataAdapter
        spinner_user_search_district.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                search_district= districtResult.district[position].toString()
                someOneRequestFragmentPresenter?.sendDistrictsReceiveHospitals(search_district!!)
                Toast.makeText(mContext, search_district, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun setHospitals(hospitalsList: SearchHospitalResult) {
        val dataAdapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_item, hospitalsList.hospitals)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_user_search_hospitals.adapter=dataAdapter
        spinner_user_search_hospitals.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                search_hospital= hospitalsList.hospitals[position].toString()
                //userRequestFragmentPresenter?.sendDistrictsReceiveHospitals(district!!)
                Toast.makeText(mContext, search_hospital, Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        fun newInstance() : SomeOneRequestFragment {
            return SomeOneRequestFragment()
        }
    }

}