package edu.ucf.cop4331.supersweetsurveyapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.logging.Handler;

//TODO: implement realtime stats
public class SurveyStatsActivity extends Activity {

    String surveyId;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_stats);

        Intent intent = getIntent();
        this.surveyId = intent.getStringExtra("surveyId");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        GetStatsTask getStatsTask = new GetStatsTask(context);
                        getStatsTask.setSurveyId(surveyId);
                        getStatsTask.execute();
                        Thread.sleep(6000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();



//
//        GraphView graph = (GraphView) findViewById(R.id.graph);
//
//        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
//
//                new DataPoint(0,1),
//                new DataPoint(1,5),
//                new DataPoint(2,3),
//                new DataPoint(3,2),
//                new DataPoint(4,6)
//        });
//
//        series.setSpacing(20);
//
//        double xInterval=1.0;
//        graph.getViewport().setXAxisBoundsManual(true);
//        if (series instanceof BarGraphSeries ) {
//
//        // Shunt the viewport, per v3.1.3 to show the full width of the first and last bars.
//            graph.getViewport().setMinX(series.getLowestValueX() - (xInterval/2.0));
//            graph.getViewport().setMaxX(series.getHighestValueX() + (xInterval/2.0));
//        } else {
//            graph.getViewport().setMinX(series.getLowestValueX());
//            graph.getViewport().setMaxX(series.getHighestValueX());
//        }
//
//        graph.addSeries(series);

    }

}
