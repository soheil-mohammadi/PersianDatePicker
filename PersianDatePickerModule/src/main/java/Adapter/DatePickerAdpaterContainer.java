package Adapter;


import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.picker.date.persian.parisa.soheil.persiandatepicker.DatePickerModelDialog;
import com.picker.date.persian.parisa.soheil.persiandatepicker.DatePickerPersian;
import com.picker.date.persian.parisa.soheil.persiandatepicker.R;
import com.picker.date.persian.parisa.soheil.persiandatepicker.RtlGridLayout;
import java.util.ArrayList;

import Interface.DayClickListenerPersianDatePicker;

public class DatePickerAdpaterContainer extends RecyclerView.Adapter<DatePickerAdpaterContainer.view_holder> {


    private Context context ;
    private Dialog dialog ;
    private ArrayList<DatePickerModelDialog> date ;
    private DayClickListenerPersianDatePicker clickDayListener ;
    private int background_date_picker_txt_month ;
    private  int backgroundColorDay;
    private   int backgroundColorCurrentDay;

    public DatePickerAdpaterContainer(Dialog dialog , Context context  , ArrayList<DatePickerModelDialog> date , DayClickListenerPersianDatePicker clickDayListener , int background_date_picker_txt_month
    ,  int backgroundColorDay ,int backgroundColorCurrentDay ) {
        this.context = context ;
        this.dialog = dialog ;
        this.date = date ;
        this.clickDayListener = clickDayListener ;
        this.background_date_picker_txt_month = background_date_picker_txt_month ;
        this.backgroundColorDay = backgroundColorDay ;
        this.backgroundColorCurrentDay = backgroundColorCurrentDay ;
    }

    @Override
    public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new view_holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_date_picker_view , parent , false));
    }

    @Override
    public void onBindViewHolder(view_holder holder, int position) {
        DatePickerModelDialog data = this.date.get(position);
        holder.date_picker_recycler.setLayoutManager(new RtlGridLayout(this.context ,7  , LinearLayoutManager.VERTICAL ,false));
        holder.date_picker_recycler.setAdapter(new DatePickerAdapterDays(this.dialog , this.backgroundColorDay, this.backgroundColorCurrentDay,  data.data, data.year , DatePickerPersian.get_instance().get_number_month(data.month) , this.clickDayListener));
        holder.date_picker_txt_year.setVisibility(View.GONE);
        holder.date_picker_txt_month.setText(data.month);
        if(background_date_picker_txt_month !=0){
            holder.date_picker_txt_month.setBackgroundColor(background_date_picker_txt_month);
        }
    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    public class view_holder extends  RecyclerView.ViewHolder {

        public RecyclerView date_picker_recycler;
        public TextView date_picker_txt_year;
        public TextView date_picker_txt_month;

        public view_holder(View view) {
            super(view);
            date_picker_recycler = (RecyclerView) view.findViewById(R.id.date_picker_recycler);
            date_picker_txt_year = (TextView) view.findViewById(R.id.date_picker_txt_year);
            date_picker_txt_month = (TextView) view.findViewById(R.id.date_picker_txt_month);
        }
    }
}
