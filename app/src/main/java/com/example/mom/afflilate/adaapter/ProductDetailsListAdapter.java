package com.example.mom.afflilate.adaapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mom.afflilate.R;
import com.example.mom.afflilate.model.ProductDetailsBean;

import java.util.ArrayList;

public class ProductDetailsListAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private Context mContext;
    private ArrayList<ProductDetailsBean> mProductDetailsListBeans; // header titles

    public ProductDetailsListAdapter(Context mContext, ArrayList<ProductDetailsBean> productDetailsBeans) {
        this.mContext = mContext;
        this.mProductDetailsListBeans = productDetailsBeans;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mProductDetailsListBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ProductDetailsBean myPatientsData = mProductDetailsListBeans.get(position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.product_details_list_item, null);
            holder.mTvProductName = convertView.findViewById(R.id.tvProductName);
            holder.mTvProductPrice = convertView.findViewById(R.id.tvProductPrice);
            holder.mTvProductCategory = convertView.findViewById(R.id.tvProductCategory);
            holder.mIVProductImage = convertView.findViewById(R.id.ivProductImage);
            holder.progressBar = convertView.findViewById(R.id.progress);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTvProductName.setText(myPatientsData.getData().get(position).getProductTitle());
        holder.mTvProductPrice.setText(myPatientsData.getData().get(position).getProductLowestPrice());
        holder.mTvProductCategory.setText(myPatientsData.getData().get(position).getProductCategory());
        Glide.with(mContext)
                .load(myPatientsData.getData().get(position).getProductImage())
                .placeholder(R.mipmap.ic_launcher).into(holder.mIVProductImage);
        return convertView;
    }

    private class ViewHolder {
        TextView mTvProductName, mTvProductPrice, mTvProductCategory;
        ImageView mIVProductImage;
        ProgressBar progressBar;
    }
}
