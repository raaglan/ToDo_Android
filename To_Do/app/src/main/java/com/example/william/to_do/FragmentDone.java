package com.example.william.to_do;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;


public class FragmentDone extends Fragment {

    private ListView doneList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_done, container, false);

        doneList = (ListView) view.findViewById(R.id.lvDone);

        return view;
    }
}
