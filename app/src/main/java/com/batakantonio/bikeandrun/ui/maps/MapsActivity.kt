package com.batakantonio.bikeandrun.ui.maps

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.batakantonio.bikeandrun.R
import com.batakantonio.bikeandrun.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.vmadalin.easypermissions.EasyPermissions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, EasyPermissions.PermissionCallbacks  {

    private lateinit var binding: ActivityMapsBinding
    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient // real time location updates
    private var currentMarker: Marker? = null
    private var firstLocationUpdate = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        supportActionBar?.hide()

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.apply {
            isZoomControlsEnabled = true
            isZoomGesturesEnabled = true
            isMyLocationButtonEnabled = true
        }

            // Permissions helper to check or request
            handleLocationPermission()


        val sydney = LatLng(-34.0, 151.0)
        map.addMarker(MarkerOptions().position(Destination.destination).title("Marker in Los Angeles"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 20f))

    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            for (location in locationResult.locations) {
                updateCurrentLocation(location)

                // Log latitude and longitude
                Log.d("LocationUpdate", "Lat: ${location.latitude}, Lng: ${location.longitude}")
            }
        }
    }
        @SuppressLint("MissingPermission")
        private fun startLocationUpdates() {
            val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 2000L)
                .setMinUpdateIntervalMillis(1000L)
                .build()

            fusedLocationClient.requestLocationUpdates(request, locationCallback, mainLooper)
        }


        private fun updateCurrentLocation(location: Location) {
            val currentLatLng = LatLng(location.latitude, location.longitude)

            // Update user marker
            currentMarker?.remove() // Ensures that only one marker(the latest one) appears on the map
            currentMarker = map.addMarker(
                MarkerOptions().position(currentLatLng).title("You are here")
            )

            if (firstLocationUpdate) {
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 17f))
                firstLocationUpdate = false
            }
            val result = FloatArray(1)
            Location.distanceBetween(
                // Calculates distance in meters between two GPS points
                // It stores the distance in the first element of the 'result' array
                currentLatLng.latitude, currentLatLng.longitude,
                Destination.destination.latitude, Destination.destination.longitude,
                result
            )

            Log.d("LocationUpdate", "Distance to destination: ${result[0]} meters")
        }


    private fun handleLocationPermission() {
        if (Permissions.hasLocationPermission(this)) {
            enableMyLocation()
            startLocationUpdates()

        } else {
            Permissions.requestLocationPermission(this)
        }
    }

    // Already checked above
    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        map.isMyLocationEnabled = true
        Toast.makeText(this, "Already Enabled", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults)
    }

    override fun onPermissionsDenied(
        requestCode: Int,
        perms: List<String>
    ) {
        if (requestCode == Permissions.PERMISSION_LOCATION_REQUEST_CODE) {
            enableMyLocation()
        }
    }

    override fun onPermissionsGranted(
        requestCode: Int,
        perms: List<String>
    ) {
        Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}