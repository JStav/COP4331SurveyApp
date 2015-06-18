package edu.ucf.cop4331.supersweetsurveyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Adapter responsible for filling a ListView with survey questions
 */
public class StatsAdapter extends BaseAdapter{

    private LayoutInflater layoutInflater;
    private Context context;
    private SurveyFeedActivity activity;
    private ArrayList<StatsTracker> st;

    public StatsAdapter(Context context, ArrayList<StatsTracker> st){
        super();
        this.context = context;
        this.st = st;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setActivity(SurveyFeedActivity activity){
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return st.size();
    }

    @Override
    public Object getItem(int position) {
        return st.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        view = layoutInflater.inflate(R.layout.statslayout, parent, false);

        String build = "";

        TextView question = (TextView) view.findViewById(R.id.textView4);
        TextView answer = (TextView) view.findViewById(R.id.textView5);

        question.setText(st.get(position).getQuestion());

        for( String key : st.get(position).questionStats.keySet()){

            build += key + ": " + st.get(position).questionStats.get(key) + "\n";

        }

        answer.setText(build);



        return view;
    }

}
