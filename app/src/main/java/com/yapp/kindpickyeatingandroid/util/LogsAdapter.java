package com.yapp.kindpickyeatingandroid.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.activity.RestaurantDetailActivity;
import com.yapp.kindpickyeatingandroid.dto.MapRestaurantDto;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class LogsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  @IntDef({VIEW_TYPE_STRING, VIEW_TYPE_BITMAP})
  @Retention(RetentionPolicy.SOURCE)
  private @interface ViewType { }
  private static final int VIEW_TYPE_STRING = 1;
  private static final int VIEW_TYPE_BITMAP = 2;

  private final List<MapRestaurantDto> logs = new ArrayList<MapRestaurantDto>();

  public void addString(MapRestaurantDto data) {
    logs.add(data);
    notifyItemInserted(logs.size() - 1);
  }

  public void addBitmap(Bitmap bitmap) {
//    logs.add(bitmap);
    notifyItemInserted(logs.size() - 1);
  }

  public void clearLogs() {
    logs.clear();
    notifyDataSetChanged();
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    switch (viewType) {
      case VIEW_TYPE_STRING:

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_text, parent, false);

        return new StringViewHolder(v,parent.getContext());
      case VIEW_TYPE_BITMAP:
        return new BitmapViewHolder(parent);
      default:
        throw new IllegalArgumentException("Can't make ViewHolder of type " + viewType);
    }
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    final MapRestaurantDto log = logs.get(position);

    switch (holder.getItemViewType()) {
      case VIEW_TYPE_STRING:
        ((StringViewHolder) holder).bind(log);
        ((StringViewHolder) holder).linearLayout.setOnClickListener(new View.OnClickListener(){
          public void onClick(View v) {
            // perform your operations here
            Intent i = new Intent(v.getContext(), RestaurantDetailActivity.class);
            i.putExtra("restaurantId",log.getId());
            v.getContext().startActivity(i);
          }
        });
        break;
      case VIEW_TYPE_BITMAP:
//        ((BitmapViewHolder) holder).bind((Bitmap) log);
        break;
      default:
        throw new IllegalArgumentException("Can't bind view holder of type " + holder.getItemViewType());
    }
  }

  @ViewType
  @Override
  public int getItemViewType(int position) {
    Object log = logs.get(position);
    if (log instanceof MapRestaurantDto) {
      return VIEW_TYPE_STRING;
    } else if (log instanceof Bitmap) {
      return VIEW_TYPE_BITMAP;
    } else {
      throw new IllegalArgumentException("Unknown object of type " + log.getClass());
    }
  }

  @Override
  public int getItemCount() {
    return logs.size();
  }

  private static final class StringViewHolder extends RecyclerView.ViewHolder{

    public TextView tv;
    public TextView tv2;
    public LinearLayout linearLayout;
    public ImageView image;
    public Context context;
    public StringViewHolder(View view, Context context) {
      super(view);
      this.context = context;
      tv = (TextView) view.findViewById(R.id.rstr_name);
      tv2 = (TextView) view.findViewById(R.id.rstr_address);
      image = (ImageView) view.findViewById(R.id.rstr_logo);
      linearLayout = (LinearLayout) view.findViewById(R.id.recy_layout);


    }

    public void bind(MapRestaurantDto data2) {
      MapRestaurantDto data = data2;
      tv.setText(data.getName());
      tv2.setText(data.getAddress());
      Glide.with(context)
          .load(data.getThumb())
          .centerCrop()
          .into(image);
//      ((TextView) itemView).setText(string);
    }

  }

  private static final class BitmapViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public BitmapViewHolder(ViewGroup parent) {
      super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_bitmap, parent, false));
    }

    public void bind(Bitmap bitmap) {
      ((ImageView) itemView).setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View view) {
      Toast.makeText(view.getContext(), "Actvity 1", Toast.LENGTH_SHORT).show();
      Intent i = new Intent(view.getContext(), RestaurantDetailActivity.class);
      view.getContext().startActivity(i);
    }
  }
}