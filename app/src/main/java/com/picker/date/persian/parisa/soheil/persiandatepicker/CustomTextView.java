package com.picker.date.persian.parisa.soheil.persiandatepicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;


public class CustomTextView extends android.support.v7.widget.AppCompatTextView {

    private static  Typeface isans_noraml ;
    private static  Typeface isans_bold ;

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
                        setTypeface(get_isnas_normal(getContext()));
                        break;
                    case "bold" :
                        setTypeface(get_isnas_bold(getContext()));
                }
            }else  {
                setTypeface(get_isnas_normal(getContext()));
            }
        }else  {
            setTypeface(get_isnas_normal(getContext()));
        }
    }

    private Typeface get_isnas_bold(Context context) {
        if(isans_bold == null) {
            return  isans_bold = Typeface.createFromAsset(context.getAssets() , "fonts/isans_bold.ttf");
        }else  {
            return  isans_bold ;
        }
    }

    private Typeface get_isnas_normal(Context context) {
        if(isans_noraml == null) {
            return  isans_noraml = Typeface.createFromAsset(context.getAssets() , "fonts/isans_normal.ttf");
        }else  {
            return  isans_noraml ;
        }
    }
}
