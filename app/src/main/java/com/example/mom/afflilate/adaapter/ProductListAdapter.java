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

public class ProductListAdapter extends BaseAdapter {

    Context mContext;
    private LayoutInflater inflater;
    private String[] mMyProductItem;
    private int[] icons;

    public ProductListAdapter(Context activity, String[] mMyProductItem, int[] icons) {
        this.mContext = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mMyProductItem = mMyProductItem;
        this.icons = icons;
    }

    @Override
    public int getCount() {
        return mMyProductItem.length;
    }

    @Override
    public String getItem(int position) {
        return mMyProductItem[position];
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
            view = inflater.inflate(R.layout.item_product, null);
            holder.ivItem = view.findViewById(R.id.ivItem);
            holder.tvProductName = view.findViewById(R.id.tvProductName);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.ivItem.setImageDrawable(VectorDrawableUtilities.setVectorForPreLollipop(icons[i], mContext));
        holder.tvProductName.setText(mMyProductItem[i]);
        return view;
    }

    private class ViewHolder {
        ImageView ivItem;
        TextView tvProductName;
    }
}
   /* @NonNull
    @Override
    public ProductListAdapt er.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_product, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.MyViewHolder myViewHolder, final int position) {
        if (mMyProductList != null && mMyProductList.toString().length() > 0 && mMyProductList.toString().length() < position) {
            myViewHolder.mTvProductName.setText(mMyProductList[position]);
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mMyProductList.toString().length();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTvProductName;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTvProductName = itemView.findViewById(R.id.tvProductName);
        }
    }
}*/
