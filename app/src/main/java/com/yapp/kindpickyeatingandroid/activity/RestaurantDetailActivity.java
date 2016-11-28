package com.yapp.kindpickyeatingandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.adapter.ParallaxFragmentPagerAdapter;
import com.yapp.kindpickyeatingandroid.dto.NaverSearchResult;
import com.yapp.kindpickyeatingandroid.dto.NaverSearchResultItem;
import com.yapp.kindpickyeatingandroid.dto.RestaurantDetailDto;
import com.yapp.kindpickyeatingandroid.fragment.DemoRecyclerViewFragment;
import com.yapp.kindpickyeatingandroid.fragment.FirstScrollViewFragment;
import com.yapp.kindpickyeatingandroid.fragment.InformationFragment;
import com.yapp.kindpickyeatingandroid.network.KIndPickyEatingNaverClient;
import com.yapp.kindpickyeatingandroid.network.KindPickyEatingServerClient;
import com.yapp.kindpickyeatingandroid.service.KindPickyEatingNaverService;
import com.yapp.kindpickyeatingandroid.service.KindPickyEatingServerService;
import com.yapp.kindpickyeatingandroid.util.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantDetailActivity extends ParallaxViewPagerBaseActivity {

    private ImageView restaurantImage;
    private TextView restaurantName;
    private SlidingTabLayout mNavigBar;
    public RestaurantDetailDto restaurantDetailDto;
    public KindPickyEatingServerClient kindPickyEatingClient;
    public KindPickyEatingServerService kindPickyEactingService;
    public Long restaurantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        initValues();

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mNavigBar = (SlidingTabLayout) findViewById(R.id.navig_tab);
        restaurantImage = (ImageView) findViewById(R.id.restaurantImage);
        restaurantName = (TextView)findViewById(R.id.restaurantTitle);
        mHeader = findViewById(R.id.header);

        Intent in = getIntent();
        restaurantId = in.getExtras().getLong("restaurantId");

        if (savedInstanceState != null) {
            restaurantImage.setTranslationY(savedInstanceState.getFloat(IMAGE_TRANSLATION_Y));
            mHeader.setTranslationY(savedInstanceState.getFloat(HEADER_TRANSLATION_Y));
        }

        kindPickyEatingClient = new KindPickyEatingServerClient(getApplicationContext());
        kindPickyEactingService = kindPickyEatingClient.getKindPickyEactingService();



         /*
            server api 호출
         */
        Call<RestaurantDetailDto> callRestaurantDetailInfo = kindPickyEactingService.restaurantDetailInfo(restaurantId);

        callRestaurantDetailInfo.enqueue(new Callback<RestaurantDetailDto>() {
            @Override
            public void onResponse(Call<RestaurantDetailDto> call, Response<RestaurantDetailDto> response) {
                Log.i("test1",response.body().getName());
                restaurantName.setText(response.body().getName());
                Glide.with(getApplicationContext()).load(response.body().getImage()).into(restaurantImage);
                setupAdapter(response.body());

            }

            @Override
            public void onFailure(Call<RestaurantDetailDto> call, Throwable t) {

            }
        });


    }

    @Override
    protected void initValues() {
        int tabHeight = getResources().getDimensionPixelSize(R.dimen.tab_height);
        mMinHeaderHeight = getResources().getDimensionPixelSize(R.dimen.min_header_height);
        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mMinHeaderTranslation = -mMinHeaderHeight + tabHeight;

        mNumFragments = 3;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putFloat(IMAGE_TRANSLATION_Y, restaurantImage.getTranslationY());
        outState.putFloat(HEADER_TRANSLATION_Y, mHeader.getTranslationY());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void setupAdapter(RestaurantDetailDto restaurantDetailDto) {
        if (mAdapter == null) {
            mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mNumFragments, restaurantDetailDto, getApplicationContext());
        }

        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(mNumFragments);
        mNavigBar.setOnPageChangeListener(getViewPagerChangeListener());
        mNavigBar.setViewPager(mViewPager);
    }

    @Override
    protected void scrollHeader(int scrollY) {
        float translationY = Math.max(-scrollY, mMinHeaderTranslation);
        mHeader.setTranslationY(translationY);
        restaurantImage.setTranslationY(-translationY / 3);
    }

//    private int getActionBarHeight() {
//        if (mActionBarHeight != 0) {
//            return mActionBarHeight;
//        }
//
//        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB){
//            getTheme().resolveAttribute(android.R.attr.actionBarSize, mTypedValue, true);
//        } else {
//            getTheme().resolveAttribute(R.attr.actionBarSize, mTypedValue, true);
//        }
//
//        mActionBarHeight = TypedValue.complexToDimensionPixelSize(
//                mTypedValue.data, getResources().getDisplayMetrics());
//
//        return mActionBarHeight;
//    }

    private static class ViewPagerAdapter extends ParallaxFragmentPagerAdapter {
        RestaurantDetailDto restaurantDetailDto;
        Context context;

        public ViewPagerAdapter(FragmentManager fm, int numFragments, RestaurantDetailDto restaurantDetailDto, Context context) {
            super(fm, numFragments);
            this.restaurantDetailDto = restaurantDetailDto;
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position) {
                case 0:
                    fragment = FirstScrollViewFragment.newInstance(0,restaurantDetailDto);
                    break;

                case 1:
//                    fragment = DemoRecyclerViewFragment.newInstance(1);
                    fragment = InformationFragment.newInstance(1,restaurantDetailDto, context);

                    break;

                case 2:
                    fragment = DemoRecyclerViewFragment.newInstance(2);
//                    fragment = InformationFragment.newInstance(2,restaurantDetailDto, context);

                    break;

                default:
                    throw new IllegalArgumentException("Wrong page given " + position);
            }
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "식당 정보";

                case 1:
                    return "네이버 블로그 리뷰";

                case 2:
                    return "인스타그램";

                default:
                    throw new IllegalArgumentException("wrong position for the fragment in vehicle page");
            }
        }
    }
}