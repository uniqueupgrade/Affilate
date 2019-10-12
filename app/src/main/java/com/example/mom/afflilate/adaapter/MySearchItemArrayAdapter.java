package com.example.mom.afflilate.adaapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mom.afflilate.R;
import com.example.mom.afflilate.model.MySearchItemBean;

import java.util.ArrayList;
import java.util.Locale;

public class MySearchItemArrayAdapter extends ArrayAdapter<MySearchItemBean> {

    private static LayoutInflater inflater = null;
    private Context mContext;
    private ArrayList<MySearchItemBean> mMySearchItemBeanDataList; // header titles
    private ArrayList<MySearchItemBean> mMySearchItemSearchDataListBean;
    private int resLayout;

    public MySearchItemArrayAdapter(Context mContext, int resLay, ArrayList<MySearchItemBean> mMySearchItemBeanDataList) {
        super(mContext, resLay);
        this.mContext = mContext;
        this.resLayout = resLay;
        this.mMySearchItemBeanDataList = mMySearchItemBeanDataList;
        this.mMySearchItemSearchDataListBean = new ArrayList<>();
        this.mMySearchItemSearchDataListBean.addAll(mMySearchItemBeanDataList);
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mMySearchItemBeanDataList.size();
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
        MySearchItemBean mySearchItemBean = mMySearchItemBeanDataList.get(position);
        holder.mTvMySearchItem.setText(mySearchItemBean.name);
        return convertView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mMySearchItemBeanDataList.clear();
        if (charText.length() == 0) {
            mMySearchItemBeanDataList.addAll(mMySearchItemSearchDataListBean);
        } else {
            for (MySearchItemBean mySearchItemBean : mMySearchItemSearchDataListBean) {
                if (mySearchItemBean.name.toLowerCase(Locale.getDefault()).contains(charText)) {
                    mMySearchItemBeanDataList.add(mySearchItemBean);
                } else if (mySearchItemBean.name.toLowerCase(Locale.getDefault()).contains(charText)) {
                    mMySearchItemBeanDataList.add(mySearchItemBean);
                }
            }
        }
        notifyDataSetChanged();
    }

    private class ViewHolder {
        TextView mTvMySearchItem;
    }
}