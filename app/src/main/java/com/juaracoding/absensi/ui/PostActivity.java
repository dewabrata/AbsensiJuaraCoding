package com.juaracoding.absensi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.juaracoding.absensi.R;
import com.juaracoding.absensi.adapter.AdapterReqRes;
import com.juaracoding.absensi.adapter.PostAdapter;
import com.juaracoding.absensi.model.imdb.post.Post;
import com.juaracoding.absensi.model.reqres.User;
import com.juaracoding.absensi.service.APIClient;
import com.juaracoding.absensi.service.APIInterfacesRest;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    RecyclerView lstView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        setTitle("Post");
        lstView = (RecyclerView)findViewById(R.id.lstPost);



        callPost();
    }


    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void callPost(){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(PostActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<List<Post>> mulaiRequest = apiInterface.getPost();
        mulaiRequest.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                progressDialog.dismiss();
                List<Post> userList = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (userList !=null) {



                    Log.d("Hasil data" , userList.toString());

                    PostAdapter toadapter = new PostAdapter (PostActivity.this,userList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PostActivity.this, LinearLayoutManager.VERTICAL, false);
                    lstView.setLayoutManager(linearLayoutManager);

                    lstView.setAdapter(toadapter);

                 




                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(PostActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(PostActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });




    }
}
