package FontLoading;

import android.content.Context;
import android.graphics.Typeface;
import android.util.LruCache;

import com.picker.date.persian.parisa.soheil.PersianDatePickerModule.DatePickerPersian;

/**
 * Created by soheilmohammadi on 8/4/18.
 */

public class Font {

    private static LruCache<String , Typeface> cache = new LruCache<>(16);
    private static final  String spliter = "@H@";

    public static Typeface getFont(Context context , String fontName , Boolean isTypeBold) {
        Typeface cacheFont = readFromCache(context ,fontName , isTypeBold) ;
        if(cacheFont != null)
            return cacheFont;

        return addNewFont(context , fontName , isTypeBold);
    }

    private static Typeface addNewFont(Context context , String fontName , Boolean isTypeBold) {
        Boolean isRtl = DatePickerPersian.get_instance().isDevicePersian(context);
        Typeface font = Typeface.createFromAsset(context.getAssets() , (isRtl ? "fonts-ldrtl/" : "fonts/") + fontName
                + (isTypeBold ? "_bold" : "") + ".ttf") ;
        cache.put(fontName + (isTypeBold ? "_bold" : "") + spliter + isRtl , font);
        return font;
    }


    private static Typeface readFromCache(Context context , String fontName , Boolean isTypeBold) {
        return cache.get(fontName  + (isTypeBold ? "_bold" : "") + spliter + DatePickerPersian.get_instance().isDevicePersian(context));
    }

}
