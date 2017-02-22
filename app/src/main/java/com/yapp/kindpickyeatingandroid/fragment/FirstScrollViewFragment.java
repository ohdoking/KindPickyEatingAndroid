package com.yapp.kindpickyeatingandroid.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.dto.MapRestaurantListDto;
import com.yapp.kindpickyeatingandroid.dto.RestaurantDetailDto;
import com.yapp.kindpickyeatingandroid.util.GpsInfo;
import com.yapp.kindpickyeatingandroid.util.NotifyingScrollView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstScrollViewFragment extends ScrollViewFragment implements OnMapReadyCallback{

    public static final String TAG = FirstScrollViewFragment.class.getSimpleName();

    public RestaurantDetailDto restaurantDetailDto;

    SupportMapFragment mapFragment;
    SupportMapFragment fragment;
    GoogleMap googleMap;

    public static Fragment newInstance(int position, RestaurantDetailDto restaurantDetailDto) {
        FirstScrollViewFragment fragment = new FirstScrollViewFragment(restaurantDetailDto);
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public FirstScrollViewFragment(RestaurantDetailDto restaurantDetailDto) {
        this.restaurantDetailDto = restaurantDetailDto;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mPosition = getArguments().getInt(ARG_POSITION);
        this.restaurantDetailDto = restaurantDetailDto;
        View view = inflater.inflate(R.layout.fragment_first_scroll_view, container, false);
        mScrollView = (NotifyingScrollView) view.findViewById(R.id.scrollview);
        TextView restaurantMenuList1 = (TextView) view.findViewById(R.id.restaurantMenuList1);
        TextView restaurantMenuList2 = (TextView) view.findViewById(R.id.restaurantMenuList2);
        TextView restaurantMenuList3 = (TextView) view.findViewById(R.id.restaurantMenuList3);

        restaurantMenuList1.setText(restaurantDetailDto.getMenu1() );
        restaurantMenuList2.setText(restaurantDetailDto.getMenu2() );
        restaurantMenuList3.setText(restaurantDetailDto.getMenu3() );

        FragmentManager fm = getChildFragmentManager();
        fragment = (SupportMapFragment) fm.findFragmentById(R.id.detailMap);
        if (fragment == null) {
            fragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.detailMap, fragment).commit();
            fragment.getMapAsync(FirstScrollViewFragment.this);
        }

        setScrollViewOnScrollListener();
        return view;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        final GoogleMap googleMap = map;

        BitmapDescriptor bitmapDescriptor;
        bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.marker_clk);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        Marker marker = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(restaurantDetailDto.getLatitute(), restaurantDetailDto.getLongitute()))
                .title(restaurantDetailDto.getName())
                .icon(bitmapDescriptor));


        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(restaurantDetailDto.getLatitute(), restaurantDetailDto.getLongitute()), 17));

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
//                googleMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
//                googleMap.getUiSettings().setMapToolbarEnabled(true);

                //google Map으로 연동시켜서 길찾기를 할 수 있다
                double latitude = restaurantDetailDto.getLatitute();
                double longitude = restaurantDetailDto.getLongitute();
                String label = restaurantDetailDto.getName();
                String uriBegin = "geo:" + latitude + "," + longitude;
                String query = latitude + "," + longitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                startActivity(intent);


                return true;
            }
        });
    }

}