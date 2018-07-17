package com.wen.asyl.mpandroidchartdemo;

import android.content.Context;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.utils.Utils;


/**
 * Description：xx <br/>
 * Copyright (c) 2018<br/>
 * This program is protected by copyright laws <br/>
 * Date:2018-07-11 15:34
 *
 * @author 姜文莒
 * @version : 1.0
 */
public class PiChart extends PieChart {

    public PiChart(Context context) {
        super(context);
    }

    public PiChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PiChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = (int) Utils.convertDpToPixel(MeasureSpec.getSize(widthMeasureSpec));
        setMeasuredDimension(
                Math.max(getSuggestedMinimumWidth(),
                        resolveSize(size,
                                widthMeasureSpec)),
                Math.max(getSuggestedMinimumHeight(),
                        resolveSize(size,
                                widthMeasureSpec)));
    }

}
