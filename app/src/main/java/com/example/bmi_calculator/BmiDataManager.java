package com.example.bmi_calculator;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

public class BmiDataManager {

    private static final String PREFS_NAME = "BmiPrefs";
    private static final String BMI_RESULTS_KEY = "bmiResults";

    public static void saveBmiResults(Context context, Map<String, Double> bmiResults) {

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(bmiResults);
        editor.putString(BMI_RESULTS_KEY, json);
        editor.apply();
    }

    public static Map<String, Double> loadBmiResults(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(BMI_RESULTS_KEY, null);
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Double>>() {}.getType();
        Map<String, Double> bmiResults = gson.fromJson(json, type);
        return bmiResults != null ? bmiResults : new LinkedHashMap<>();
    }

    public static void initializeBmiDataIfNecessary(Context context){
        Map<String, Double> existingData = BmiDataManager.loadBmiResults(context);
        if (existingData.isEmpty()) {
            Map<String, Double> bmiResults = new LinkedHashMap<>();
            //Dummy data
            bmiResults.put("2022-05-17", 35.38);
            bmiResults.put("2022-06-10", 34.55);
            bmiResults.put("2022-07-04", 33.23);
            bmiResults.put("2022-07-28", 32.46);
            bmiResults.put("2022-08-21", 31.41);
            bmiResults.put("2022-09-14", 30.55);
            bmiResults.put("2022-10-08", 29.89);
            bmiResults.put("2022-11-01", 29.08);
            bmiResults.put("2022-11-25", 28.43);
            bmiResults.put("2022-12-19", 28.01);
            bmiResults.put("2023-01-12", 27.50);
            bmiResults.put("2023-02-05", 27.17);
            bmiResults.put("2023-03-01", 27.86);
            bmiResults.put("2023-03-25", 28.31);
            bmiResults.put("2023-04-18", 28.70);
            bmiResults.put("2023-05-12", 28.13);
            bmiResults.put("2023-06-05", 27.80);
            bmiResults.put("2023-06-29", 27.41);
            bmiResults.put("2023-07-23", 27.02);
            bmiResults.put("2023-08-16", 26.40);
            bmiResults.put("2023-09-09", 25.83);
            bmiResults.put("2023-10-03", 25.14);
            bmiResults.put("2023-10-27", 24.58);
            bmiResults.put("2023-11-20", 24.19);
            bmiResults.put("2023-12-14", 23.80);
            bmiResults.put("2024-01-07", 24.19);
            bmiResults.put("2024-01-31", 24.04);
            bmiResults.put("2024-02-24", 24.61);
            bmiResults.put("2024-03-19", 24.96);
            bmiResults.put("2024-04-12", 24.07);

            BmiDataManager.saveBmiResults(context, bmiResults);
        }
    }
}
