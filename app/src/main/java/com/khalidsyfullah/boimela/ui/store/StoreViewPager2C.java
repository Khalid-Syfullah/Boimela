package com.khalidsyfullah.boimela.ui.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;

import java.util.ArrayList;

public class StoreViewPager2C extends Fragment {


    private RecyclerView topPublishersRecycler;
    private ArrayList<AuthorDataModel> publishersDataModels;
    private TopPublishersAdapter topPublishersAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.viewpager_store_2c, container, false);

        topPublishersRecycler = root.findViewById(R.id.store_2c_recycler_view);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        publishersDataModels = new ArrayList<>();

        publishersDataModels.add(new AuthorDataModel("চিরকুট","https://cdn.shopify.com/s/files/1/1296/0049/files/chirkutt_logo_450.png?height=628&pad_color=ffffff&v=1546845363&width=1200"));
        publishersDataModels.add(new AuthorDataModel("বিভাস","https://ds.rokomari.store/rokomari110/company/06df811a0a54_67.jpg"));
        publishersDataModels.add(new AuthorDataModel("ছায়াবীথি","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwKEakbr_9Ps9Z6vRnBcUpkMkvla318TaiX_RJ8Y4UNJRfLJYG6R_fBjbqQVJCSvKCcf0&usqp=CAU"));
        publishersDataModels.add(new AuthorDataModel("আনন্দ","https://play-lh.googleusercontent.com/oIpsql26FPp1xqGj5L8N8OoLF7Nx7RsCWyEMv1275NocR-FsupjsCKf0L97xZIvdlA"));
        publishersDataModels.add(new AuthorDataModel("চিরকুট","https://cdn.shopify.com/s/files/1/1296/0049/files/chirkutt_logo_450.png?height=628&pad_color=ffffff&v=1546845363&width=1200"));
        publishersDataModels.add(new AuthorDataModel("বিভাস","https://ds.rokomari.store/rokomari110/company/06df811a0a54_67.jpg"));
        publishersDataModels.add(new AuthorDataModel("ছায়াবীথি","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwKEakbr_9Ps9Z6vRnBcUpkMkvla318TaiX_RJ8Y4UNJRfLJYG6R_fBjbqQVJCSvKCcf0&usqp=CAU"));
        publishersDataModels.add(new AuthorDataModel("আনন্দ","https://play-lh.googleusercontent.com/oIpsql26FPp1xqGj5L8N8OoLF7Nx7RsCWyEMv1275NocR-FsupjsCKf0L97xZIvdlA"));
        publishersDataModels.add(new AuthorDataModel("চিরকুট","https://cdn.shopify.com/s/files/1/1296/0049/files/chirkutt_logo_450.png?height=628&pad_color=ffffff&v=1546845363&width=1200"));
        publishersDataModels.add(new AuthorDataModel("বিভাস","https://ds.rokomari.store/rokomari110/company/06df811a0a54_67.jpg"));
        publishersDataModels.add(new AuthorDataModel("ছায়াবীথি","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwKEakbr_9Ps9Z6vRnBcUpkMkvla318TaiX_RJ8Y4UNJRfLJYG6R_fBjbqQVJCSvKCcf0&usqp=CAU"));
        publishersDataModels.add(new AuthorDataModel("আনন্দ","https://play-lh.googleusercontent.com/oIpsql26FPp1xqGj5L8N8OoLF7Nx7RsCWyEMv1275NocR-FsupjsCKf0L97xZIvdlA"));
        publishersDataModels.add(new AuthorDataModel("চিরকুট","https://cdn.shopify.com/s/files/1/1296/0049/files/chirkutt_logo_450.png?height=628&pad_color=ffffff&v=1546845363&width=1200"));
        publishersDataModels.add(new AuthorDataModel("বিভাস","https://ds.rokomari.store/rokomari110/company/06df811a0a54_67.jpg"));
        publishersDataModels.add(new AuthorDataModel("ছায়াবীথি","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwKEakbr_9Ps9Z6vRnBcUpkMkvla318TaiX_RJ8Y4UNJRfLJYG6R_fBjbqQVJCSvKCcf0&usqp=CAU"));
        publishersDataModels.add(new AuthorDataModel("আনন্দ","https://play-lh.googleusercontent.com/oIpsql26FPp1xqGj5L8N8OoLF7Nx7RsCWyEMv1275NocR-FsupjsCKf0L97xZIvdlA"));

        topPublishersRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));
        topPublishersAdapter = new TopPublishersAdapter(getActivity(), publishersDataModels);
        topPublishersRecycler.setAdapter(topPublishersAdapter);
    }
}
