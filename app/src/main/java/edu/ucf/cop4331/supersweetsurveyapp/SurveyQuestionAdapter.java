package edu.ucf.cop4331.supersweetsurveyapp;

import android.content.Context;
import android.text.Layout;
import android.text.method.MultiTapKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter responsible for filling a ListView with survey questions
 */
public class SurveyQuestionAdapter extends BaseAdapter{

    private Survey survey;
    private LayoutInflater layoutInflater;
    private List<SurveyQuestion> questions;
    private Context context;

    public SurveyQuestionAdapter(Context context, Survey survey){
        super();
        this.survey = survey;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        questions = survey.getQuestions();
    }


    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Object getItem(int position) {
        return questions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        TextView questionText;
        SurveyQuestion currentQuestion = questions.get(position);
        int questionType = getItemViewType(position);


        switch(questionType){

            case 1:
                view = layoutInflater.inflate(R.layout.true_false_question_layout, parent, false);
                questionText = (TextView) view.findViewById(R.id.tf_text);
                RadioButton tf1 = (RadioButton) view.findViewById(R.id.tf_radio_1);
                RadioButton tf2 = (RadioButton) view.findViewById(R.id.tf_radio_2);

                questionText.setText(currentQuestion.getQuestionText());
                tf1.setText(currentQuestion.getOption1());
                tf2.setText(currentQuestion.getOption2());

                break;

            case 2:
                view = layoutInflater.inflate(R.layout.multiple_choice_question_layout, parent, false);
                questionText = (TextView) view.findViewById(R.id.multi_text);
                RadioButton multi1 = (RadioButton) view.findViewById(R.id.multi_radio_1);
                RadioButton multi2 = (RadioButton) view.findViewById(R.id.multi_radio_2);
                RadioButton multi3 = (RadioButton) view.findViewById(R.id.multi_radio_3);
                RadioButton multi4 = (RadioButton) view.findViewById(R.id.multi_radio_4);

                questionText.setText(currentQuestion.getQuestionText());
                multi1.setText(currentQuestion.getOption1());
                multi2.setText(currentQuestion.getOption2());
                multi3.setText(currentQuestion.getOption3());
                multi4.setText(currentQuestion.getOption4());
                break;

        }


        return view;

    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return Integer.parseInt(questions.get(position).getqTypeId());
    }
}
