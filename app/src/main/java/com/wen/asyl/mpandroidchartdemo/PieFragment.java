package com.wen.asyl.mpandroidchartdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Description：xx <br/>
 * Copyright (c) 2018<br/>
 * This program is protected by copyright laws <br/>
 * Date:2018-07-11 14:20
 *
 * @author 姜文莒
 * @version : 1.0
 */
public class PieFragment extends Fragment {
    private static final String DATA_KEY = "piefragment_data_key";
    private MonthBean mData;
    private PieChart mChart;
    private TextView mTvDes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
            if (arguments != null) {
                mData = (MonthBean) arguments.getParcelable(DATA_KEY);
            }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pie, null);
        mChart =(PieChart) view.findViewById(R.id.pc_chart);
        mTvDes = (TextView) view.findViewById(R.id.tv_des);
        initView();
        return view;
    }

    private void initView() {
        setData();
        mChart.getLegend().setEnabled(false);
        mChart.setDrawSliceText(false);
        mChart.getData().getDataSet().setDrawValues(false);
        mChart.setCenterText(getCenterText());
        //设置详情，也就什么读不显示了
        mChart.setDescription("");
        mChart.setRotationEnabled(false);
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                mTvDes.setVisibility(View.VISIBLE);
                float proportion=360f/mData.getSum();
                float angle=90-mData.obj.get(e.getXIndex()).value*proportion/2-mData.getSum(e.getXIndex())*proportion;
                mChart.setRotationAngle(angle);
                upDateDesText(e.getXIndex());
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void upDateDesText(int index) {
        mTvDes.setText(mData.obj.get(index).title+":"+mData.obj.get(index).value+"元");
    }

    private void setData() {
        List<String> titles=new ArrayList<>();
        List<Entry> entrys=new ArrayList<>();
        for (int i=0;i<mData.obj.size();i++){
            MonthBean.PieBean pieBean=mData.obj.get(i);
            titles.add(pieBean.title);
            entrys.add(new Entry(pieBean.value,i));
        }
        PieDataSet dataSet=new PieDataSet(entrys,"");
        //设置颜色背景
        dataSet.setColors(new int[]{Color.rgb(216, 77, 719), Color.rgb(183, 56, 63), Color.rgb(247, 85, 47)});
        PieData pieData=new PieData(titles,dataSet);
        pieData.setValueTextSize(22);
        mChart.setData(pieData);
    }

    public static PieFragment newInstance(MonthBean data) {

        Bundle args = new Bundle();
        args.putParcelable(DATA_KEY,data);
        PieFragment fragment = new PieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CharSequence getCenterText() {
        CharSequence centerText = "总支出\n" + mData.getSum() + "元";
        SpannableString spannableString=new SpannableString(centerText);
        spannableString.setSpan(new ForegroundColorSpan(Color.rgb(178, 178,178)), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(64, true), 3, centerText.length()-1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}
