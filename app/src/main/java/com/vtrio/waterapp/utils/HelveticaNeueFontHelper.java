package com.vtrio.waterapp.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by Tiji on 25-05-2018.
 */

public class HelveticaNeueFontHelper {

    private static Typeface helveticNeu = null;
    private static Typeface helveticaNeult = null;
    private static Typeface helveticaneuLight = null;
    private static Typeface helveticaneuLt = null;
    private static Typeface helveticaneuMedium = null;


    private static Typeface getHelveticaneuTypeface(Context context) {
        if (helveticNeu == null)
            helveticNeu = Typeface.createFromAsset(context.getAssets(), "fonts/helveticaneue.ttf");
        return helveticNeu;
    }

    public static TextView applyHelveticaneuFont(Context context, TextView textView) {
        if (textView != null)
            textView.setTypeface(getHelveticaneuTypeface(context));
        return textView;
    }

    private static Typeface getHelveticaneuitTypeface(Context context) {
        if (helveticaNeult == null)
            helveticaNeult = Typeface.createFromAsset(context.getAssets(), "fonts/helveticaneueit.ttf");
        return helveticaNeult;
    }

    public static TextView applyHelveticaneultFont(Context context, TextView textView) {
        if (textView != null)
            textView.setTypeface(getHelveticaneuitTypeface(context));
        return textView;
    }


    public static Typeface getHelveticaneuelightTypeface(Context context) {
        if (helveticaneuLight == null)
            helveticaneuLight = Typeface.createFromAsset(context.getAssets(), "fonts/helveticaneuelight.ttf");
        return helveticaneuLight;
    }

    public static TextView applyHelveticaneuelightFont(Context context, TextView textView) {
        if (textView != null)
            textView.setTypeface(getHelveticaneuelightTypeface(context));
        return textView;
    }

    private static Typeface getHelveticaneueLtTypeface(Context context) {
        if (helveticaneuLt == null)
            helveticaneuLt = Typeface.createFromAsset(context.getAssets(), "fonts/helveticaneuelt.ttf");
        return helveticaneuLt;
    }

    public static TextView applyHelveticaneueLtFont(Context context, TextView textView) {
        if (textView != null)
            textView.setTypeface(getHelveticaneueLtTypeface(context));
        return textView;
    }


    private static Typeface getHelveticaneuemedTypeface(Context context) {
        if (helveticaneuMedium == null)
            helveticaneuMedium = Typeface.createFromAsset(context.getAssets(), "fonts/helveticaneuemed.ttf");
        return helveticaneuMedium;
    }

    public static TextView applyHelveticaneuemedFont(Context context, TextView textView) {
        if (textView != null)
            textView.setTypeface(getHelveticaneuemedTypeface(context));
        return textView;
    }

}
