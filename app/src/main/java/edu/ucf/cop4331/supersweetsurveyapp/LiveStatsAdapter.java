package edu.ucf.cop4331.supersweetsurveyapp;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter responsible for filling a ListView with survey questions
 */
public class LiveStatsAdapter extends BaseAdapter{

    private LayoutInflater layoutInflater;
    private Context context;
    private SurveyStatsActivity activity;
    private ArrayList<SurveyAnswer> answers;

    public LiveStatsAdapter(Context context, ArrayList<SurveyAnswer> answers){
        super();
        this.context = context;
        this.answers = answers;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setActivity(SurveyStatsActivity activity){
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return answers.size();
    }

    @Override
    public Object getItem(int position) {
        return answers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        TextView questionText, answerText, submittedText;
        final SurveyAnswer currentAnswer = answers.get(position);

        view = layoutInflater.inflate(R.layout.live_stats_layout, parent, false);

        questionText = (TextView) view.findViewById(R.id.question_text);
        answerText = (TextView) view.findViewById(R.id.answer_text);
        submittedText = (TextView) view.findViewById(R.id.submitter_text);

        questionText.setText("Question: " + currentAnswer.question);
        answerText.setText("Answer: "  + currentAnswer.answer);
        submittedText.setText("Submitter: " + currentAnswer.submitter);

        return view;
    }

}
