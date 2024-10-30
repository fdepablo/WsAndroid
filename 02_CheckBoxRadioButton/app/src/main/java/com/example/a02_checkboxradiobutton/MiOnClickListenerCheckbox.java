package com.example.a02_checkboxradiobutton;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class MiOnClickListenerCheckbox implements View.OnClickListener{

    @Override
    public void onClick(View v) {
        Log.i("MiOnClickListenerCheckbox","`pulsado");
        CheckBox checkBox = (CheckBox) v;
        boolean isChecked = checkBox.isChecked();

        if (isChecked) {
            checkBox.setText("Checkbox marcado!");
        } else {
            checkBox.setText("Checkbox desmarcado!");
        }
    }
}
