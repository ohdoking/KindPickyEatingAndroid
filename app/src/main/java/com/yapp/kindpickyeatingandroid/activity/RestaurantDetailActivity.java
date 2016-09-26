package com.yapp.kindpickyeatingandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.adapter.ParallaxFragmentPagerAdapter;
import com.yapp.kindpickyeatingandroid.dto.RestaurantDetailDto;
import com.yapp.kindpickyeatingandroid.dto.UserDto;
import com.yapp.kindpickyeatingandroid.fragment.DemoListViewFragment;
import com.yapp.kindpickyeatingandroid.fragment.DemoRecyclerViewFragment;
import com.yapp.kindpickyeatingandroid.fragment.FirstScrollViewFragment;
import com.yapp.kindpickyeatingandroid.fragment.SecondScrollViewFragment;
import com.yapp.kindpickyeatingandroid.network.KindPickyEatingClient;
import com.yapp.kindpickyeatingandroid.service.KindPickyEactingService;
import com.yapp.kindpickyeatingandroid.util.SlidingTabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantDetailActivity extends ParallaxViewPagerBaseActivity {

    private ImageView restaurantImage;
    private TextView restaurantName;
    private SlidingTabLayout mNavigBar;
    public RestaurantDetailDto restaurantDetailDto;
    public KindPickyEatingClient kindPickyEatingClient;
    public KindPickyEactingService kindPickyEactingService;
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

        kindPickyEatingClient = new KindPickyEatingClient();
        kindPickyEactingService = kindPickyEatingClient.getKindPickyEactingService();

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

        mNumFragments = 2;
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
            mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mNumFragments, restaurantDetailDto);
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

        public ViewPagerAdapter(FragmentManager fm, int numFragments, RestaurantDetailDto restaurantDetailDto) {
            super(fm, numFragments);
            this.restaurantDetailDto = restaurantDetailDto;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position) {
                case 0:
                    fragment = FirstScrollViewFragment.newInstance(0,restaurantDetailDto);
                    break;

                case 1:
                    fragment = DemoRecyclerViewFragment.newInstance(1);
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
                    return "리뷰";

                default:
                    throw new IllegalArgumentException("wrong position for the fragment in vehicle page");
            }
        }
    }
}