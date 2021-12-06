package ar.com.wolox.android.cookbook.maps

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : WolmoFragment<MapPresenter>(), MapView, OnMapReadyCallback {

    override fun layout(): Int = R.layout.fragment_map

    override fun init() {
        val mapFragment = SupportMapFragment.newInstance()
        childFragmentManager
            .beginTransaction()
            .add(R.id.map_container, mapFragment, "map")
            .commit()
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(0.0, 0.0))
                .title("Marker")
        )
    }
}