package com.picker.date.persian.parisa.soheil.PersianDatePickerModule;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Adapter.DatePickerAdpaterContainer;
import Interface.DayClickListenerPersianDatePicker;
import Interface.ListenerDateListPickerPersian;

public class DatePickerPersian {

    private static DatePickerPersian instance;

    public static final String TRANSALATE_ANIM = "TRANSALATE_ANIM";
    public static final String ROTATE_ANIM = "ROTATE_ANIM";


    private static  Typeface isans_noraml ;
    private static  Typeface isans_bold ;


    private static final String TAG = "DatePickerPersian";

    private final int FARVARDIN = 1;
    private final int ORDIBEHESHT = 2;
    private final int KHORDAD = 3;
    private final int TIR = 4;
    private final int MORDAD = 5;
    private final int SHAHRIVAR = 6;
    private final int MEHR = 7;
    private final int ABAN = 8;
    private final int AZAR = 9;
    private final int DAAY = 10;
    private final int BAHMAN = 11;
    private final int ESFAND = 12;

    private  Context context ;


    public static DatePickerPersian get_instance() {
        if (instance == null) {
            return instance = new DatePickerPersian();
        } else {
            return instance;
        }
    }

    public void show_Dialog(final Context context, String style, @Nullable Typeface typeface, int background_color_header, final int background_date_picker_txt_month,
                            final int backgroundColorDay , final int backgroundColorCurrentDay, final DayClickListenerPersianDatePicker clickDayListener) {
        final Dialog dialog;
        this.context = context;

        if (style != null) {
            if (style.equals(TRANSALATE_ANIM)) {
                dialog = new Dialog(context, R.style.DialogTranslationAnim);
            } else {
                dialog = new Dialog(context, R.style.DialogRotateAnim);
            }
        } else {
            dialog = new Dialog(context);
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_date_picker_persian);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final RecyclerView recycler_dialog = (RecyclerView) dialog.findViewById(R.id.recycler_dialog);
        final TextView txt_year_dialog = (TextView) dialog.findViewById(R.id.txt_year_dialog);
        LinearLayout container_dialog = (LinearLayout) dialog.findViewById(R.id.container_dialog);
        TextView txt_month_dialog = (TextView) dialog.findViewById(R.id.txt_month_dialog);
        TextView txt_day_dialog = (TextView) dialog.findViewById(R.id.txt_day_dialog);
        ImageView img_back_dialog_date_picker = (ImageView) dialog.findViewById(R.id.img_back_dialog_date_picker);
        ImageView img_go_dialog_date_picker = (ImageView) dialog.findViewById(R.id.img_go_dialog_date_picker);
        if (background_color_header != 0) {
            container_dialog.setBackgroundColor(background_color_header);
        }
        if (typeface != null) {
            txt_year_dialog.setTypeface(typeface);
            txt_month_dialog.setTypeface(typeface);
            txt_day_dialog.setTypeface(typeface);
        }
        recycler_dialog.setLayoutManager(new LinearLayoutManager(context));
        final ArrayList<DatePickerModelDialog> data_dialog = new ArrayList<>();
        list_date(new ListenerDateListPickerPersian() {
            @Override
            public void add_list(ArrayList<Integer> data) {
                data_dialog.add(new DatePickerModelDialog(data, Integer.parseInt(getDateByLang(context ,System.currentTimeMillis()).split("/")[0]), get_name_month(data_dialog.size() + 1 , context)));
            }
        });
        recycler_dialog.setAdapter(new DatePickerAdpaterContainer(dialog, context, data_dialog, clickDayListener, background_date_picker_txt_month , backgroundColorDay , backgroundColorCurrentDay));
        recycler_dialog.scrollToPosition(Integer.parseInt(getDateByLang(context ,System.currentTimeMillis()).split("/")[1]) - 1);
        txt_year_dialog.setText(getDateByLang(context, System.currentTimeMillis()).split("/")[0]);
        txt_month_dialog.setText(get_name_month(Integer.parseInt(getDateByLang(context , System.currentTimeMillis()).split("/")[1]) , context));
        txt_day_dialog.setText(getDateByLang(context , System.currentTimeMillis()).split("/")[2]);
        img_back_dialog_date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int year_back = Integer.parseInt(txt_year_dialog.getText().toString()) - 1;
                txt_year_dialog.setText(year_back + "");
                data_dialog.clear();
                list_date(new ListenerDateListPickerPersian() {
                    @Override
                    public void add_list(ArrayList<Integer> data) {
                        data_dialog.add(new DatePickerModelDialog(data, year_back, get_name_month(data_dialog.size() + 1 , context)));
                    }
                });
                recycler_dialog.setAdapter(new DatePickerAdpaterContainer(dialog, context, data_dialog, clickDayListener, background_date_picker_txt_month , backgroundColorDay , backgroundColorCurrentDay));
            }
        });
        img_go_dialog_date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int year_go = Integer.parseInt(txt_year_dialog.getText().toString()) + 1;
                txt_year_dialog.setText(year_go + "");
                data_dialog.clear();
                list_date(new ListenerDateListPickerPersian() {
                    @Override
                    public void add_list(ArrayList<Integer> data) {
                        data_dialog.add(new DatePickerModelDialog(data, year_go, get_name_month(data_dialog.size() + 1 , context)));
                    }
                });
                recycler_dialog.setAdapter(new DatePickerAdpaterContainer(dialog, context, data_dialog, clickDayListener, background_date_picker_txt_month , backgroundColorDay , backgroundColorCurrentDay));
            }
        });

        dialog.show();

    }


    private void list_date(ListenerDateListPickerPersian listener) {
        for (int i = 1; i < 13; i++) {
            ArrayList<Integer> data = new ArrayList<>();
            if (i <= 6) {
                for (int j = 1; j < 32; j++) {
                    data.add(j);
                }
                if (listener != null) {
                    listener.add_list(data);
                }
            } else if (i == 12) {
                for (int j = 1; j < 30; j++) {
                    data.add(j);
                }
                listener.add_list(data);
            } else {
                for (int j = 1; j < 31; j++) {
                    data.add(j);
                }
                listener.add_list(data);

            }

        }

    }

    private String getCurrentLocal(Context context) {
        return context.getResources().getConfiguration().locale.getLanguage();
    }


    public boolean isDevicePersian(Context context) {
        return getCurrentLocal(context).equals("fa");
    }
    public String getDateByLang(Context context , long date) {
        if(getCurrentLocal(context).equals("fa")) {
            return new JalaliCalendar().getJalaliDate(new Date(date));
        }else {
            return new SimpleDateFormat("yyyy/MM/dd").format(new Date(date));
        }
    }


    public  int get_number_month(String month , Context context) {
        int month_int = 0;
        if(context.getString(R.string.farvardin).equals(month)) {
            month_int = 1 ;
        }else if(context.getString(R.string.ordibehesht).equals(month)) {
            month_int = 2 ;
        }else if(context.getString(R.string.khordad).equals(month)) {
            month_int = 3 ;
        }else if(context.getString(R.string.tir).equals(month)) {
            month_int = 4 ;
        }else if(context.getString(R.string.mordad).equals(month)) {
            month_int = 5 ;
        }else if(context.getString(R.string.shahrivar).equals(month)) {
            month_int = 6 ;
        }else if(context.getString(R.string.mehr).equals(month)) {
            month_int = 7 ;
        }else if(context.getString(R.string.aban).equals(month)) {
            month_int = 8 ;
        }else if(context.getString(R.string.azar).equals(month)) {
            month_int = 9 ;
        }else if(context.getString(R.string.dey).equals(month)) {
            month_int = 10 ;
        }else if(context.getString(R.string.bahman).equals(month)) {
            month_int = 11 ;
        }else if(context.getString(R.string.esfand).equals(month)) {
            month_int = 12 ;
        }


        return  month_int ;
    }
    public  String get_name_month(int month , Context context) {
        String month_name = "";
        switch (month) {
            case  FARVARDIN :
                month_name = context.getString(R.string.farvardin) ;
                break;
            case  ORDIBEHESHT :
                month_name = context.getString(R.string.ordibehesht) ;
                break;
            case  KHORDAD :
                month_name = context.getString(R.string.khordad) ;
                break;
            case  TIR :
                month_name = context.getString(R.string.tir) ;
                break;
            case  MORDAD :
                month_name = context.getString(R.string.mordad) ;
                break;
            case  SHAHRIVAR :
                month_name = context.getString(R.string.shahrivar) ;
                break;
            case  MEHR :
                month_name = context.getString(R.string.mehr) ;
                break;
            case  ABAN :
                month_name = context.getString(R.string.aban) ;
                break;
            case  AZAR :
                month_name = context.getString(R.string.azar) ;
                break;
            case  DAAY :
                month_name = context.getString(R.string.dey) ;
                break;
            case  BAHMAN :
                month_name = context.getString(R.string.bahman) ;
                break;
            case  ESFAND :
                month_name = context.getString(R.string.esfand) ;
                break;

        }
        return  month_name ;
    }


}
