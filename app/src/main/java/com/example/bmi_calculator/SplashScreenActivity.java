package com.example.bmi_calculator;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Button btnCalculateBMI = findViewById(R.id.btn_calculate_bmi);
        Button btnCaloriesIntake = findViewById(R.id.btn_calories_intake);

        btnCalculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnCaloriesIntake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashScreenActivity.this, CaloryIntakeActivity.class);
                startActivity(intent);
            }
        });

        // Dodaj OnClickListener dla każdego przycisku prowadzącego do nowej aktywności
    }
}
