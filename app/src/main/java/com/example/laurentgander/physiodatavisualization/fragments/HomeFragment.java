package com.example.laurentgander.physiodatavisualization.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.laurentgander.physiodatavisualization.R;

import java.util.Calendar;

/**
 * Created by laurent.gander on 01/11/2017.
 */

public class HomeFragment extends android.support.v4.app.Fragment {
    private CalendarView calendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        retrieveViews(getView());
        //setUpViews(getActivity());
    }

    private void retrieveViews(View view) {
        calendar = (CalendarView) view.findViewById(R.id.calendar);
    }
}
