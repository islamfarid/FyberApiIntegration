package com.example.islam.fyberapiintegration.offers.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.islam.fyberapiintegration.R;
import com.example.islam.fyberapiintegration.model.Offer;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by islam on 8/19/16.
 */
public class OffersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Offer> offers;
    Context context;

    public OffersAdapter(Context context, List<Offer> offers) {
        this.offers = offers;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_item, parent, false);
            return new VHItem(item);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Offer offer = offers.get(position);
            ((VHItem) holder).offerTeaser.setText(offer.getTeaser());
            ((VHItem) holder).offerPayout.setText(String.valueOf(offer.getPayout()));
            ((VHItem) holder).offerTitle.setText(offer.getTitle());
            Glide.with(context).load(offer.getThumbnail().getHires()).crossFade().into(((VHItem) holder).offersThumb);
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }


    class VHItem extends RecyclerView.ViewHolder {
        @Bind(R.id.offer_teaser_textview)
        TextView offerTeaser;
        @Bind(R.id.offer_title_textview)
        TextView offerTitle;
        @Bind(R.id.thumb_hires_imageview)
        ImageView offersThumb;
        @Bind(R.id.offer_payout_textview)
        TextView offerPayout;

        public VHItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}