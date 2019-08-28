package com.juaracoding.absensi.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.juaracoding.absensi.R;

public class ListBasic extends AppCompatActivity {
    private ActionBar actionBar;
    private Toolbar toolbar;
    private RecyclerView listUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_basic);
    }
}
