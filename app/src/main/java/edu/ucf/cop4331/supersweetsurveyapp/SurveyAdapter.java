package edu.ucf.cop4331.supersweetsurveyapp;

// Custom adapter to populate the list of surveys and keep track of their IDs

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SurveyAdapter extends ArrayAdapter<Survey> {

    Context context;
    int layoutResourceId;
    ArrayList<Survey> surveys;

    public SurveyAdapter(Context context, int layoutResourceId, ArrayList<Survey> surveys) {
        super(context, layoutResourceId, surveys);
        this.surveys = surveys;
        this.context = context;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.survey_layout, parent, false);

        Survey survey = getItem(position);

        TextView surveyTitle = (TextView) rowView.findViewById(R.id.survey_text_view);

        surveyTitle.setText(survey.getSurveyName());

        return rowView;
    }


}
