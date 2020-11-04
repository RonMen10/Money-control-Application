package com.example.home;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chart extends AppCompatActivity {

    //String selectTransactionName22;
    PieChart pieChart;
    Cursor dbAllData;
    BarChart mChart;
    String selectTransactionName;
    DatabaseHelper myDb = new DatabaseHelper(this);

    public static final String RENT = "Rent";
    public static final String FOOD = "Food";
    public static final String INTERNET = "Internet";
    public static final String ELECTRICITY = "Electricity Bill";

    public static final int DB_COLUMN_CATEGORY = 2;
    public static final int DB_COLUMN_AMOUNT = 5;
    public static final int DB_OLUMN_TRANSACTION = 1;

    Map<String, Integer> fullAmount;

    private HashMap<String, Integer> initializeCategories() {
        HashMap<String, Integer> categories = new HashMap<String, Integer>();
        categories.put(RENT, 0);
        categories.put(FOOD, 0);
        categories.put(INTERNET, 0);
        categories.put(ELECTRICITY, 0);
//        To add user defined categories, with the method bellow
//        categories = addUserCategories(categories);
        return categories;
    }

    private HashMap<String, Integer> addUserCategories(HashMap<String, Integer> categories) {
        Cursor dbCategories = myDb.getAllCategories();
        while (dbCategories.moveToNext()) {
            String userCategory = dbCategories.getString(1);
            if(userCategory.equals("Choose category")){
                continue;
            }
            if (!(userCategory == null)) {
                categories.put(userCategory, 0);
            }
        }
        return categories;
    }

    public Map calculateFullAmount() {
        Cursor dbAllData = myDb.getAllData();
        HashMap<String, Integer> sumByCategory = initializeCategories();
        addUserCategories(sumByCategory);

        while (dbAllData.moveToNext()) {
            String category = dbAllData.getString(DB_COLUMN_CATEGORY);
            Integer amount = dbAllData.getInt(DB_COLUMN_AMOUNT);
            String transactionType = dbAllData.getString(DB_OLUMN_TRANSACTION);

            if (category != null && sumByCategory.containsKey(category) && transactionType.equals(selectTransactionName)) {
                Integer sum = sumByCategory.get(category) + amount;
                sumByCategory.put(category, sum);
            }
        }
        return cleanup(sumByCategory);
    }

    private Map cleanup(HashMap<String, Integer> cleanMap) {
        List<String> ceroValues = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : cleanMap.entrySet()) {
            if (entry.getValue() == 0){
                ceroValues.add(entry.getKey());
            }
        }
        for (String key:ceroValues){
            cleanMap.remove(key);
        }
        return cleanMap;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        setTitle("Pie Chart");

        Bundle extras = getIntent().getExtras();
        selectTransactionName = extras.get("type").toString();

        Button chart1=(Button) findViewById(R.id.BarChartB);
        chart1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent chart1Intent=new Intent(getApplicationContext(), BChart.class);
                startActivity(chart1Intent);
                Intent texIntent=new Intent(Chart.this,BChart.class);
                texIntent.putExtra("type", selectTransactionName);
                startActivity(texIntent);
            }
        });

        Button backButton=(Button) findViewById(R.id.chart_Back);
        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent chart1Intent=new Intent(getApplicationContext(), TransactionSelect.class);
                startActivity(chart1Intent);
            }
        });


        pieChart = (PieChart) findViewById(R.id.chart);
        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(1, 1, 1, 1);
        pieChart.setDragDecelerationFrictionCoef(0.9f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(60f);
        pieChart.setDrawEntryLabels(false);
        pieChart.animateX(1000, Easing.EasingOption.EaseInCubic);
        fullAmount = calculateFullAmount();

        if(fullAmount.keySet().size()==0){
            Toast.makeText(Chart.this, "Database is empty", Toast.LENGTH_SHORT).show();
            Intent save = new Intent(getApplicationContext(), Mainactivity.class);
            startActivity(save);
        }

        else {
            ArrayList<PieEntry> yValues = new ArrayList<>();
            for(int ks=0; ks < fullAmount.keySet().size(); ks++){
                yValues.add(new PieEntry(fullAmount.get(fullAmount.keySet().toArray()[ks]), fullAmount.keySet().toArray()[ks].toString()));
            }

//            yValues.add(new PieEntry(fullAmount.get(RENT), RENT));
//            yValues.add(new PieEntry(fullAmount.get(FOOD), FOOD));
//            yValues.add(new PieEntry(fullAmount.get(INTERNET), INTERNET));
//            yValues.add(new PieEntry(fullAmount.get(ELECTRICITY), ELECTRICITY));

            PieDataSet dataSet = new PieDataSet(yValues, "Expenses");
            dataSet.setSliceSpace(3f);
            dataSet.setSelectionShift(7f);
            int[] CUSTOM_COLORS = {
                    Color.rgb(0, 0, 0),
                    Color.rgb(128, 0, 128),
                    Color.rgb(255, 0, 0),
                    Color.rgb(0, 255, 0),
                    Color.rgb(0, 0, 255),
                    Color.rgb(0, 0, 128),
                    Color.rgb(0, 255, 255),
                    Color.rgb(255, 0, 255),
                    Color.rgb(192, 192, 192),
                    Color.rgb(128, 128, 128),
                    Color.rgb(128, 0, 0)
            };
            dataSet.setColors(CUSTOM_COLORS);
            PieData data = new PieData((dataSet));
            data.setValueTextColor(Color.WHITE);
            data.setValueTextSize(15f);
            pieChart.setData(data);
            Legend legend = pieChart.getLegend();
            legend.setOrientation(Legend.LegendOrientation.VERTICAL);
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        }
    }

    @Override
    public void onBackPressed() {
        Intent chart1Intent=new Intent(getApplicationContext(), TransactionSelect.class);
        startActivity(chart1Intent);
        finish();
    }
}
