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
import com.juaracoding.absensi.model.user.User;


import java.util.List;

public class AdapterTemplateAwal extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;
    List<User> lstUser;

    public AdapterTemplateAwal(Context context , List<User> lstUser ) {

        this.context = context;
        this.lstUser = lstUser;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personel, parent, false);
        vh = new ContohViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContohViewHolder) {
            ContohViewHolder view = (ContohViewHolder) holder;
            User user = lstUser.get(position);

            view.txtPersonel.setText(user.getUsername());
            view.btnPersonel.setText(user.getEmail());


        }
    }

    @Override
    public int getItemCount() {
        return lstUser.size();
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


        TextView txtPersonel;
        ImageView imgPersonel;
        Button btnPersonel;

        public ContohViewHolder2(@NonNull View itemView) {
            super(itemView);
            txtPersonel = (TextView) itemView.findViewById(R.id.txtPersonel);
            imgPersonel = (ImageView)itemView.findViewById(R.id.imgPersonel);
            btnPersonel = (Button)itemView.findViewById(R.id.btnPersonel);

        }
    }


}
