package com.fission.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.fission.R;
import com.fission.db.Image;
import com.fission.db.RecyclerViewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private final String image_description[] = {
            "Donut",
            "Eclair",
            "Froyo"
    };

    public final String[] image_urls = {
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/DvpvklR.png"
    };
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        initView(v);
        return v;
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ArrayList imagesArrayList = prepareData();
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(),imagesArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    private ArrayList prepareData(){

        ArrayList imageList = new ArrayList<>();
        for(int i=0;i<image_description.length;i++){
            Image image = new Image();
            image.setImageDescription(image_description[i]);
            Log.d("url",image_urls[i]);
            image.setImageUrl(image_urls[i]);
            imageList.add(image);
        }
        return imageList;
    }
}
