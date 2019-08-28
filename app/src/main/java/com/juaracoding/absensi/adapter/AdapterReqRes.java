package com.juaracoding.absensi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juaracoding.absensi.R;
import com.juaracoding.absensi.model.reqres.Datum;
import com.squareup.picasso.Picasso;


import java.util.List;

public class AdapterReqRes extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TIPE_PERTAMA = 1;
    private final int TIPE_KEDUA = 2;


    Context context;
    List<Datum> lstUser;

    public AdapterReqRes(Context context , List<Datum> lstUser ) {

        this.context = context;
        this.lstUser = lstUser;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;

        if(viewType == TIPE_PERTAMA) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personel, parent, false);
            vh = new ContohViewHolder(v);
        }else {
            View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user, parent, false);
            vh = new ContohViewHolder2(v2);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContohViewHolder) {
            ContohViewHolder view = (ContohViewHolder) holder;
            Datum user = lstUser.get(position);


            view.txtPersonel.setText(user.getFirstName() +user.getFirstName());
            view.btnPersonel.setText(user.getEmail());

            Picasso.get().load(user.getAvatar()).into(view.imgPersonel);



        }else{
            ContohViewHolder2 view = (ContohViewHolder2)holder;
            Datum user = lstUser.get(position);
            view.txtPersonel2.setText(user.getFirstName() +user.getFirstName());
            view.btnPersonel2.setText(user.getEmail());
            view.txtDate.setText(user.getLastName());
            Picasso.get().load(user.getAvatar()).into(view.imgPersonel2);



        }
    }

    @Override
    public int getItemCount() {
        return lstUser.size();
    }

    @Override
    public int getItemViewType(int position) {

        Datum datum = lstUser.get(position);

        if((datum.getId()% 2) == 0 ){
            return TIPE_PERTAMA;
        }else{
            return TIPE_KEDUA;
        }


    }

    public class ContohViewHolder  extends RecyclerView.ViewHolder{


        TextView txtPersonel;
        ImageView imgPersonel;
        Button btnPersonel;

        public ContohViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPersonel = (TextView) itemView.findViewById(R.id.txtPersonel);
            imgPersonel = (ImageView)itemView.findViewById(R.id.imgPersonel);
            btnPersonel = (Button)itemView.findViewById(R.id.btnPersonel);

        }
    }

    public class ContohViewHolder2  extends RecyclerView.ViewHolder{


        TextView txtPersonel2;
        ImageView imgPersonel2;
        TextView btnPersonel2;
        TextView txtDate;

        public ContohViewHolder2(@NonNull View itemView) {
            super(itemView);
            txtPersonel2 = (TextView) itemView.findViewById(R.id.txtPersonel);
            imgPersonel2 = (ImageView)itemView.findViewById(R.id.imgPhoto);
            btnPersonel2 = (TextView)itemView.findViewById(R.id.txtEmail);
            txtDate = (TextView)itemView.findViewById(R.id.registeredDate);

        }
    }


}
