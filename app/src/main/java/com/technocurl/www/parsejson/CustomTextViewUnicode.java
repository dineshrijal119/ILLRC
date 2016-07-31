package com.technocurl.www.parsejson;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by deadlydragger on 6/25/16.
 */
public class CustomTextViewUnicode extends TextView {
    public CustomTextViewUnicode(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initFont();
    }

    public CustomTextViewUnicode(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFont();
    }

    public CustomTextViewUnicode(Context context) {
        super(context);
        initFont();
    }

    private void initFont() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"myfont.ttf");
        setTypeface(tf);
    }
}