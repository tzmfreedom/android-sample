package com.freedom_man.standard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {
    companion object {
        fun newInstance() = MapsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_maps, container, false)
        childFragmentManager.findFragmentById(R.id.mapFragment).let {
            (it as SupportMapFragment).getMapAsync {
                it.setMinZoomPreference(14.0f)
                val ny = LatLng(40.7143528, -74.0059731)
                it.addMarker(MarkerOptions().position(ny));
                it.moveCamera(CameraUpdateFactory.newLatLng(ny))
            }
        }
        return view
    }
}
