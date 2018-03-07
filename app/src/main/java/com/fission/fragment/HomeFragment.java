package com.fission.fragment;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fission.R;
import com.fission.db.ImageItem;
import com.fission.db.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

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
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), loadImagesFromGallery());
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private List<ImageItem> loadImagesFromGallery() {

        List<ImageItem> imageItems = new ArrayList<>();

        String[] projection = {MediaStore.Images.Thumbnails._ID};
        Cursor cursor = getActivity().managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, projection, null, null, MediaStore.Images.Thumbnails._ID + "");
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);

        while (cursor.moveToNext()) {
            int imageID = cursor.getInt(columnIndex);
            ImageItem image = new ImageItem(Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, "" + imageID), "" + imageID);
            imageItems.add(image);
        }

        return imageItems;
    }
}
