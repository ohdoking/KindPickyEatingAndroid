package com.yapp.kindpickyeatingandroid.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.activity.RestaurantDetailActivity;
import com.yapp.kindpickyeatingandroid.dto.MapRestaurantDto;
import com.yapp.kindpickyeatingandroid.dto.MapRestaurantListDto;
import com.yapp.kindpickyeatingandroid.network.KindPickyEatingClient;
import com.yapp.kindpickyeatingandroid.service.KindPickyEactingService;
import com.yapp.kindpickyeatingandroid.util.GpsInfo;
import com.yapp.kindpickyeatingandroid.util.LogsAdapter;
import com.yapp.kindpickyeatingandroid.util.SampleAPIAS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OneFragment extends Fragment implements OnMapReadyCallback,GoogleMap.OnCameraChangeListener,
        GoogleMap.OnMapClickListener, GoogleMap.OnCameraMoveListener,
        GoogleMap.OnInfoWindowClickListener {

    SupportMapFragment mapFragment;
    SupportMapFragment fragment;
    private static View view;
    private GpsInfo gps;
    private String myLat;
    private String myLon;
    GoogleMap googleMap;
    private RecyclerView logsRecyclerView;
    private HashMap<Marker, Integer> mHashMap = new HashMap<Marker, Integer>();
    private final LogsAdapter adapter = new LogsAdapter();

    final static double myLatitude = 37.5102747;
    final static double myLongitude = 127.04381669999998;

    ArrayList<MapRestaurantDto> smp;
    List<MapRestaurantDto> smp2;
    KindPickyEactingService kindPickyEactingService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//		View view = inflater.inflate(R.layout.onefragmentragment, container, false);
        KindPickyEatingClient kindPickyEatingClient = new KindPickyEatingClient();
        kindPickyEactingService = kindPickyEatingClient.getKindPickyEactingService();

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.onefragment, container, false);
        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }

        myLat = String.valueOf(myLatitude);
        myLon = String.valueOf(myLongitude);

//        Toast.makeText(
//                getContext(),
//                "당신의 위치 - \n위도: " +  myLatitude + "\n경도: " + myLongitude,
//                Toast.LENGTH_LONG).show();

        SampleAPIAS s = new SampleAPIAS();
        try {
            smp = s.execute(myLat, myLon).get();
            //마커로 넘기기





        } catch (Exception e) {

        }

        Call<MapRestaurantListDto> mapdt = kindPickyEactingService.map(myLat, myLon);
        try {
            mapdt.enqueue(new Callback<MapRestaurantListDto>() {


                @Override
                public void onResponse(Call<MapRestaurantListDto> call, Response<MapRestaurantListDto> response) {
                    smp2 = response.body().getList();
                    Log.i("tset",smp.get(0).getName());
                    Log.i("tset",smp.get(1).getName());

                }

                @Override
                public void onFailure(Call<MapRestaurantListDto> call, Throwable t) {
                    Log.i("tset","fail");

                }
                // smp = s.execute(myLat, myLon).get();
                //마커로 넘기기


            });
        } catch (Exception e) {

        }



//		mapFragment = (SupportMapFragment)getFragmentManager()
//				.findFragmentById(R.id.map);


        FragmentManager fm = getChildFragmentManager();
        fragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (fragment == null) {
            fragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, fragment).commit();
            fragment.getMapAsync(OneFragment.this);

        }

        return view;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        logsRecyclerView = (RecyclerView) view.findViewById(R.id.logs);
        ((LinearLayoutManager) logsRecyclerView.getLayoutManager()).setReverseLayout(true);
        logsRecyclerView.setAdapter(adapter);


        for( int i  = 0; i < smp.size(); i++){
            BitmapDescriptor bitmapDescriptor;
            if(0 == i%3){
                bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.marker_clk);
            }
            else if(1 == i%3){
                bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.marker1);
            }
            else{
                bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.marker);
            }
            Marker marker = googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(smp.get(i).getLatatitue(), smp.get(i).getLongitute()))
                    .title(smp.get(i).getName())
                    .icon(bitmapDescriptor));

            mHashMap.put(marker, i);
        }

//        {
//
//            @Override
//            public boolean onMarkerClick(Marker arg0) {
//                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.marker);
//                appendBitmap(bm);
//                return true;
//            }
//
//        });



//        googleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(37.4629101, -122.244909))
//                .title("LinkedIn")
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
//
//        googleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(37.4629101, -122.2449094))
//                .title("Facebook")
//                .snippet("Facebook HQ: Menlo Park"))
//                .setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
//
//
//        googleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(37.3092293, -122.1136845))
//                .title("Apple"));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLatitude, myLongitude), 13));
//        googleMap.setMyLocationEnabled(true);

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            public boolean onMarkerClick(Marker marker) {
//                Toast.makeText(getContext(),marker+"",Toast.LENGTH_SHORT).show();

                int pos = mHashMap.get(marker);

//                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.marker);
                appendLog(smp.get(pos));
//                appendBitmap(bm);
//                String text = "[마커 클릭 이벤트] latitude ="
//                        + marker.getPosition().latitude + ", longitude ="
//                        + marker.getPosition().longitude;
//                Toast.makeText(getContext(), text, Toast.LENGTH_LONG)
//                        .show();
//                return false;

//                new BottomSheet.Builder(this, R.style.BottomSheet_Dialog)
//                        .title("New")
//                        .grid() // <-- important part
//                        .sheet(R.menu.menu_bottom_sheet)
//                        .listener(new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // TODO
//                            }
//                        }).show();
                return false;
            }
        });
    }


    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraMove() {
//        appendLog("Map onCameraMove triggered");
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
//        appendLog("Map onInfoWindowClick triggered with id " + marker.getId());
    }

    @Override
    public void onMapClick(LatLng latLng) {
    }
    private void appendLog(MapRestaurantDto data) {
        adapter.addString(data);
        logsRecyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    private void appendBitmap(Bitmap bitmap) {
        adapter.addBitmap(bitmap);
        logsRecyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

//    @Override
//    public boolean onMarkerClick(Marker marker) {
//        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.marker);
//        appendBitmap(bm);
//        return false;
//    }
}

