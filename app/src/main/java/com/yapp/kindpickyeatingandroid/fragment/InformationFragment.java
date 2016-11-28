package com.yapp.kindpickyeatingandroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.adapter.NaverSearchResultListViewAdapter;
import com.yapp.kindpickyeatingandroid.dto.NaverSearchResult;
import com.yapp.kindpickyeatingandroid.dto.NaverSearchResultItem;
import com.yapp.kindpickyeatingandroid.dto.RestaurantDetailDto;
import com.yapp.kindpickyeatingandroid.network.KIndPickyEatingNaverClient;
import com.yapp.kindpickyeatingandroid.service.KindPickyEatingNaverService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mac004 on 2016. 11. 28..
 */
public class InformationFragment extends ListViewFragment {

    public static final String TAG = InformationFragment.class.getSimpleName();
    public List<NaverSearchResultItem> naverSearchResultItemArrayList;
    public Context context;
    public RestaurantDetailDto restaurantDetailDto;
    public NaverSearchResultListViewAdapter naverSearchResultListViewAdapter;

    public KIndPickyEatingNaverClient kindPickyEatingNaverClient;
    public KindPickyEatingNaverService kindPickyEactingNaverService;

    public static Fragment newInstance(int position, RestaurantDetailDto restaurantDetailDto, Context context) {
        InformationFragment fragment = new InformationFragment(context, restaurantDetailDto);
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);

        return fragment;
    }

    public InformationFragment(Context context, RestaurantDetailDto restaurantDetailDto) {
        this.context = context;
        this.restaurantDetailDto = restaurantDetailDto;
        kindPickyEatingNaverClient = new KIndPickyEatingNaverClient(context);
        kindPickyEactingNaverService = kindPickyEatingNaverClient.getKindPickyEactingService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mPosition = getArguments().getInt(ARG_POSITION);

        View view = inflater.inflate(R.layout.information_second_list_view, container, false);
        mListView = (ListView) view.findViewById(R.id.naverSearchList);
        View placeHolderView = inflater.inflate(R.layout.header_placeholder, mListView, false);
        mListView.addHeaderView(placeHolderView);


        setAdapter();

        setListViewOnScrollListener();
         /*
                    restaurant 이름을 받아서 Naver api 호출
                 */
        Call<NaverSearchResult> searchResult = kindPickyEactingNaverService.getBlogInfo(restaurantDetailDto.getName());

        searchResult.enqueue(new Callback<NaverSearchResult>() {
            @Override
            public void onResponse(Call<NaverSearchResult> call, Response<NaverSearchResult> response) {
                Response<NaverSearchResult> response2 = response;

                if(response.body().getItems().size() != 0){
                    naverSearchResultListViewAdapter.addList(response.body().getItems());
                }
                else{
                    //list가 없는 경우에 검색된 데이터가 없습니다라는 임의의 데이터 추가하기
                    List<NaverSearchResultItem> list = new ArrayList<NaverSearchResultItem>();
                    NaverSearchResultItem naverSearchResultItem = new NaverSearchResultItem();
                    naverSearchResultItem.setTitle("검색된 데이터가 없습니다");
                    naverSearchResultItem.setDescription("");
                    list.add(naverSearchResultItem);
                    naverSearchResultListViewAdapter.addList(list);
                }


            }

            @Override
            public void onFailure(Call<NaverSearchResult> call, Throwable t) {

            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = naverSearchResultListViewAdapter.getItem(i).getLink().replace("amp;","");
                Log.i("ohdoking-test",url);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });


        return view;
    }

    private void setAdapter() {
        if (getActivity() == null) return;

        int size = 7;
        String[] stringArray = new String[size];
        for (int i = 0; i < size; ++i) {
            stringArray[i] = "" + i;
        }

        naverSearchResultListViewAdapter = new NaverSearchResultListViewAdapter(context);

        mListView.setAdapter(naverSearchResultListViewAdapter);
    }
}