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
    private  String month_now = DatePickerPersian.get_instance().get_name_month(Integer.parseInt(DatePickerPersian.get_instance().get_jalali_date(System.currentTimeMillis())[1]) + "");
    private  int count ;
    private  int current_item_position ;
    private  int background_lable_month ;

    public ViewPagerAdapter(FragmentManager fm , int count , int current_item_position , int background_lable_month) {
        super(fm);
        this.count = count ;
        this.current_item_position = current_item_position ;
        this.background_lable_month = background_lable_month ;
    }

    @Override
    public Fragment getItem(int position) {
        FragmentDatePickerPersianView fragment =  new FragmentDatePickerPersianView();
        int year_position_start_of =  position - (position % 12);
        int year_current_position_start_of = this.current_item_position -(this.current_item_position % 12);
        int month_position = ((position) % 12) + 1;
        if(position > current_item_position) {
            int year = (Math.abs(year_current_position_start_of - year_position_start_of) / 12) + year_now ;
            String month = DatePickerPersian.get_instance().get_name_month(month_position + "") ;
            return fragment.newInstance(position ,get_date(month_position) ,year, month , background_lable_month);
        }else  if(position == current_item_position) {
            return fragment.newInstance(position , get_date(month_position) , year_now, month_now , background_lable_month);
        }else  {
            int year = year_now - (Math.abs(year_current_position_start_of - year_position_start_of) / 12) ;
            String month = DatePickerPersian.get_instance().get_name_month(month_position + "") ;
            return fragment.newInstance(position ,get_date(month_position) , year, month , background_lable_month);
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
        }else  {
            for (int i = 1; i <31 ; i++) {
                data.add(i);
            }
        }
        return  data ;
    }


}
