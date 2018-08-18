package Adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.picker.date.persian.parisa.soheil.PersianDatePickerModule.DatePickerPersian;
import com.picker.date.persian.parisa.soheil.PersianDatePickerModule.R;

import java.util.ArrayList;
import Fragments.FragmentDatePickerPersianView;


public class ViewPagerAdapter extends FragmentPagerAdapter {



    private static final String TAG = "LOG";

    private  int year_now ;
    private  String month_now ;
    private  int count ;
    private  int current_item_position ;
    private  int background_lable_month ;
    private  int backgroundColorDay;
    private   int backgroundColorCurrentDay;
    private  int start_year ;
    private  int end_year ;

    private Context context ;

    public ViewPagerAdapter(Context context  ,FragmentManager fm , int count , int start_year , int end_year  , int current_item_position , int background_lable_month
      , int backgroundColorDay , int backgroundColorCurrentDay) {
        super(fm);
        this.context = context;
        this.count = count ;
        this.current_item_position = current_item_position ;
        this.background_lable_month = background_lable_month ;
        this.backgroundColorDay = backgroundColorDay ;
        this.backgroundColorCurrentDay = backgroundColorCurrentDay ;

        this.end_year = end_year ;
    }

    @Override
    public Fragment getItem(int position) {
        int year_position_start_of =  position - (position % 12);
        int year_current_position_start_of = Math.abs(this.current_item_position -(this.current_item_position % 12));
        int month_position = (position % 12) + 1;
        year_now = Integer.parseInt(DatePickerPersian.get_instance().getDateByLang(context , System.currentTimeMillis()).split("/")[0]);
         month_now = DatePickerPersian.get_instance().get_name_month(Integer.parseInt(DatePickerPersian.get_instance().getDateByLang(context  ,
                System.currentTimeMillis()).split("/")[1]) ,
               context);
        if(position > current_item_position) {
            int year ;
            String month = DatePickerPersian.get_instance().get_name_month(month_position , context);

            if(this.end_year < year_now) {
                year = (Math.abs(year_current_position_start_of - year_position_start_of) / 12) + start_year ;
            }else  {
                year = (Math.abs(year_current_position_start_of - year_position_start_of) / 12) + this.year_now ;
            }

            return FragmentDatePickerPersianView.newInstance(get_date(month_position) , year,  month , background_lable_month , this.backgroundColorDay , this.backgroundColorCurrentDay);


        }else  if(position == current_item_position) {
            if(this.end_year < year_now) {
                return FragmentDatePickerPersianView.newInstance(get_date(month_position) , this.start_year,  context.getString(R.string.farvardin) , background_lable_month ,this.backgroundColorDay , this.backgroundColorCurrentDay);
            }else  {
                return FragmentDatePickerPersianView.newInstance(get_date(month_position) , year_now, month_now , background_lable_month  ,this.backgroundColorDay , this.backgroundColorCurrentDay);

            }
        }else  {
            int year = year_now - (Math.abs(year_current_position_start_of - year_position_start_of) / 12) ;
            String month = DatePickerPersian.get_instance().get_name_month(month_position , context) ;
            return FragmentDatePickerPersianView.newInstance(get_date(month_position) , year, month , background_lable_month , this.backgroundColorDay , this.backgroundColorCurrentDay);
        }
    }


    @Override
    public int getCount() {
        return this.count;
    }


    private  ArrayList<Integer> get_date(int month) {
        ArrayList<Integer> data = new ArrayList<>();
        if(month <=6){
            for (int i = 1; i <32 ; i++) {
                data.add(i);
            }
        }else if (month == 12) {
            for (int j = 1; j < 30; j++) {
                data.add(j);
            }
        } else {
            for (int j = 1; j < 31; j++) {
                data.add(j);
            }
        }

        return  data ;
    }


}
