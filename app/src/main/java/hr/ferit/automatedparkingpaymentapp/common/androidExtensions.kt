package hr.ferit.automatedparkingpaymentapp.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.model.LatLng

const val EXTRA_CAR_ID = "extra_car_id"
const val ZONE1 = 11111
const val ZONE2 = 22222
val zone1 = LatLng(45.556553, 18.653179)
val zone2 = LatLng(45.556483, 18.711843)
var zoneTag = 0


fun FragmentActivity.showFragment(
    containerId: Int,
    fragment: Fragment,
    shouldAddToBackStack: Boolean = false,
    tag: String = ""
) {
    supportFragmentManager.beginTransaction().apply {
        if (shouldAddToBackStack) {
            addToBackStack(tag)
        }
    }.replace(containerId, fragment).commitAllowingStateLoss()
}
