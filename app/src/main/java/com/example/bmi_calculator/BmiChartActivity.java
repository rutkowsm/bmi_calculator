package com.example.bmi_calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class BmiChartActivity extends AppCompatActivity {

    ImageView homeImg, shoppingCartImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_chart);

        homeImg = findViewById(R.id.homeImg);
        shoppingCartImg = findViewById(R.id.shoppingCartImg);

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
    }



}
