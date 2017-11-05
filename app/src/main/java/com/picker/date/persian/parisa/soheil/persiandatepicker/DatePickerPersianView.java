package com.picker.date.persian.parisa.soheil.persiandatepicker;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import Adapter.ViewPagerAdapter;


public class DatePickerPersianView extends LinearLayout {

    private  int count_view_pager ;
    private  int start_year = 1390 ;
    private  int end_year = 1400 ;
    private  int background_lable_month;
    private  int current_item_viewPager ;

    private  ViewPager  view_pager_date_picker_persian_view ;


    public DatePickerPersianView(Context context) {
        super(context);
        inflate(null);
    }

    public DatePickerPersianView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(attrs);
    }

    public DatePickerPersianView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(attrs);
    }


    private  void inflate(AttributeSet attrs) {
        View view =  inflate(getContext() ,R.layout.date_picker_persian_layout , this);
        view_pager_date_picker_persian_view = (ViewPager) view.findViewById(R.id.view_pager_date_picker_persian_view);
        if(attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs , R.styleable.DatePickerPersianView);
            if(typedArray !=null) {
                this.start_year = typedArray.getInt(R.styleable.DatePickerPersianView_start_year , this.start_year);
                this.end_year = typedArray.getInt(R.styleable.DatePickerPersianView_end_year , this.end_year);
                this.background_lable_month = typedArray.getColor(R.styleable.DatePickerPersianView_background_lable_month ,this.background_lable_month);
                this.count_view_pager = (Math.abs(end_year - start_year) + 1) * 12;
            }else  {
                this.count_view_pager = ((this.end_year - this.start_year) +1) * 12;
            }
        }else  {
            this.count_view_pager = ((this.end_year - this.start_year) +1) * 12;
        }
        int year_now = Integer.parseInt(DatePickerPersian.get_instance().get_jalali_date(System.currentTimeMillis())[0]) ;
        int month_now = Integer.parseInt(DatePickerPersian.get_instance().get_jalali_date(System.currentTimeMillis())[1]) ;
        if(this.end_year >  year_now) {
            int position_start_year_end = (this.count_view_pager - 1) - 11 ;
            this.current_item_viewPager = Math.abs(((this.end_year - year_now) * 12) - position_start_year_end ) + month_now - 1;
        }else if(this.end_year == year_now){
            this.current_item_viewPager = ((this.count_view_pager - 1) - 11) + month_now - 1;
        }else  {
            this.current_item_viewPager = ((this.count_view_pager - 1) - 11) + month_now - 1;
        }
    }

    public void set_support_fragmentManager(FragmentManager fragmentManager ) {
        view_pager_date_picker_persian_view.setAdapter(new ViewPagerAdapter(fragmentManager, this.count_view_pager , this.current_item_viewPager , this.background_lable_month));
       view_pager_date_picker_persian_view.setCurrentItem(this.current_item_viewPager);
    }
}

