package com.picker.date.persian.parisa.soheil.PersianDatePickerModule;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by Soheil on 7/17/2017.
 */

public class RtlGridLayout extends GridLayoutManager {
    public RtlGridLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public RtlGridLayout(Context context, int spanCount) {
        super(context, spanCount);
    }

    public RtlGridLayout(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    protected boolean isLayoutRTL() {
        return true ;
    }
}
