package com.yapp.kindpickyeatingandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.dto.Item;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Item> data;
    private int layout;

    public Adapter(Context context, int layout, ArrayList<Item> data){
        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data=data;
        this.layout=layout;
    }

    @Override
    public int getCount(){return data.size();}

    @Override
    public String getItem(int position){return data.get(position).getName();}

    @Override
    public long getItemId(int position) {return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView=inflater.inflate(layout,parent,false);
        }

        Item item=data.get(position);

        ImageView icon=(ImageView)convertView.findViewById(R.id.rstr_logo);
        icon.setImageResource(item.getIcon());

        TextView name=(TextView)convertView.findViewById(R.id.rstr_name);
        name.setText(item.getName());

        RatingBar grade=(RatingBar)convertView.findViewById(R.id.RatingBar);
        grade.setRating(item.getGrade());

        TextView address=(TextView) convertView.findViewById(R.id.rstr_address);
        address.setText(item.getAddress());


        return convertView;
    }
}
