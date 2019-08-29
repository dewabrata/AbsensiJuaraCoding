package com.juaracoding.absensi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juaracoding.absensi.R;
import com.juaracoding.absensi.model.imdb.post.Post;
import com.juaracoding.absensi.ui.CommentActivity;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;
    List<Post> lstPost;

    public PostAdapter(Context context , List<Post> lstUser ) {

        this.context = context;
        this.lstPost = lstUser;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        vh = new ContohViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContohViewHolder) {
            ContohViewHolder view = (ContohViewHolder) holder;
            final Post user = lstPost.get(position);

            view.txtTitle.setText(user.getTitle());
            view.txtBody.setText(user.getBody());
            view.btnComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CommentActivity.class);
                    intent.putExtra("postid",String.valueOf(user.getIdPost()));
                    context.startActivity(intent);
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return lstPost.size();
    }


    public class ContohViewHolder  extends RecyclerView.ViewHolder{


        TextView txtTitle;
        TextView txtBody;
        Button btnComment;


        public ContohViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtBody = (TextView)itemView.findViewById(R.id.txtBody);
            btnComment = (Button) itemView.findViewById(R.id.btnComment);


        }
    }

    public class ContohViewHolder2  extends RecyclerView.ViewHolder{


        TextView txtPersonel;
        ImageView imgPersonel;
        Button btnPersonel;

        public ContohViewHolder2(@NonNull View itemView) {
            super(itemView);
            txtPersonel = (TextView) itemView.findViewById(R.id.txtTitle);
            imgPersonel = (ImageView)itemView.findViewById(R.id.imgPersonel);
            btnPersonel = (Button)itemView.findViewById(R.id.btnPersonel);

        }
    }


}
