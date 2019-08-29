package com.juaracoding.absensi.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.juaracoding.absensi.R;
import com.juaracoding.absensi.adapter.CommentAdapter;
import com.juaracoding.absensi.adapter.PostAdapter;
import com.juaracoding.absensi.application.AppController;
import com.juaracoding.absensi.model.imdb.post.Comment;
import com.juaracoding.absensi.model.imdb.post.Post;
import com.juaracoding.absensi.service.APIClient;
import com.juaracoding.absensi.service.APIInterfacesRest;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.queriable.StringQuery;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

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



                   savedb(userList);




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
                sqlQueryList();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });




    }

    public void savedb(List<Comment> comments ){

        FlowManager.getDatabase(AppController.class)
                .beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                        new ProcessModelTransaction.ProcessModel<Comment>() {
                            @Override
                            public void processModel(Comment comment, DatabaseWrapper wrapper) {

                                comment.save();



                            }

                        }).addAll(comments).build())  // add elements (can also handle multiple)
                .error(new Transaction.Error() {
                    @Override
                    public void onError(Transaction transaction, Throwable error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                })
                .success(new Transaction.Success() {
                    @Override
                    public void onSuccess(Transaction transaction) {
                        Toast.makeText(getApplicationContext(),"Data Tersimpan",Toast.LENGTH_LONG).show();
                        sqlQueryList();
                    }
                }).build().execute();


    }



    public void sqlQueryList(){

        String rawQuery = "SELECT * FROM `Comment` ";
        StringQuery<Comment> stringQuery = new StringQuery<>(Comment.class, rawQuery);
        stringQuery
                .async()
                .queryListResultCallback(new QueryTransaction.QueryResultListCallback<Comment>() {
                                             @Override
                                             public void onListQueryResult(QueryTransaction transaction, @NonNull List<Comment> models) {

                                                 setupAdapterList(models);


                                             }
                                         }


                )
                .execute();





    }


    public void setupAdapterList(List<Comment> model){

        CommentAdapter toadapter = new CommentAdapter (CommentActivity.this,model);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CommentActivity.this, LinearLayoutManager.VERTICAL, false);
        lstView.setLayoutManager(linearLayoutManager);

        lstView.setAdapter(toadapter);

    }
}
