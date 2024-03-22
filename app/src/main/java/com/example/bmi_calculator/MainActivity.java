package com.example.bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText input_height, input_weight;
    Button button_ok, button_clear;
    TextView display_bmi;
    String userHeightTxt, userWeightTxt;
    float userHeight, userWeight, bmiIndex;

    private void clearInputs() {
        input_height.setText("");
        input_weight.setText("");
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

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                userHeightTxt = input_height.getText().toString();
                userWeightTxt = input_weight.getText().toString();

                // Convert String to float
                try {
                    userHeight = Float.parseFloat(userHeightTxt) / 100; // height is entered in centimeters
                    userWeight = Float.parseFloat(userWeightTxt);

                    // Calculate BMI
                    bmiIndex = userWeight / (userHeight * userHeight);

                    // Display BMI
                    display_bmi.setText(String.format("Your BMI: %.2f", bmiIndex));
                } catch (NumberFormatException e) {
                    // Handle exception if input is not a valid number
                    display_bmi.setText("Invalid input");
                }
            }
        });

        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInputs();
                clearOutputs();
            }
        });
    }
}