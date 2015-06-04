package edu.ucf.cop4331.supersweetsurveyapp;

import android.text.method.MultiTapKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.security.KeyStore;

/**
 * Adapter responsible for filling a ListView with survey questions
 */
public class SurveyQuestionAdapter extends BaseAdapter{

    private Survey s;
    private LayoutInflater layoutInflater;

    public SurveyQuestionAdapter(Survey s){
        this.s = s;
    }

    @Override
    public int getCount() {
        return s.getQuestions().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


}
