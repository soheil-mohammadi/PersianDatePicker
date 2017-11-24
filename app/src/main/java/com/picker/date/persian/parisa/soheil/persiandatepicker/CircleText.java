package com.picker.date.persian.parisa.soheil.persiandatepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import Interface.OnAnimationEnd;

/**
 * Created by soheilmohammadi on 11/5/17.
 */

public class CircleText extends TextView {

    private static final String TAG = "CircleText";

    private Paint paintCircleStroke;
    private Paint paintCircleFill;

    private Boolean is_animate = false;
    private int radius_animate = 0;

    private int color_txt_before_animate;
    private int color_txt_after_animate;
    private int color;
    private float radius;

    public CircleText(Context context) {
        super(context);
        init();
    }

    public CircleText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void build(float radius,  int color, int color_txt) {
        this.color = color;
        this.radius = radius;
        this.color_txt_before_animate = color_txt;
        invalidate();
    }


    public void animateBuild(final float radius, int color_txt , final OnAnimationEnd onAnimationEnd) {
        this.radius = radius;
        this.color_txt_after_animate = color_txt;
        is_animate = true;
        setTextColor(this.color_txt_after_animate);
        animate().rotationBy(360).setDuration(300).start();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (radius_animate != radius) {
                    try {
                        radius_animate++;
                        postInvalidate();
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                is_animate = false;
                postInvalidate();
                radius_animate = 0;
                onAnimationEnd.onAnimEnded();

            }
        });


        thread.start();
    }


    private void init() {

        paintCircleStroke = new Paint();
        paintCircleStroke.setColor(Color.RED);
        paintCircleStroke.setStyle(Paint.Style.STROKE);
        paintCircleStroke.setStrokeWidth(1);
        paintCircleStroke.setAntiAlias(true);


        paintCircleFill = new Paint();
        paintCircleFill.setAntiAlias(true);
        paintCircleFill.setColor(Color.RED);
        paintCircleFill.setStyle(Paint.Style.FILL);

        setTypeface(DatePickerPersian.get_instance().get_isnas_normal(getContext()));

    }


    @Override
    protected void onDraw(Canvas canvas) {


        if (is_animate) {
                paintCircleFill.setColor(color);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius_animate, paintCircleFill);
            } else {

                if (radius != 0 && color != 0) {
                    paintCircleStroke.setColor(color);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paintCircleStroke);

                } else {
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, 10, paintCircleFill);
                }
                setTextColor(color_txt_before_animate);
            }

            super.onDraw(canvas);
        }


}
