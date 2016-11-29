package com.yapp.kindpickyeatingandroid.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.adapter.RecyclerAdapter;
import com.yapp.kindpickyeatingandroid.dto.InstagramHashTagResultItem;
import com.yapp.kindpickyeatingandroid.dto.RestaurantDetailDto;
import com.yapp.kindpickyeatingandroid.network.KindPickyEatingInstagramClient;
import com.yapp.kindpickyeatingandroid.service.KindPickyEatingInstagramService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mac004 on 2016. 11. 29..
 */
public class InstagramInformatinFragment extends RecyclerViewFragment {

    public static final String TAG = DemoRecyclerViewFragment.class.getSimpleName();

    private LinearLayoutManager mLayoutMgr;
    public Context context;
    public RestaurantDetailDto restaurantDetailDto;

    public KindPickyEatingInstagramClient kindPickyEatingInstagramClient;
    public KindPickyEatingInstagramService kindPickyEatingInstagramService;

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

        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        setupRecyclerView();

        Call<InstagramHashTagResultItem> searchResult = kindPickyEatingInstagramService.searchHashtagRecent(restaurantDetailDto.getName());

        searchResult.enqueue(new Callback<InstagramHashTagResultItem>() {
            @Override
            public void onResponse(Call<InstagramHashTagResultItem> call, Response<InstagramHashTagResultItem> response) {
                Response<InstagramHashTagResultItem> response2 = response;

                Log.i("ohdoking-test",response2.body().toString());

            }

            @Override
            public void onFailure(Call<InstagramHashTagResultItem> call, Throwable t) {

            }
        });
        return view;
    }

    @Override
    protected void setScrollOnLayoutManager(int scrollY) {
        mLayoutMgr.scrollToPositionWithOffset(0, -scrollY);
    }

    private void setupRecyclerView() {
        mLayoutMgr = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutMgr);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
        recyclerAdapter.addItems(createItemList());
        mRecyclerView.setAdapter(recyclerAdapter);

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
