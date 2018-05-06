package Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import com.picker.date.persian.parisa.soheil.persiandatepicker.DatePickerPersian;
import java.util.ArrayList;
import Fragments.FragmentDatePickerPersianView;


public class ViewPagerAdapter extends FragmentPagerAdapter {



    private static final String TAG = "LOG";

    private  int year_now = Integer.parseInt(DatePickerPersian.get_instance().get_jalali_date(System.currentTimeMillis())[0]);
    private  String month_now = DatePickerPersian.get_instance().get_name_month(Integer.parseInt(DatePickerPersian.get_instance().get_jalali_date(System.currentTimeMillis())[1]));
    private  int count ;
    private  int current_item_position ;
    private  int background_lable_month ;
    private  int backgroundColorDay;
    private   int backgroundColorCurrentDay;
    private  int start_year ;
    private  int end_year ;

    public ViewPagerAdapter(FragmentManager fm , int count ,int start_year ,  int end_year  ,int current_item_position , int background_lable_month
      , int backgroundColorDay ,int backgroundColorCurrentDay) {
        super(fm);
        this.count = count ;
        this.current_item_position = current_item_position ;
        this.background_lable_month = background_lable_month ;
        this.backgroundColorDay = backgroundColorDay ;
        this.backgroundColorCurrentDay = backgroundColorCurrentDay ;
        this.start_year = start_year ;
        this.end_year = end_year ;
    }

    @Override
    public Fragment getItem(int position) {
        FragmentDatePickerPersianView fragment =  new FragmentDatePickerPersianView();
        int year_position_start_of =  position - (position % 12);
        int year_current_position_start_of = Math.abs(this.current_item_position -(this.current_item_position % 12));
        int month_position = (position % 12) + 1;
        if(position > current_item_position) {
            int year ;
            String month = DatePickerPersian.get_instance().get_name_month(month_position);

            if(this.end_year < year_now) {
                year = (Math.abs(year_current_position_start_of - year_position_start_of) / 12) + start_year ;
            }else  {
                year = (Math.abs(year_current_position_start_of - year_position_start_of) / 12) + this.year_now ;
            }

            return fragment.newInstance(get_date(month_position) , year,  month , background_lable_month , this.backgroundColorDay , this.backgroundColorCurrentDay);


        }else  if(position == current_item_position) {
            if(this.end_year < year_now) {
                return fragment.newInstance(get_date(month_position) , this.start_year,  "فروردین" , background_lable_month ,this.backgroundColorDay , this.backgroundColorCurrentDay);

            }else  {
                return fragment.newInstance(get_date(month_position) , year_now, month_now , background_lable_month  ,this.backgroundColorDay , this.backgroundColorCurrentDay);

            }
        }else  {
            int year = year_now - (Math.abs(year_current_position_start_of - year_position_start_of) / 12) ;
            String month = DatePickerPersian.get_instance().get_name_month(month_position) ;
            return fragment.newInstance(get_date(month_position) , year, month , background_lable_month , this.backgroundColorDay , this.backgroundColorCurrentDay);
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
