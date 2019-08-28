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
import com.juaracoding.absensi.adapter.AdapterTemplateAwal;

import com.juaracoding.absensi.model.reqres.User;
import com.juaracoding.absensi.service.APIClient;
import com.juaracoding.absensi.service.APIInterfacesRest;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DewaActivity extends AppCompatActivity {


    RecyclerView lstDewa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dewa);
        lstDewa = (RecyclerView)findViewById(R.id.listDewa);


        callUser("2");



    }


    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void callUser(String page){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(DewaActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<User> mulaiRequest = apiInterface.getUserReqres(page);
        mulaiRequest.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressDialog.dismiss();
                User userList = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (userList !=null) {



                    Log.d("Hasil data" , userList.toString());

                    AdapterReqRes toadapter = new AdapterReqRes (DewaActivity.this,userList.getData());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DewaActivity.this, LinearLayoutManager.VERTICAL, false);
                    lstDewa.setLayoutManager(linearLayoutManager);

                    lstDewa.setAdapter(toadapter);




                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(DewaActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(DewaActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });




    }
}
