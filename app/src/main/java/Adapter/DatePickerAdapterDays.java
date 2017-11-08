package Adapter;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.picker.date.persian.parisa.soheil.persiandatepicker.CircleText;
import com.picker.date.persian.parisa.soheil.persiandatepicker.DatePickerPersian;
import com.picker.date.persian.parisa.soheil.persiandatepicker.JalaliCalendar;
import com.picker.date.persian.parisa.soheil.persiandatepicker.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Interface.DayClickListenerPersianDatePicker;

public class DatePickerAdapterView extends RecyclerView.Adapter<DatePickerAdapterView.view_holder> {


    private static final String TAG = "LOG";

    private  Dialog dialog ;

    private  int year ;
    private  int month ;
    private   DayClickListenerPersianDatePicker clickDayListener ;

    private  Boolean is_set_first_position = false ;
    private  int data_position = 0 ;
    private  Calendar calendar  = Calendar.getInstance();
    private ArrayList<Integer> data ;
    private  String[] jalali_date ;

    public DatePickerAdapterView(@Nullable  Dialog dialog , ArrayList<Integer> data  , int year , int month , DayClickListenerPersianDatePicker clickDayListener) {
        this.data = data ;
        this.dialog = dialog ;
        this.clickDayListener = clickDayListener ;
        jalali_date = DatePickerPersian.get_instance().get_jalali_date(System.currentTimeMillis());
        JalaliCalendar jalaliCalendar = new JalaliCalendar();
        Date date ;
        if(year == 0 || month == 0) {
            this.year =  Integer.parseInt(this.jalali_date[0]);
            this.month =  Integer.parseInt(this.jalali_date[1]);
            date = jalaliCalendar.getGregorianDate(this.jalali_date[0] + "/" + this.jalali_date[1] + "/" + 1);
        }else  {
            this.year = year ;
            this.month =  month;
            date = jalaliCalendar.getGregorianDate(year + "/" + month   + "/" + 1);
        }
        calendar.setTimeInMillis(date.getTime());
    }

    @Override
    public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new view_holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date_picker ,parent , false));
    }

    @Override
    public void onBindViewHolder(final view_holder holder, int position) {
        Integer data_item = this.data.get(data_position);
        if(!is_set_first_position) {
            switch (calendar.get(Calendar.DAY_OF_WEEK)) {
                //یکشنبه :
                case  1 :
                    if(position == 1) {
                        set_day_text(holder , data_item);
                    }
                    break;
                //دوشنبه :
                case  2 :
                    if(position == 2) {
                        set_day_text(holder , data_item);
                    }
                    break;
                //سه شنبه :
                case  3 :
                    if(position == 3) {
                        set_day_text(holder , data_item);
                    }
                    break;
                //چهارشنبه :
                case  4 :
                    if(position == 4) {
                        set_day_text(holder , data_item);
                    }
                    break;
                //پنج شنبه :
                case  5 :
                    if(position == 5) {
                        set_day_text(holder , data_item);
                    }
                    break;
                //جمعه :
                case  6 :
                    if(position == 6) {
                        set_day_text(holder , data_item);
                    }
                    break;
                //شنبه :
                case  7:
                    set_day_text(holder , data_item);
                    break;
            }
        }else  {


            if(position+1 <= data.size()) {
                set_day_text(holder , data_item);

            }else  {
                if(calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY ){
                    set_day_text(holder , data_item);
                }
            }

        }
        handle_click_days(holder);
    }


    @Override
    public int getItemCount() {
        return data.size() + calendar.get(Calendar.DAY_OF_WEEK) ;
    }

    class  view_holder extends  RecyclerView.ViewHolder {

        View line_border_bottom ;
        CircleText txt_day ;

        view_holder(View view) {
            super(view);
            line_border_bottom =  view.findViewById(R.id.line_border_bottom);
            txt_day = (CircleText) view.findViewById(R.id.txt_day);
        }
    }

    private  void set_day_text(view_holder holder , Integer data) {

        holder.txt_day.build(20 , false , Color.GREEN);
        holder.txt_day.setText(data + "");
        holder.line_border_bottom.setVisibility(View.VISIBLE);

        if(this.data.size()  > data_position+1 ) {
            data_position++;
        }
        if((this.year + "/" + this.month + "/" + data).equals(Integer.parseInt(jalali_date[0]) +"/" +Integer.parseInt(jalali_date[1])+ "/" + Integer.parseInt(jalali_date[2])) ) {
            holder.line_border_bottom.setBackgroundColor(Color.GREEN);
            holder.txt_day.setText(data + "");
        }
        is_set_first_position = true ;
    }


    private void handle_click_days(final view_holder holder ) {
        final int colorFrom = Color.TRANSPARENT;
        final int colorTo = Color.GREEN;

            holder.txt_day.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    clickDayListener.on_click(year, month, Integer.parseInt(holder.txt_day.getText().toString()));
                    ObjectAnimator colorAnim = ObjectAnimator.ofObject(holder.txt_day , "backgroundColor" , new ArgbEvaluator() , colorFrom , colorTo  );
                    colorAnim.setDuration(500);
                    colorAnim.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });

                    colorAnim.start();
                    if(dialog != null ){
                        dialog.cancel();
                    }
                }
            });
        }

}
