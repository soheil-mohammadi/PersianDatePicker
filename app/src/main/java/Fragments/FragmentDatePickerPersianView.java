package Fragments;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.picker.date.persian.parisa.soheil.persiandatepicker.DatePickerPersian;
import com.picker.date.persian.parisa.soheil.persiandatepicker.R;
import com.picker.date.persian.parisa.soheil.persiandatepicker.RtlGridLayout;
import java.util.ArrayList;

import Adapter.DatePickerAdapterView;
import Interface.DayClickListenerPersianDatePicker;


public class FragmentDatePickerPersianView extends Fragment {

    private static final String TAG = "LOG";


    private  int year ;
    private  String month ;
    private ArrayList<Integer> data;
    private int background_lable_month ;
    private DayClickListenerPersianDatePicker clickListenerday ;

    private  RecyclerView date_picker_recycler ;
    private TextView date_picker_txt_year ;
    private TextView date_picker_txt_month ;
    private TextView txt_saturday ;
    private TextView txt_sunday ;
    private TextView txt_monday ;
    private TextView txt_tuesday ;
    private TextView txt_wednesday ;
    private TextView txt_thursday ;
    private TextView txt_friday ;

    private    final  String NUMBER_PAGE = "NUMBER_PAGE";
    private    final  String  YEAR = "YEAR";
    private    final  String MONTH = "MONTH";
    private    final  String DATA = "ADAPTER";
    private    final  String BACK_COLOR_MONTH = "BACK_COLOR_MONTH";
    private  int page ;


    public  FragmentDatePickerPersianView newInstance(int position , ArrayList<Integer> data , int year , String month ,  int background_lable_month) {
        Bundle args = new Bundle();
        args.putInt(YEAR ,year);
        args.putString(MONTH ,month);
        args.putIntegerArrayList(DATA , data);
        args.putInt(BACK_COLOR_MONTH , background_lable_month);
        FragmentDatePickerPersianView fragment = new FragmentDatePickerPersianView();
        fragment.setArguments(args);
        return fragment ;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt(NUMBER_PAGE);
        this.year = getArguments().getInt(YEAR);
        this.month = getArguments().getString(MONTH);
        this.data = getArguments().getIntegerArrayList(DATA);
        this.background_lable_month = getArguments().getInt(BACK_COLOR_MONTH);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_picker_view , container , false) ;
        date_picker_recycler = (RecyclerView) view.findViewById(R.id.date_picker_recycler);
        date_picker_txt_year = (TextView) view.findViewById(R.id.date_picker_txt_year);
        date_picker_txt_month = (TextView) view.findViewById(R.id.date_picker_txt_month);
        txt_saturday = (TextView) view.findViewById(R.id.txt_saturday);
        txt_sunday = (TextView) view.findViewById(R.id.txt_sunday);
        txt_monday = (TextView) view.findViewById(R.id.txt_monday);
        txt_tuesday = (TextView) view.findViewById(R.id.txt_tuesday);
        txt_wednesday = (TextView) view.findViewById(R.id.txt_wednesday);
        txt_thursday = (TextView) view.findViewById(R.id.txt_thursday);
        txt_friday = (TextView) view.findViewById(R.id.txt_friday);



        date_picker_recycler.setLayoutManager(new RtlGridLayout(getContext() , 7 , LinearLayoutManager.VERTICAL , false));
        date_picker_recycler.setAdapter(new DatePickerAdapterView( null, this.data, this.year , DatePickerPersian.get_instance().get_number_month(this.month) , this.clickListenerday));
        date_picker_txt_year.setText(this.year + "");
        date_picker_txt_month.setText(this.month);
        if(background_lable_month != 0) {
          date_picker_txt_month.setBackgroundColor(background_lable_month);
        }

        return  view ;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            clickListenerday = (DayClickListenerPersianDatePicker) activity ;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement MyInterface");
        }
    }

    public void set_month_font(Typeface typeface) {
        date_picker_txt_month.setTypeface(typeface);
    }

    public void set_year_font(Typeface typeface) {
        date_picker_txt_year.setTypeface(typeface);
    }

    public void set_day_font(Typeface typeface) {
        date_picker_txt_year.setTypeface(typeface);
        date_picker_txt_month.setTypeface(typeface);
        txt_friday.setTypeface(typeface);
        txt_thursday.setTypeface(typeface);
        txt_wednesday.setTypeface(typeface);
        txt_tuesday.setTypeface(typeface);
        txt_monday.setTypeface(typeface);
        txt_sunday.setTypeface(typeface);
        txt_saturday.setTypeface(typeface);
    }
}
