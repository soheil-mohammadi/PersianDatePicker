package com.picker.date.persian.parisa.soheil.persiandatepicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;


public class CustomTextView extends android.support.v7.widget.AppCompatTextView {

    public CustomTextView(Context context) {
        super(context);
        set_font(null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        set_font(attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        set_font(attrs);
    }

    private void set_font(AttributeSet attrs) {
        if(attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs , R.styleable.CustomTextView);
            String font_name = typedArray.getString(R.styleable.CustomTextView_isans);
            if(font_name != null) {
                switch (font_name) {
                    case  "normal" :
                        setTypeface(DatePickerPersian.get_instance().get_isnas_normal(getContext()));
                        break;
                    case "bold" :
                        setTypeface(DatePickerPersian.get_instance().get_isnas_bold(getContext()));
                }
            }else  {
                setTypeface(DatePickerPersian.get_instance().get_isnas_normal(getContext()));
            }
        }else  {
            setTypeface(DatePickerPersian.get_instance().get_isnas_normal(getContext()));
        }
    }


}
