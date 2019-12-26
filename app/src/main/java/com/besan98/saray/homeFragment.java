package com.besan98.saray;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class homeFragment extends Fragment {

private RecyclerView categoryrecucle;
private categoryadapter categoryadapter;
    public homeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home2, container, false);
        categoryrecucle =view.findViewById(R.id.categoryrecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        categoryrecucle.setLayoutManager(linearLayoutManager);
        List<categorymodel> categorymodelList = new ArrayList<categorymodel>();
        categorymodelList.add(new categorymodel("Link"));
        categorymodelList.add(new categorymodel("Link"));
        categorymodelList.add(new categorymodel("Link"));
        categorymodelList.add(new categorymodel("Link"));
        categorymodelList.add(new categorymodel("Link"));
        categorymodelList.add(new categorymodel("Link"));
        categorymodelList.add(new categorymodel("Link"));
        categorymodelList.add(new categorymodel("Link"));
        categorymodelList.add(new categorymodel("Link"));
        categorymodelList.add(new categorymodel("Link"));

        return  view;
    }

}
