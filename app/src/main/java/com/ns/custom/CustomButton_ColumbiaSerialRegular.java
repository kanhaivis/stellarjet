package com.ns.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

public class CustomButton_ColumbiaSerialRegular extends Button {
    public CustomButton_ColumbiaSerialRegular(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public CustomButton_ColumbiaSerialRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public CustomButton_ColumbiaSerialRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }


    private void applyCustomFont(Context context){
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "font/columbia_serial_regular.otf");
        setTypeface(custom_font);
    }
}
