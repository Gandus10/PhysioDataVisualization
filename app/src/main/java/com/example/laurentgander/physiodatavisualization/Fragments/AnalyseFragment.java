package com.example.laurentgander.physiodatavisualization.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.laurentgander.physiodatavisualization.Activity.MeasuresDetailActivity;
import com.example.laurentgander.physiodatavisualization.MainActivity;
import com.example.laurentgander.physiodatavisualization.Measures.Measures;
import com.example.laurentgander.physiodatavisualization.Measures.MeasuresAdapter;
import com.example.laurentgander.physiodatavisualization.R;

import java.io.Serializable;

/**
 * Created by laurent.gander on 03/11/2017.
 */

public class AnalyseFragment extends PhysioDataVisualisationFragment{

    private ListView measuresListView;
    private SearchView measureSearchView;
    private MeasuresAdapter measuresAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.analyse_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        retrieveViews(getView());
        setUpViews(getActivity());
    }

    private void retrieveViews(View view) {
        measuresListView = (ListView) view.findViewById(R.id.measuresListView);
        measureSearchView = (SearchView) view.findViewById(R.id.measureSearchView);
    }

    private void setUpViews(final FragmentActivity activity) {
        measuresAdapter = new MeasuresAdapter(activity, ((MainActivity)getActivity()).getService());
        measuresListView.setAdapter(measuresAdapter);
        measuresListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Measures measures = (Measures) measuresListView.getItemAtPosition(position);

                final Intent intent = new Intent(getActivity(), MeasuresDetailActivity.class);
                intent.putExtra( "Measure", measures );
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
                startActivity(intent);

            }
        });

        measureSearchView.setQueryHint("Measures date...");

        measureSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Filter filter = measuresAdapter.getFilter();

                if(TextUtils.isEmpty(newText))
                {
                    filter.filter(null);
                }else{
                    filter.filter(newText);
                }
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        measuresAdapter.notifyDataSetChanged();

        super.onResume();
    }

    @Override
    public void onMeasureServiceConnected() {}

    @Override
    public void onMeasureServiceDisconnected() {

    }

}
