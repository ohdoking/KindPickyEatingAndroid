package com.yapp.kindpickyeatingandroid.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.dto.RestaurantDetailDto;
import com.yapp.kindpickyeatingandroid.util.NotifyingScrollView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstScrollViewFragment extends ScrollViewFragment {

    public static final String TAG = FirstScrollViewFragment.class.getSimpleName();

    public RestaurantDetailDto restaurantDetailDto;

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
        TextView restaurantMenuList = (TextView) view.findViewById(R.id.restaurantMenuList);
        TextView restaurantTel = (TextView) view.findViewById(R.id.restaurantTel);
        TextView restaurantAddress = (TextView) view.findViewById(R.id.restaurantAddress);
        TextView restaurantPrice = (TextView) view.findViewById(R.id.restaurantPrice);

        restaurantAddress.setText(restaurantDetailDto.getAddress());
        restaurantPrice.setText(restaurantDetailDto.getPrice());
        restaurantTel.setText(restaurantDetailDto.getTel());

        restaurantMenuList.setText(restaurantDetailDto.getMenu1() + ", " + restaurantDetailDto.getMenu2() + ", " + restaurantDetailDto.getMenu3());


        setScrollViewOnScrollListener();
        return view;
    }
}