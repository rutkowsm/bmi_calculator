package com.example.bmi_calculator;

public class ShoppingProduct {
    private String name;
    private boolean isChecked;


    public ShoppingProduct(String name, boolean isChecked) {
        this.name = name;
        this.isChecked = isChecked;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

}

