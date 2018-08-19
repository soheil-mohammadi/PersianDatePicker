package FontLoading;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.picker.date.persian.parisa.soheil.PersianDatePickerModule.R;


/**
 * Created by soheilmohammadi on 8/4/18.
 */

public class TextViewLoader extends android.support.v7.widget.AppCompatTextView {

    private Boolean isTypeBold = false;

    public TextViewLoader(Context context) {
        super(context);
        init(null);
    }

    public TextViewLoader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TextViewLoader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    private void init(AttributeSet attrs) {
        if(attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TextViewLoader);
            isTypeBold =  typedArray.getBoolean(R.styleable.TextViewLoader_bold , false) ;
            typedArray.recycle();
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setTypeface(Font.getFont(getContext() , "main" , isTypeBold));
    }
}
