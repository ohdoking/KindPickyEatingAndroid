package com.yapp.kindpickyeatingandroid.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yapp.kindpickyeatingandroid.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class RestaurantDetailActivityFragment extends Fragment {

    public RestaurantDetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restaurant_detail, container, false);
    }
}
