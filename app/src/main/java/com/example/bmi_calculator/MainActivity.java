package com.example.bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText input_height, input_weight;
    Button button_ok, button_clear;
    TextView display_bmi;
    String userHeightTxt, userWeightTxt;
    float userHeight, userWeight, bmiIndex;
    ImageView homeImg, shoppingCartImg, chartBmiImg;

    private void clearInputs() {
        input_height.setText("");
        input_weight.setText("");
    }

    public static float calculateBMI(float weight, float height) {
        float heightInMeters = height / 100;
        return weight / (heightInMeters * heightInMeters);
    }


    private void clearOutputs() {
        display_bmi.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_weight = findViewById(R.id.input_weight);
        input_height = findViewById(R.id.input_height);
        button_ok = findViewById(R.id.button_ok);
        button_clear = findViewById(R.id.button_clear);
        display_bmi = findViewById(R.id.display_bmi);
        homeImg = findViewById(R.id.homeImg);
        shoppingCartImg = findViewById(R.id.shoppingCartImg);
        chartBmiImg = findViewById(R.id.chartBmiImg);

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                userHeightTxt = input_height.getText().toString();
                userWeightTxt = input_weight.getText().toString();

                // Convert String to float
                try {
                    userHeight = Float.parseFloat(userHeightTxt); // height is entered in centimeters
                    userWeight = Float.parseFloat(userWeightTxt);

                    // Calculate BMI
                    bmiIndex = calculateBMI(userWeight, userHeight);

                    // Display BMI
                    display_bmi.setText(String.format("Your BMI: %.2f", bmiIndex));
                } catch (NumberFormatException e) {
                    // Handle exception if input is not a valid number
                    display_bmi.setText("Invalid Input");
                }
            }
        });

        homeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SplashScreenActivity.class);
                startActivity(intent);
            }
        });

        shoppingCartImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShoppingListActivity.class);
                startActivity(intent);
            }
        });

        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInputs();
                clearOutputs();
            }
        });

        chartBmiImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BmiChartActivity.class);
                startActivity(intent);
            }
        });
    }
}