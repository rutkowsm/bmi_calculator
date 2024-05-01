package com.example.bmi_calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
    private WebView webViewBmiChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_chart);
        //Data initialization for BMI chart
        BmiDataManager.initializeBmiDataIfNecessary(this);

        homeImg = findViewById(R.id.homeImg);
        shoppingCartImg = findViewById(R.id.shoppingCartImg);
        listBmiResults = findViewById(R.id.listBmiResults);
        webViewBmiChart = findViewById(R.id.webViewBmiChart);
        bmiList = new ArrayList<>();


        homeImg.setOnClickListener(v -> startActivity(new Intent(BmiChartActivity.this, SplashScreenActivity.class)));
        shoppingCartImg.setOnClickListener(v -> startActivity(new Intent(BmiChartActivity.this, ShoppingListActivity.class)));

        Map<String, Double> bmiResults = BmiDataManager.loadBmiResults(this);
        for (Map.Entry<String, Double> entry : bmiResults.entrySet()) {
            bmiList.add(entry.getKey() + ": " + String.format("%.2f", entry.getValue()));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bmiList);
        listBmiResults.setAdapter(adapter);

        webViewBmiChart.getSettings().setJavaScriptEnabled(true);

        bmiResults = BmiDataManager.loadBmiResults(this);
        ArrayList<String> labels = new ArrayList<>();
        ArrayList<Double> values = new ArrayList<>();

        for (Map.Entry<String, Double> entry : bmiResults.entrySet()) {
            labels.add("'" + entry.getKey() + "'");
            values.add(entry.getValue());
        }

        // Convert to JSON strings
        Gson gson = new Gson();
        final String jsonLabels = gson.toJson(labels);
        final String jsonValues = gson.toJson(values);
        webViewBmiChart.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String jsCode = "javascript:drawChart(" + jsonLabels + ", " + jsonValues + ");";
                webViewBmiChart.evaluateJavascript(jsCode, null);
            }
        });

        webViewBmiChart.loadUrl("file:///android_asset/bmi_chart.html");
    }



}
