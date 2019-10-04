package com.example.mom.afflilate.adaapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.mom.afflilate.R;
import com.example.mom.afflilate.activities.WebViewActivity;
import com.example.mom.afflilate.model.BannerListBean;
import com.example.mom.afflilate.utils.Utilities;

import java.util.ArrayList;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.MyViewHolder> {
    Context mContext;
    private LayoutInflater inflater;
    private ArrayList<BannerListBean.Banner> imageModelArrayList;

    public BannerAdapter(Context context, ArrayList<BannerListBean.Banner> imageModelArrayList) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.imageModelArrayList = imageModelArrayList;
    }

    @NonNull
    @Override
    public BannerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_banner, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerAdapter.MyViewHolder myViewHolder, final int position) {
        if (imageModelArrayList != null) {
            if (imageModelArrayList.size() == 1) {
                myViewHolder.bannerImageFull.setVisibility(View.VISIBLE);
                myViewHolder.bannerImage.setVisibility(View.GONE);
                Utilities.BannerWithExcImageView(mContext, imageModelArrayList.get(position).getImage()
                        , R.drawable.ic_login_top, myViewHolder.bannerImageFull, myViewHolder.progressBar);
            } else {
                myViewHolder.bannerImageFull.setVisibility(View.GONE);
                myViewHolder.bannerImage.setVisibility(View.VISIBLE);
                Utilities.BannerWithExcImageView(mContext, imageModelArrayList.get(position).getImage()
                        , R.drawable.ic_login_top, myViewHolder.bannerImage, myViewHolder.progressBar);
            }
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!imageModelArrayList.get(position).getRedirectionLink().equalsIgnoreCase("") &&
                            imageModelArrayList.get(position).getRedirectionLink() != null) {
                        Intent intent = new Intent(mContext, WebViewActivity.class);
                        intent.putExtra("titlename", imageModelArrayList.get(position).getTitle());
                        intent.putExtra("url", imageModelArrayList.get(position).getRedirectionLink());
                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView bannerImage, bannerImageFull;
        ProgressBar progressBar;

        public MyViewHolder(View itemView) {
            super(itemView);
            bannerImage = itemView.findViewById(R.id.bannerImage);
            bannerImageFull = itemView.findViewById(R.id.bannerImageSingle);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
