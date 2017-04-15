package com.yapp.kindpickyeatingandroid.activity;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.dto.MapRestaurantDto;
import com.yapp.kindpickyeatingandroid.dto.MapRestaurantListDto;
import com.yapp.kindpickyeatingandroid.network.KindPickyEatingServerClient;
import com.yapp.kindpickyeatingandroid.service.KindPickyEatingServerService;
import com.yapp.kindpickyeatingandroid.util.GpsInfo;
import com.yapp.kindpickyeatingandroid.util.LogsAdapter;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapActivity extends BaseAppCompatActivity implements OnMapReadyCallback,GoogleMap.OnCameraChangeListener,
        GoogleMap.OnMapClickListener, GoogleMap.OnCameraMoveListener,
        GoogleMap.OnInfoWindowClickListener, SearchView.OnQueryTextListener {

    private GpsInfo gps;
    private String myLat;
    private String myLon;
    GoogleMap googleMap;
    private RecyclerView logsRecyclerView;
    private HashMap<Marker, Integer> mHashMap = new HashMap<Marker, Integer>();
    private final LogsAdapter adapter = new LogsAdapter();

    final static double myLatitude = 37.5102747;
    final static double myLongitude = 127.04381669999998;

    List<MapRestaurantDto> smp2;
    KindPickyEatingServerService kindPickyEactingService;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionbar();
        setContentView(R.layout.activity_map);

        KindPickyEatingServerClient kindPickyEatingClient = new KindPickyEatingServerClient(getApplicationContext());
        kindPickyEactingService = kindPickyEatingClient.getKindPickyEactingService();


        myLat = String.valueOf(myLatitude);
        myLon = String.valueOf(myLongitude);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    private void initActionbar(){

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.activity_map_actionbar);
        View view =getSupportActionBar().getCustomView();





        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) view.findViewById(R.id.searchView);

        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });
        searchView.setOnQueryTextListener(this);
        searchView.setIconifiedByDefault(true);
        searchView.setQueryHint("원하는 이름의 식당을 검색해주세요");
        searchView.setIconified(true);




        ImageButton filterBtn= (ImageButton)view.findViewById(R.id.action_bar_back);

        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"필터창",Toast.LENGTH_LONG).show();
            }
        });

//        ImageButton searchBtn = (ImageButton)view.findViewById(R.id.action_bar_forward);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Toast.makeText(getApplicationContext(),"검색창",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap map) {
        final GoogleMap googleMap = map;
        logsRecyclerView = (RecyclerView) findViewById(R.id.logs);
        ((LinearLayoutManager) logsRecyclerView.getLayoutManager()).setReverseLayout(true);
        logsRecyclerView.setAdapter(adapter);

        Call<MapRestaurantListDto> mapdt = kindPickyEactingService.map(myLat, myLon);
        try {
            mapdt.enqueue(new Callback<MapRestaurantListDto>() {


                @Override
                public void onResponse(Call<MapRestaurantListDto> call, Response<MapRestaurantListDto> response) {
                    smp2 = response.body().getList();

                    for( int i  = 0; i < smp2.size(); i++){
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
                                .position(new LatLng(smp2.get(i).getLatatitue(), smp2.get(i).getLongitute()))
                                .title(smp2.get(i).getName())
                                .icon(bitmapDescriptor));

                        mHashMap.put(marker, i);
                    }

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




        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLatitude, myLongitude), 13));
//        googleMap.setMyLocationEnabled(true);

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            public boolean onMarkerClick(Marker marker) {

                int pos = mHashMap.get(marker);

//                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.marker);
                appendLog(smp2.get(pos));
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

//    @Override
//    public boolean onMarkerClick(Marker marker) {
//        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.marker);
//        appendBitmap(bm);
//        return false;
//    }
}
