package com.example.laurentgander.physiodatavisualization.fragments;

import android.content.Intent;
import android.icu.util.Measure;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import com.example.laurentgander.physiodatavisualization.MainActivity;
import com.example.laurentgander.physiodatavisualization.MeasureService;
import com.example.laurentgander.physiodatavisualization.MeasureServiceConnection;
import com.example.laurentgander.physiodatavisualization.Measures;
import com.example.laurentgander.physiodatavisualization.MeasuresAdapter;
import com.example.laurentgander.physiodatavisualization.R;

/**
 * Created by laurent.gander on 03/11/2017.
 */

public class AnalyseFragment extends Fragment implements MeasureServiceConnection{

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
    }

    private void retrieveViews(View view) {
        measuresListView = (ListView) view.findViewById(R.id.measuresListView);
        measureSearchView = (SearchView) view.findViewById(R.id.measureSearchView);

    }

    private void setUpViews(final MainActivity mainActivity) {
        measuresAdapter = new MeasuresAdapter(mainActivity, mainActivity.getService());
        measuresListView.setAdapter(measuresAdapter);
        measuresListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Measures measures = (Measures) measuresListView.getItemAtPosition(position);
                MeasuresDetailFragment measuresDetailFragment = (MeasuresDetailFragment) getFragmentManager().findFragmentById(R.id.measureDetailsFragment);

                if(measuresDetailFragment != null && measuresDetailFragment.isInLayout()){
                    measuresDetailFragment.updateDetails(measures);
                }else{
                    final Intent intent = new Intent("DATA_ACTION");
                    intent.putExtra("DATA_EXTRA", measures.getDate());
                    LocalBroadcastManager.getInstance(mainActivity).sendBroadcast(intent);
                }
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
        // Important! Refresh our list when we return to this activity (from another one)
        //measuresAdapter.notifyDataSetChanged();

        super.onResume();
    }

    @Override
    public void onMeasureServiceConnected() {setUpViews((MainActivity)getActivity());}

    @Override
    public void onMeasureServiceDisconnected() {

    }
}
