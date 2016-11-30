package com.yapp.kindpickyeatingandroid.fragment;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.adapter.GalleryAdapter;
import com.yapp.kindpickyeatingandroid.adapter.RecyclerAdapter;
import com.yapp.kindpickyeatingandroid.dto.InstagramHashTagResult;
import com.yapp.kindpickyeatingandroid.dto.InstagramHashTagResultItem;
import com.yapp.kindpickyeatingandroid.dto.InstagramImage;
import com.yapp.kindpickyeatingandroid.dto.RestaurantDetailDto;
import com.yapp.kindpickyeatingandroid.network.KindPickyEatingInstagramClient;
import com.yapp.kindpickyeatingandroid.service.KindPickyEatingInstagramService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mac004 on 2016. 11. 29..
 *
 * 도움 받음 : http://www.androidhive.info/2016/04/android-glide-image-library-building-image-gallery-app/
 */
public class InstagramInformatinFragment extends RecyclerViewFragment {

    public static final String TAG = InstagramInformatinFragment.class.getSimpleName();

    private LinearLayoutManager mLayoutMgr;
    private RecyclerView.LayoutManager mLayoutManager;
    public Context context;
    public RestaurantDetailDto restaurantDetailDto;

    public KindPickyEatingInstagramClient kindPickyEatingInstagramClient;
    public KindPickyEatingInstagramService kindPickyEatingInstagramService;

    private ArrayList<InstagramImage> images;
    private ProgressDialog pDialog;
    private GalleryAdapter mAdapter;

    public static Fragment newInstance(int position, RestaurantDetailDto restaurantDetailDto, Context context) {
        InstagramInformatinFragment fragment = new InstagramInformatinFragment(context, restaurantDetailDto);
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public InstagramInformatinFragment(Context context, RestaurantDetailDto restaurantDetailDto) {
        this.context = context;
        this.restaurantDetailDto = restaurantDetailDto;
        kindPickyEatingInstagramClient = new KindPickyEatingInstagramClient(context);
        kindPickyEatingInstagramService = kindPickyEatingInstagramClient.getKindPickyEactingService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mPosition = getArguments().getInt(ARG_POSITION);

        pDialog = new ProgressDialog(context);
        images = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_instagram_view, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerViewHeader header = (RecyclerViewHeader) view.findViewById(R.id.header);

        setupRecyclerView();
        header.attachTo(mRecyclerView);

        Log.i("ohdoking-test","dodo");
        Call<JsonObject> searchResult = kindPickyEatingInstagramService.searchHashtagRecent(restaurantDetailDto.getName().replace(" ",""));

        searchResult.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Response<JsonObject> response2 = response;
//                Log.i("ohdoking-test",response2.errorBody().toString());

                JsonArray datas = response2.body().getAsJsonArray("data");

                if(datas.size() != 0){
                    images.clear();
                    int size;
                    if(datas.size() > 6){
                        size = 6;
                    }
                    else{
                        size = datas.size();
                    }
                    for (int i = 0; i < size; i++) {
                        InstagramImage image = new InstagramImage();
                        JsonElement data = datas.get(i).getAsJsonObject().get("images");
                        JsonElement lowResolution = data.getAsJsonObject().get("low_resolution");
                        JsonElement thumbnail = data.getAsJsonObject().get("thumbnail");
                        JsonElement standardResolution = data.getAsJsonObject().get("standard_resolution");
                        String lowResolutionString = lowResolution.getAsJsonObject().get("url").toString().replaceAll("\"","");
                        String standardResolutionString = standardResolution.getAsJsonObject().get("url").toString().replaceAll("\"","");
                        String thumbnailString =thumbnail.getAsJsonObject().get("url").toString().replaceAll("\"","");
                        image.setMedium(lowResolutionString);
                        image.setBig(standardResolutionString);
                        image.setThumbnail(thumbnailString);
                        images.add(image);
                    }
                    mAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });





        mRecyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener(context, mRecyclerView, new GalleryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.i("ohdoking-test","ccc"+position);
                if(position == mAdapter.getItemCount()-1){
                    Log.i("ohdoking-test","gogo insta");

                    Uri uri = Uri.parse("http://instagram.com/explore/tags/"+restaurantDetailDto.getName());
                    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                    likeIng.setPackage("com.instagram.android");

                    try {
                        startActivity(likeIng);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://instagram.com/explore/tags/"+restaurantDetailDto.getName())));
                    }

                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        return view;
    }

    @Override
    protected void setScrollOnLayoutManager(int scrollY) {
//        mLayoutManager.smoothScrollToPosition(mRecyclerView,State.);
    }

    private void setupRecyclerView() {
//        mLayoutMgr = new LinearLayoutManager(getActivity());
//        mRecyclerView.setLayoutManager(mLayoutMgr);
//        RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
//        recyclerAdapter.addItems(createItemList());
//        mRecyclerView.setAdapter(recyclerAdapter);


        mAdapter = new GalleryAdapter(context, images);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        setRecyclerViewOnScrollListener();
    }

    private List<String> createItemList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("Item " + i);
        }
        return list;
    }
}
