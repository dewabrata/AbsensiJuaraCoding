package com.juaracoding.absensi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.juaracoding.absensi.R;
import com.juaracoding.absensi.adapter.AdapterListBasic;
import com.juaracoding.absensi.adapter.AdapterTemplateAwal;
import com.juaracoding.absensi.model.DummyDataFactory;

import com.juaracoding.absensi.model.user.User;
import com.juaracoding.absensi.service.APIClient;
import com.juaracoding.absensi.service.APIInterfacesRest;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPersonel extends AppCompatActivity {
RecyclerView rv ,rv2;
List<User> lstUser,lstUser2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_personel);
        rv = (RecyclerView)findViewById(R.id.lstPersonel);
        rv2 = (RecyclerView)findViewById(R.id.lstLainnyaLah);

        callUser();

/*


        AdapterTemplateAwal toadapter = new AdapterTemplateAwal (this,new DummyDataFactory().createUser());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListPersonel.this, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(linearLayoutManager);

        rv.setAdapter(toadapter);


        AdapterListBasic toadapter2 = new AdapterListBasic (this,new DummyDataFactory().createUser());
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(ListPersonel.this, LinearLayoutManager.VERTICAL, false);
        rv2.setLayoutManager(linearLayoutManager2);

        rv2.setAdapter(toadapter2);

*/
    }

    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void callUser(){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(ListPersonel.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<List<User>> call3 = apiInterface.getUser();
        call3.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                progressDialog.dismiss();
                List<User> userList = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (userList !=null) {


                    AdapterTemplateAwal toadapter = new AdapterTemplateAwal (ListPersonel.this,userList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListPersonel.this, LinearLayoutManager.HORIZONTAL, false);
                    rv.setLayoutManager(linearLayoutManager);

                    rv.setAdapter(toadapter);




                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ListPersonel.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ListPersonel.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });




    }
}
