package com.example.bmi_calculator;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CaloryIntakeActivity extends AppCompatActivity{

    EditText input_height, input_weight, input_age;
    Button button_calc, button_clear;
    RadioGroup radio_group;
    RadioButton radio_male, radio_female;
    TextView display_intake;
    String userHeightTxt, userWeightTxt, userAgeTxt;
    float userHeight, userWeight, userAge, caloryIntake, userGenderFactor = 0;

    private void clearInputs() {
        input_height.setText("");
        input_weight.setText("");
        input_age.setText("");
        radio_group.clearCheck();
    }

    private void clearOutputs() {
        display_intake.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calory_intake);

        input_weight = findViewById(R.id.input_weight);
        input_height = findViewById(R.id.input_height);
        input_age = findViewById(R.id.input_age);
        button_calc = findViewById(R.id.button_calculate);
        button_clear = findViewById(R.id.button_clear);
        radio_group = findViewById(R.id.radio_group);
        radio_male = findViewById(R.id.radio_male);
        radio_female = findViewById(R.id.radio_female);
        display_intake = findViewById(R.id.display_intake);

        button_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                userHeightTxt = input_height.getText().toString();
                userWeightTxt = input_weight.getText().toString();
                userAgeTxt = input_age.getText().toString();
                if (radio_male.isChecked()){
                    userGenderFactor = 5;
                }
                else if (radio_female.isChecked()){
                    userGenderFactor = -161;
                }


                // Convert String to float
                try {
                    userHeight = Float.parseFloat(userHeightTxt); // height is entered in centimeters
                    userWeight = Float.parseFloat(userWeightTxt);
                    userAge = Float.parseFloat(userAgeTxt);

                    // Calculate Calory Intake
                    caloryIntake = (float) ((10*userWeight)+(6.25*userHeight)-(5*userAge)+userGenderFactor);

                    // Display BMI
                    display_intake.setText(String.format("You can eat: %.2f kcal", caloryIntake));
                } catch (NumberFormatException e) {
                    // Handle exception if input is not a valid number
                    display_intake.setText("Invalid input");
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
