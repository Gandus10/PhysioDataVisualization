package com.example.laurentgander.physiodatavisualization;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laurent.gander on 07/11/2017.
 */

public class MeasuresAdapter extends BaseAdapter implements Filterable{

    private Context context;

    private MeasureService measureService;

    private List<Measures> measures;

    private List<Measures> filteredMeasures;

    private Filter measuresFilter;

    public MeasuresAdapter(Context context, MeasureService measureService)
    {
        super();

        this.context = context;
        this.measureService = measureService;
        construct();
    }

    private void construct() {

        update();

        measuresFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint == null) {
                    filteredMeasures = measures;

                } else {
                    filteredMeasures = new ArrayList<>();

                    for(Measures measure : measures)
                    {
                        if(measure.getDate().toString().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            filteredMeasures.add(measure);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredMeasures;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                notifyDataSetChanged();
            }
        };

    }

    public void update() {

        measures = measureService.findMeasures();
        filteredMeasures = measures;
    }

    @Override
    public int getCount() {
        return filteredMeasures.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredMeasures.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MeasuresHolder holder;
        if(convertView == null){
           convertView = LayoutInflater.from(context).inflate(R.layout.measures_list_row, parent,false);

           holder = new MeasuresHolder();

           holder.posImage = (ImageView) convertView.findViewById(R.id.posImage);
           holder.dateTextView = (TextView) convertView.findViewById(R.id.dateTextView);
           holder.cenestesisTextView = (TextView) convertView.findViewById(R.id.heartBeatTextView);

           convertView.setTag(holder);
        }else
        {
            holder = (MeasuresHolder) convertView.getTag();
        }

        Measures measures = filteredMeasures.get(position);

        switch (measures.getPosition()){
            case 1:
                holder.posImage.setImageResource(R.drawable.lit);
                break;
            case 2:
                holder.posImage.setImageResource(R.drawable.assis);
                break;
            case 3:
                holder.posImage.setImageResource(R.drawable.debout);
                break;
        }

        holder.cenestesisTextView.setText(String.valueOf(measures.getCenestesisIndex()));
        holder.dateTextView.setText(measures.getDate().substring( 0, 10 ));

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return measuresFilter;
    }

    private static class MeasuresHolder{
        ImageView posImage;
        TextView dateTextView;
        TextView cenestesisTextView;
    }

}
