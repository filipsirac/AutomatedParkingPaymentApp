package hr.ferit.automatedparkingpaymentapp.ui.activities.maps

import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import hr.ferit.automatedparkingpaymentapp.common.EXTRA_CAR_ID
import hr.ferit.automatedparkingpaymentapp.common.zone1
import hr.ferit.automatedparkingpaymentapp.common.zone2
import hr.ferit.automatedparkingpaymentapp.common.zoneTag
import hr.ferit.automatedparkingpaymentapp.ui.activities.base.BaseActivity
import hr.ferit.automatedparkingpaymentapp.ui.fragments.payment.PaymentDialogFragment


class MapsActivity : BaseActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private var carID: Int = -1
    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.uiSettings.isZoomControlsEnabled = true
        map.setOnMarkerClickListener(this)

        setUpMap()
    }

    override fun getLayoutResourceId() = hr.ferit.automatedparkingpaymentapp.R.layout.activity_maps

    override fun setUpUi() {

        val mapFragment = supportFragmentManager
            .findFragmentById(hr.ferit.automatedparkingpaymentapp.R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)



        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        carID = intent.getIntExtra(EXTRA_CAR_ID, -1)
    }




    override fun onMarkerClick(p0: Marker?) = false

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        addMarkers()

        map.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 14f))
                calculateDistance(currentLatLng)
            }
        }

    }

    private fun payment() {
        val dialog = PaymentDialogFragment.newInstance(carID)
        dialog.show(supportFragmentManager, dialog.tag)
    }

    private fun calculateDistance(currentLatLng: LatLng) {
        var results = floatArrayOf(10F)
        Location.distanceBetween(
            currentLatLng.latitude,
            currentLatLng.longitude,
            zone1.latitude,
            zone1.longitude,
            results
        )

        var results1 = floatArrayOf(10F)
        Location.distanceBetween(
            currentLatLng.latitude,
            currentLatLng.longitude,
            zone2.latitude,
            zone2.longitude,
            results1
        )


        when {
            (results[0] < results1[0]) && (results[0] < 100F) -> {
                zoneTag = 1
                payment()
            }
            (results1[0] < results[0]) && (results1[0] < 100F) -> {
                zoneTag = 2
                payment()
            }
            else -> {
                zoneTag = 0
                payment()
            }
        }


    }


    private fun addMarkers() {
        map.addMarker(MarkerOptions().position(zone1).title("Zone1"))
        map.addMarker(MarkerOptions().position(zone2).title("Zone2"))
    }


    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }


}