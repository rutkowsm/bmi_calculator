package com.example.bmi_calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BmiChartActivity extends AppCompatActivity {

    ImageView homeImg, shoppingCartImg;
    private List<String> bmiList;
    private ListView listBmiResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_chart);
        //Data initialization for BMI chart
        BmiDataManager.initializeBmiDataIfNecessary(this);

        homeImg = findViewById(R.id.homeImg);
        shoppingCartImg = findViewById(R.id.shoppingCartImg);
        listBmiResults = findViewById(R.id.listBmiResults);
        bmiList = new ArrayList<>();

        homeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BmiChartActivity.this, SplashScreenActivity.class);
                startActivity(intent);
            }
        });

        shoppingCartImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BmiChartActivity.this, ShoppingListActivity.class);
                startActivity(intent);
            }
        });

        Map<String, Double> bmiResults = BmiDataManager.loadBmiResults(this);
        for (Map.Entry<String, Double> entry : bmiResults.entrySet()) {
            bmiList.add(entry.getKey() + ": " + String.format("%.2f", entry.getValue()));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bmiList);
        listBmiResults.setAdapter(adapter);
    }



}
