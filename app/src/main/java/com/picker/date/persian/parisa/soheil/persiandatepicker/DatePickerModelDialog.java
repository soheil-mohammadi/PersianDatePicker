package com.picker.date.persian.parisa.soheil.persiandatepicker;


import java.util.ArrayList;

public class DatePickerModelDialog {
    public ArrayList<Integer> data ;
    public String month;
    public int year;

    public  DatePickerModelDialog(ArrayList<Integer> data ,  int year ,String month) {
        this.data =  data ;
        this.year = year ;
        this.month = month ;
    }
}
