package kavoshgar1.soheil.com.appintro;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by soheilmohammadi on 11/5/17.
 */

public class Circle extends ImageView {

    private Paint paintCircleStroke ;
    private Paint paintCircleFill ;


    private  Boolean is_fill ;
    private  int color ;
    private  float radius  ;

    public Circle(Context context) {
        super(context);
        init();
    }

    public Circle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Circle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Circle(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }




    public void build(float radius ,Boolean is_fill , int color) {
        this.is_fill = is_fill ;
        this.color = color ;
        this.radius = radius ;
        invalidate();
    }

    private  void init() {

        paintCircleStroke = new Paint();
        paintCircleStroke.setColor(Color.RED);
        paintCircleStroke.setStyle(Paint.Style.STROKE);
        paintCircleStroke.setStrokeWidth(1);
        paintCircleStroke.setAntiAlias(true);


        paintCircleFill = new Paint();
        paintCircleFill.setAntiAlias(true);
        paintCircleFill.setColor(Color.RED);
        paintCircleFill.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(radius != 0 && color != 0 ) {
            if(is_fill) {
                paintCircleFill.setColor(color);
                canvas.drawCircle(getWidth()/2 , getHeight()/2 , radius , paintCircleFill  );
            }else  {
                paintCircleStroke.setColor(color);
                canvas.drawCircle(getWidth()/2 , getHeight()/2 ,  radius , paintCircleStroke);
            }
        }else  {
            canvas.drawCircle(getWidth()/2 , getHeight()/2 , 10 , paintCircleFill );
        }
    }
}
