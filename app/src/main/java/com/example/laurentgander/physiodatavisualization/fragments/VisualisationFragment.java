package com.example.laurentgander.physiodatavisualization.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.laurentgander.physiodatavisualization.MainActivity;
import com.example.laurentgander.physiodatavisualization.MeasureService;
import com.example.laurentgander.physiodatavisualization.Measures;
import com.example.laurentgander.physiodatavisualization.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laurent.gander on 31/10/2017.
 */

public class VisualisationFragment extends PhysioDataVisualisationFragment{

    private GraphView graph;
    private MeasureService measureService;
    private Spinner dateSpinner;
    private List<Measures> list;

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
        list = new ArrayList<Measures>();
        list = measureService.findMeasures();
        initSpinner();
        makeGraph(dateSpinner.getSelectedItem().toString());
    }

    private void initSpinner()
    {
        List<String> listDateString = new ArrayList<String>();
        for(Measures measure : list){

            listDateString.add(measure.getDate().substring( 0,10 ));
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listDateString);
        dateSpinner.setAdapter(arrayAdapter);
    }

    private void makeGraph(String date)
    {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
        HashMap<Integer, Integer> tab = getIndexByHour(list, date);
        for(Map.Entry<Integer, Integer> entry : tab.entrySet())
        {
            int heure = entry.getKey();
            int index = entry.getValue();

            for (int i = 0; i < 24; i++)
            {
                if(i == heure)
                {
                    series.appendData(new DataPoint(heure, index), false, 100);
                }
                else
                {
                    series.appendData(new DataPoint(i, 0), false, 100);
                }
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
                tab.put(heure, measure.getCenestesisIndex());
            }
        }
        return tab;
    }

    private void retrieveViews(View view) {

        graph = (GraphView) view.findViewById(R.id.graph);
        dateSpinner = (Spinner) view.findViewById(R.id.spinnerDate);
    }

    @Override
    public void onMeasureServiceConnected() {

    }

    @Override
    public void onMeasureServiceDisconnected() {

    }
}
