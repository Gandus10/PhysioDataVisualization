package com.example.laurentgander.physiodatavisualization.Fragments;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.laurentgander.physiodatavisualization.Log.Log;
import com.example.laurentgander.physiodatavisualization.MainActivity;
import com.example.laurentgander.physiodatavisualization.Measures.MeasureService;
import com.example.laurentgander.physiodatavisualization.Measures.Measures;
import com.example.laurentgander.physiodatavisualization.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by laurent.gander on 31/10/2017.
 */

public class VisualisationFragment extends PhysioDataVisualisationFragment{

    private GraphView graph;
    private MeasureService measureService;
    private Spinner spinnerDate;
    private List<Measures> list;
    private LineGraphSeries<DataPoint> series;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.visualisation_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        retrieveViews(getView());
        setUpViews(getActivity());
    }

    private void setUpViews(FragmentActivity activity) {
        measureService = ((MainActivity)getActivity()).getService();
        list = new ArrayList<>();
        list = measureService.findMeasures();
        initListView();
    }

    private void initListView()
    {
        List<String> listDateString = new ArrayList<>();
        for(Measures measure : list){

            if(!listDateString.contains( measure.getDate().substring( 0,10 ) ))
            {
                listDateString.add(measure.getDate().substring( 0,10 ));
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter( getContext(), R.layout.element_date_list_view, listDateString  );
        spinnerDate.setAdapter(arrayAdapter);

        spinnerDate.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = spinnerDate.getSelectedItem().toString();
                makeGraph( s );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );
    }

    private void makeGraph(String date)
    {
        graph.removeAllSeries();
        series = new LineGraphSeries<>();
        series.resetData(new DataPoint[] {});
        Map<Integer, Integer> tab = new TreeMap<Integer, Integer>(getIndexByHour(list, date));

        for (int i = 0; i < 24; i++)
        {
            if(tab.containsKey( i ))
            {
                series.appendData(new DataPoint(i, tab.get( i )), false, 100);
            }
            else
            {
                series.appendData(new DataPoint(i, 0), false, 100);
            }
        }

        graph.addSeries(series);
    }

    private HashMap<Integer, Integer> getIndexByHour(List<Measures> list, String date)
    {
        HashMap<Integer, Integer> tab = new HashMap(  );
        for(Measures measure : list)
        {
            String dateComplete = measure.getDate();
            String dateMeasure = dateComplete.substring( 0, 10 );
            if (dateMeasure.equals( date ))
            {
                int heure = Integer.parseInt( dateComplete.substring( 11, 13 ) );
                tab.put(heure, measure.getStressIndex());
            }
        }
        return tab;
    }

    private void retrieveViews(View view) {

        graph = (GraphView) view.findViewById(R.id.graph);
        spinnerDate = (Spinner) view.findViewById(R.id.spinner_date);
    }

    @Override
    public void onMeasureServiceConnected() {

    }

    @Override
    public void onMeasureServiceDisconnected() {

    }
}
