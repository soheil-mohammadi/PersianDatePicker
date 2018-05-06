package com.picker.date.persian.parisa.soheil.persiandatepicker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewAnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import Adapter.ViewPagerAdapter;
import Interface.DayClickListenerPersianDatePicker;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener , DayClickListenerPersianDatePicker{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatePickerPersianView date_picker_persian =  findViewById(R.id.date_picker_persian);
        date_picker_persian.build(getSupportFragmentManager());
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
