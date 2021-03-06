package com.yapp.kindpickyeatingandroid.fragment;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.activity.InstagramDetailActivity;
import com.yapp.kindpickyeatingandroid.adapter.GalleryAdapter;
import com.yapp.kindpickyeatingandroid.common.KindPickyEatingConstant;
import com.yapp.kindpickyeatingandroid.dto.InstagramDetailData;
import com.yapp.kindpickyeatingandroid.dto.RestaurantDetailDto;
import com.yapp.kindpickyeatingandroid.network.KindPickyEatingInstagramClient;
import com.yapp.kindpickyeatingandroid.service.KindPickyEatingInstagramService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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

    private ArrayList<InstagramDetailData> images;
    private ProgressDialog pDialog;
    private GalleryAdapter mAdapter;

    TextView emptyView;

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

        emptyView = (TextView) view.findViewById(R.id.empty_view);

// ...



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
                images.clear();
                if(datas.size() != 0){

                    int size;
                    if(datas.size() > 6){
                        size = 6;
                    }
                    else{
                        size = datas.size();
                    }
                    for (int i = 0; i < size; i++) {
                        InstagramDetailData image = new InstagramDetailData();
                        JsonElement data = datas.get(i).getAsJsonObject().get("images");
                        JsonElement tags = datas.get(i).getAsJsonObject().get("tags");
                        JsonElement user = datas.get(i).getAsJsonObject().get("user");
                        String userName = user.getAsJsonObject().get("username").toString();
                        JsonElement lowResolution = data.getAsJsonObject().get("low_resolution");
                        JsonElement thumbnail = data.getAsJsonObject().get("thumbnail");
                        JsonElement standardResolution = data.getAsJsonObject().get("standard_resolution");
                        String lowResolutionString = lowResolution.getAsJsonObject().get("url").toString().replaceAll("\"","");
                        String standardResolutionString = standardResolution.getAsJsonObject().get("url").toString().replaceAll("\"","");
                        String thumbnailString =thumbnail.getAsJsonObject().get("url").toString().replaceAll("\"","");
                        image.setMedium(lowResolutionString);
                        image.setBig(standardResolutionString);
                        image.setThumbnail(thumbnailString);
                        image.setAuthor(userName);

                        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
                        ArrayList<String> tagsList = new Gson().fromJson(tags, listType);

                        image.setTags(tagsList);
                        images.add(image);
                    }
                    mAdapter.notifyDataSetChanged();
                }
                else{
                    if (mAdapter.isEmpty()) {
                        mRecyclerView.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    }
                    else {
                        mRecyclerView.setVisibility(View.VISIBLE);
                        emptyView.setVisibility(View.GONE);
                    }
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
                else{
                    Log.i("click","ohking");
                    Intent i = new Intent(getActivity(), InstagramDetailActivity.class);
                    i.putExtra(KindPickyEatingConstant.INSTADETAILDATA,images.get(position));
                    startActivity(i);
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
