package com.joveeinfotech.kinship.contract

import com.joveeinfotech.kinship.model.Album
import com.joveeinfotech.kinship.model.CountryResult
import com.joveeinfotech.kinship.model.DistrictResult
import com.joveeinfotech.kinship.model.StateResult

/**
 * Created by shanmugarajjoveeinfo on 8/2/18.
 */
interface KinshipContract {

    // Home Fragment
    interface HomeFragmentView{
        fun bloodDonatorInstructions()
        fun bloodRequestorInstructions()
        fun setViewData(count_of_hospitals:String,count_of_donors:String,count_of_users:String)
    }
    interface HomeFragmentPresenter{
        fun initPresenter()
        fun Click()
    }

    // Login Activity
    interface LoginView{
        fun closeActivity()
    }
    interface LoginPresenter{
        fun initPresenter()
        fun navigateActivity()
        fun callRegisterActivity()
        fun userPhoneNumberAndPassword(phone_number : String, password : String)
    }

    // Register Activity
    interface RegisterView{
        fun closeActivity()
        fun confirmOTP()
        fun confirmPassword()
    }
    interface RegisterPresenter {
        fun initPresenter()
        fun userPhoneNumberAndBloodGroup(phone_number: String, blood_group: String)
        fun OtpContent(otp: String)
        fun passwordContent(password: String, phone_number: String)
    }

    //UserDetails
    interface UserDetailsView{
        fun setNavigationFragmentValues(isCompleteProfile : Boolean, isCompleteAddress : Boolean, isCompleteAdditionalDetails : Boolean)
    }
    interface UserDetailsPresenter{
        fun initPresenter()
        fun onLoad()
    }

    // UserProfileFragment
    interface UserProfileFragmentView{}
    interface UserProfileFragmentPresenter{
        fun initPresenter()
        fun userProfileDetails(first_name : String, last_name : String, date_of_birth : String, gender : Int)
    }

    // UserAddressFragment
    interface UserAddrssFragmentView{
        fun setCountries(dataAdapter: CountryResult)
        fun setStates(stateList: StateResult)
        fun setDistricts(districtList: DistrictResult)
    }
    interface UserAddressFragmentPresenter{
        fun initPresenter()
        fun loadCountries()
        fun sendCountryReceiveState(country : String)
        fun userAddressDetails(country : String,state : String, district : String, city : String, locality : String, street : String)
        fun sendStateReceiveDistrict(state: String)
    }

    // UserAdditionalDetailsFragment
    interface UserAdditionalDetailsFragmentView{}
    interface UserAdditionalDetailsFragmentPresenter{
        fun initPresenter()
        fun moveSkiptoHome()
        fun userAdditionalDetails(category_of_person: String, additionalPhoneNumber : String, additionalEmail: String, socialProfile: String)
    }



    interface Listener {
        fun onItemClick(data: Album)
        fun displayResult(result:Boolean)
    }

}