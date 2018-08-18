package com.picker.date.persian.parisa.soheil.PersianDatePickerModule;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

import Interface.DayClickListenerPersianDatePicker;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener , DayClickListenerPersianDatePicker{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatePickerPersianView date_picker_persian =  findViewById(R.id.date_picker_persian);
        date_picker_persian.build(this , getSupportFragmentManager());
    }


    @Override
    public void onClick(View v) {
        DatePickerPersian datePickerPersian = DatePickerPersian.get_instance();
        datePickerPersian.show_Dialog(this ,DatePickerPersian.TRANSALATE_ANIM , null  , Color.GRAY ,R.color.colorPrimary, Color.RED , Color.BLUE , this);
    }

    @Override
    public void on_click(int year, int month, int day) {
        Toast.makeText(this, year + "/" + month +"/" + day , Toast.LENGTH_SHORT).show();
    }
}
