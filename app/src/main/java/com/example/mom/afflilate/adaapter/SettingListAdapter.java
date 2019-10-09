package com.example.mom.afflilate.adaapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mom.afflilate.R;
import com.example.mom.afflilate.utils.VectorDrawableUtilities;

public class SettingListAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private Context activity;
    private String[] titles;
    private int[] icons;

    public SettingListAdapter(Context activity, String[] titles, int[] icons) {
        this.activity = activity;
        this.titles = titles;
        this.icons = icons;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public String getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(final int i, View view, @NonNull ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.setting_list_item, null);
            holder.ivImage = view.findViewById(R.id.ivImage);
            holder.tvTitle = view.findViewById(R.id.tvTitle);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.ivImage.setImageDrawable(VectorDrawableUtilities.setVectorForPreLollipop(icons[i], activity));
        holder.tvTitle.setText(titles[i]);
        return view;
    }

    private class ViewHolder {
        ImageView ivImage;
        TextView tvTitle;
    }
}
