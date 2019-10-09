package com.example.mom.afflilate.adaapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mom.afflilate.R;
import com.example.mom.afflilate.model.MySearchItem;

import java.util.ArrayList;
import java.util.Locale;

public class MySearchItemArrayAdapter extends ArrayAdapter<MySearchItem> {

    private static LayoutInflater inflater = null;
    private Context mContext;
    private ArrayList<MySearchItem> mMySearchItemDataList; // header titles
    private ArrayList<MySearchItem> mMySearchItemSearchDataList;
    private int resLayout;

    public MySearchItemArrayAdapter(Context mContext, int resLay, ArrayList<MySearchItem> mMySearchItemDataList) {
        super(mContext, resLay);
        this.mContext = mContext;
        this.resLayout = resLay;
        this.mMySearchItemDataList = mMySearchItemDataList;
        this.mMySearchItemSearchDataList = new ArrayList<>();
        this.mMySearchItemSearchDataList.addAll(mMySearchItemDataList);
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mMySearchItemDataList.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(resLayout, null);
            holder.mTvMySearchItem = convertView.findViewById(R.id.tvMySearchItem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MySearchItem mySearchItem = mMySearchItemDataList.get(position);
        holder.mTvMySearchItem.setText(mySearchItem.name);
        return convertView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mMySearchItemDataList.clear();
        if (charText.length() == 0) {
            mMySearchItemDataList.addAll(mMySearchItemSearchDataList);
        } else {
            for (MySearchItem mySearchItem : mMySearchItemSearchDataList) {
                if (mySearchItem.name.toLowerCase(Locale.getDefault()).contains(charText)) {
                    mMySearchItemDataList.add(mySearchItem);
                } else if (mySearchItem.name.toLowerCase(Locale.getDefault()).contains(charText)) {
                    mMySearchItemDataList.add(mySearchItem);
                }
            }
        }
        notifyDataSetChanged();
    }

    private class ViewHolder {
        TextView mTvMySearchItem;
    }
}