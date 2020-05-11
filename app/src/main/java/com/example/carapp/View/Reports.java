package com.example.carapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.carapp.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class Reports extends AppCompatActivity {
    HorizontalBarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        chart = findViewById(R.id.chart1);
    }
    private void setData(int count , int range)
    {
        ArrayList<BarEntry> yaxis = new ArrayList<>();
        float barwidth = 9f;
        float spaceforbar = 10f;
        yaxis.add(new BarEntry(spaceforbar,1));
        yaxis.add(new BarEntry(spaceforbar,2));
        yaxis.add(new BarEntry(spaceforbar,7));
        yaxis.add(new BarEntry(spaceforbar,3));
        BarDataSet set1;
        set1 = new BarDataSet(yaxis, "Trips Data");
        BarData data = new BarData(set1);
        chart.setData(data);

    }

}
