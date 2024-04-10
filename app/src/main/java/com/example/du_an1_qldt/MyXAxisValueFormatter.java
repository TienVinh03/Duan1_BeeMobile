package com.example.du_an1_qldt;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.List;

public class MyXAxisValueFormatter extends ValueFormatter {

    private final List<String> mValues;

    public MyXAxisValueFormatter(List<String> values) {
        this.mValues = values;
    }

    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        int index = (int) value;
        if (index >= 0 && index < mValues.size()) {
            return mValues.get(index);
        }
        return "";
    }
}
