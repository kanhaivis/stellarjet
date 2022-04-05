package com.ns.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTxtView_ColumbiaSerialBold extends TextView {
    public CustomTxtView_ColumbiaSerialBold(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public CustomTxtView_ColumbiaSerialBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public CustomTxtView_ColumbiaSerialBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }


    private void applyCustomFont(Context context){
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "font/columbia_serial_bold.otf");
        setTypeface(custom_font);
    }
}
