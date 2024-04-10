package com.example.bmi_calculator;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CaloryIntakeActivity extends AppCompatActivity{

    EditText input_height, input_weight, input_age;
    Button button_calc, button_clear, button_find_recipies;
    RadioGroup radio_group;
    RadioButton radio_male, radio_female;
    TextView display_intake;
    String userHeightTxt, userWeightTxt, userAgeTxt;
    float userHeight, userWeight, userAge, caloryIntake, userGenderFactor = 0;
    ImageView homeImg;

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
        homeImg = findViewById(R.id.homeImg);
        button_find_recipies = findViewById(R.id.button_find_recipes);

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

        homeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaloryIntakeActivity.this, SplashScreenActivity.class);
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

        button_find_recipies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Uruchomienie RecipesActivity
                Intent intent = new Intent(CaloryIntakeActivity.this, RecipeActivity.class);
                startActivity(intent);
            }
        });
    }
}
