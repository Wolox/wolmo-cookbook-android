package ar.com.wolox.android.cookbook.maps

import android.Manifest
import android.annotation.SuppressLint
import androidx.core.app.ActivityCompat
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.FragmentMapBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.permission.PermissionListener
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager

class MapFragment : WolmoFragment<FragmentMapBinding, BasePresenter<Any>>(), MapView,
    GoogleMap.OnMyLocationButtonClickListener, OnMapReadyCallback,
    ActivityCompat.OnRequestPermissionsResultCallback {

    private lateinit var map: GoogleMap
    private lateinit var clusterManager: ClusterManager<MyItem>

    private val permissionListener = object : PermissionListener() {
        @SuppressLint("MissingPermission")
        override fun onPermissionsGranted() {
            map.isMyLocationEnabled = true
        }
    }

    override fun layout(): Int = R.layout.fragment_map

    override fun init() {
        val mapFragment = SupportMapFragment.newInstance()
        childFragmentManager
            .beginTransaction()
            .add(R.id.map_container, mapFragment, "map")
            .commit()
        mapFragment.getMapAsync(this)
    }

    @SuppressLint("PotentialBehaviorOverride")
    private fun setClusterConfiguration() {
        clusterManager = ClusterManager(context, map)
        with(map) {
            setOnCameraIdleListener(clusterManager)
            setOnMarkerClickListener(clusterManager)
        }
        addClusterItems()
    }

    private fun addClusterItems() {
        var lat = defaultBuenosAiresLocationLatitude
        var lng = defaultBuenosAiresLocationLongitude
        for (i in 0..9) {
            val offset = i / 120.0
            lat += offset
            lng += offset
            val offsetItem =
                MyItem(
                    lat,
                    lng,
                    getString(R.string.map_marker_title, i),
                    getString(R.string.map_marker_snippet, i)
                )
            clusterManager.addItem(offsetItem)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        this.map = googleMap
        this.permissionManager.requestPermission(
            this,
            permissionListener,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        googleMap.setOnMyLocationButtonClickListener(this)
        with(map) {
            moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(defaultArgentinaLatitude, defaultArgentinaLongitude),
                    defaultZoom
                )
            )
            uiSettings.isMyLocationButtonEnabled = true
        }

        setClusterConfiguration()
    }

    override fun onMyLocationButtonClick(): Boolean {
        return false
    }

    inner class MyItem(
        lat: Double,
        lng: Double,
        private val title: String?,
        private val snippet: String?
    ) : ClusterItem {

        private val position: LatLng = LatLng(lat, lng)

        override fun getPosition(): LatLng {
            return position
        }

        override fun getTitle(): String? {
            return title
        }

        override fun getSnippet(): String? {
            return snippet
        }
    }

    companion object {
        private const val defaultZoom = 4f
        private const val defaultBuenosAiresLocationLatitude = -34.784393
        private const val defaultBuenosAiresLocationLongitude = -58.838345
        private const val defaultArgentinaLatitude = -40.9718805
        private const val defaultArgentinaLongitude = -61.9478275
    }
}
