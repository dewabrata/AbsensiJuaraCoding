package com.juaracoding.absensi.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.juaracoding.absensi.R;
import com.juaracoding.absensi.model.User;

import java.util.ArrayList;
import java.util.List;


public class AdapterUserList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User> items = new ArrayList<>();

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, User obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterUserList(Context context, List<User> items) {
        this.items = items;
        ctx = context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto ;
        TextView txtNama ;
        TextView txtRegisteredDate ;
        TextView txtEmail;


        public OriginalViewHolder(View view) {
              super(view);

             txtNama = (TextView) view.findViewById(R.id.txtTitle);
             txtEmail = (TextView) view.findViewById(R.id.txtEmail);
             txtRegisteredDate = (TextView) view.findViewById(R.id.registeredDate);
           //  imgPhoto = (ImageView) view.findViewById(R.id.imgPhoto);

        }
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user, parent, false);
        vh = new OriginalViewHolder(v);
        return vh ;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;

            User user = items.get(position);

            view.txtNama.setText(user.getFirstname());
            view.txtEmail.setText(user.getEmail());
            view.txtRegisteredDate.setText(user.getRegisteredDate());
          //  view.imgPhoto.setImageResource(R.drawable.photo_female_4);




        /*    view.name.setText(p.getOrderNo());
            view.description.setText(p.getShipName());
           // Tools.displayImageRound(ctx, view.image, p.image);
            view.image.setImageResource(R.drawable.img_social_instagram);
            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, items.get(position), position);
                    }
                }
            });
            */
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}