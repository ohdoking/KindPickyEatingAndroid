package com.yapp.kindpickyeatingandroid.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.dto.NaverSearchResultItem;
import com.yapp.kindpickyeatingandroid.viewHolder.NaverSearchResultViewHolder;

import java.util.ArrayList;
import java.util.List;

public class NaverSearchResultListViewAdapter extends BaseAdapter {
        private Context mContext = null;

        private List<NaverSearchResultItem> naverSearchResultItemArrayList = new ArrayList<NaverSearchResultItem>();
        
        public NaverSearchResultListViewAdapter(Context mContext) {
            super();
            this.mContext = mContext;
        }
        
        @Override
        public int getCount() {
            return naverSearchResultItemArrayList.size();
        }

        @Override
        public NaverSearchResultItem getItem(int position) {
            return naverSearchResultItemArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        
        public void addList(List<NaverSearchResultItem> naverSearchResultItemArrayList){
            this.naverSearchResultItemArrayList = naverSearchResultItemArrayList;
        }

        public void addItemList(NaverSearchResultItem naverSearchResultItem){
            naverSearchResultItemArrayList.add(naverSearchResultItem);
        }
        
        public void remove(int position){
            naverSearchResultItemArrayList.remove(position);
            dataChange();
        }

        //@TODO 정렬
        public void sort(){
        }
        
        public void dataChange(){
            notifyDataSetChanged();
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            NaverSearchResultViewHolder holder;
            if (convertView == null) {
                holder = new NaverSearchResultViewHolder();
                
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.naver_search_result_list_item, null);
                
                holder.image = (ImageView) convertView.findViewById(R.id.mImage);
                holder.title = (TextView) convertView.findViewById(R.id.searchTitle);
                holder.content = (TextView) convertView.findViewById(R.id.searchContent);
                
                convertView.setTag(holder);
            }else{
                holder = (NaverSearchResultViewHolder) convertView.getTag();
            }
            
            NaverSearchResultItem naverSearchResultItem = naverSearchResultItemArrayList.get(position);
            
//            if (naverSearchResultItem.get != null) {
//                holder.mIcon.setVisibility(View.VISIBLE);
//                holder.mIcon.setImageDrawable(mData.mIcon);
//            }else{
//                holder.mIcon.setVisibility(View.GONE);
//            }
            holder.image.setImageResource(R.mipmap.ic_launcher);
            holder.title.setText(Html.fromHtml(naverSearchResultItem.getTitle()));
            holder.content.setText(Html.fromHtml(naverSearchResultItem.getDescription()));
            
            return convertView;
        }
    }