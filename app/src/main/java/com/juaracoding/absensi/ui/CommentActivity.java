package com.juaracoding.absensi.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.juaracoding.absensi.R;
import com.juaracoding.absensi.adapter.CommentAdapter;
import com.juaracoding.absensi.adapter.PostAdapter;
import com.juaracoding.absensi.model.imdb.post.Comment;
import com.juaracoding.absensi.model.imdb.post.Post;
import com.juaracoding.absensi.service.APIClient;
import com.juaracoding.absensi.service.APIInterfacesRest;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {
    String postid;
    RecyclerView lstView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        setTitle("Comment");
        lstView = (RecyclerView)findViewById(R.id.lstPost);


        postid = getIntent().getStringExtra("postid");

        if (postid!=null){
            callComment(postid);
        }

    }


    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void callComment(String postId){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(CommentActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<List<Comment>> mulaiRequest = apiInterface.getComment(postId);
        mulaiRequest.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                progressDialog.dismiss();
                List<Comment> userList = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (userList !=null) {



                    Log.d("Hasil data" , userList.toString());

                    CommentAdapter toadapter = new CommentAdapter (CommentActivity.this,userList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CommentActivity.this, LinearLayoutManager.VERTICAL, false);
                    lstView.setLayoutManager(linearLayoutManager);

                    lstView.setAdapter(toadapter);




                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(CommentActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(CommentActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });




    }
}
