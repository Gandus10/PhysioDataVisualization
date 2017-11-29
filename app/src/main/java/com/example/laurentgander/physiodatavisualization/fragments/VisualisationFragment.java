package com.example.laurentgander.physiodatavisualization.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.laurentgander.physiodatavisualization.R;

/**
 * Created by laurent.gander on 31/10/2017.
 */

public class VisualisationFragment extends android.support.v4.app.Fragment {

    private Button TestButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.visualisation_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        retrieveViews(getView());
        //setUpViews(getActivity());
    }


    private void retrieveViews(View view) {
        TestButton = (Button) view.findViewById(R.id.button2);
    }

}
