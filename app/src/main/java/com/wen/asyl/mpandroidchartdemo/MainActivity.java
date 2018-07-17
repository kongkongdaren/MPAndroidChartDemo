package com.wen.asyl.mpandroidchartdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mVp;
    private String mJson = "[{\"date\":\"2016年5月\",\"obj\":[{\"title\":\"外卖\",\"value\":34}," +
            "{\"title\":\"娱乐\",\"value\":21},{\"title\":\"其他\",\"value\":45}]}," +
            "{\"date\":\"2016年6月\",\"obj\":[{\"title\":\"外卖\",\"value\":32}," +
            "{\"title\":\"娱乐\",\"value\":22},{\"title\":\"其他\",\"value\":42}]}," +
            "{\"date\":\"2016年7月\",\"obj\":[{\"title\":\"外卖\",\"value\":34}," +
            "{\"title\":\"娱乐\",\"value\":123},{\"title\":\"其他\",\"value\":24}]}," +
            "{\"date\":\"2016年8月\",\"obj\":[{\"title\":\"外卖\",\"value\":145}," +
            "{\"title\":\"娱乐\",\"value\":123},{\"title\":\"其他\",\"value\":124}]}]";

    private ArrayList<MonthBean> mData;
    private Button mBtnPre;
    private Button mBtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        Gson gson=new Gson();
        mData = gson.fromJson(mJson, new TypeToken<ArrayList<MonthBean>>() {
        }.getType());
    }

    private void initView() {
        mVp= (ViewPager) findViewById(R.id.vp_page);
        mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return PieFragment.newInstance(mData.get(position));
            }

            @Override
            public int getCount() {
                return mData.size();
            }
        });
        mBtnPre = (Button) findViewById(R.id.btn_pre);
        mBtnNext = (Button) findViewById(R.id.btn_next);
        mBtnPre.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        updateJumpText();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_pre:
                if(mVp.getCurrentItem()!=0){
                    mVp.setCurrentItem(mVp.getCurrentItem()-1);
                }
                break;
            case R.id.btn_next:
                if (mVp.getCurrentItem()!=mVp.getAdapter().getCount()-1){
                    mVp.setCurrentItem(mVp.getCurrentItem()+1);
                }
                break;
        }
        updateJumpText();
    }

    private void updateJumpText() {
        if(mVp.getCurrentItem()!=0){
            mBtnPre.setText(handlerText(mData.get(mVp.getCurrentItem()-1).date));
        }else{
            mBtnPre.setText("没有了!");
        }
        if (mVp.getCurrentItem()!=mVp.getAdapter().getCount()-1){
            mBtnNext.setText(handlerText(mData.get(mVp.getCurrentItem()+1).date));
        }else{
            mBtnNext.setText("没有了!");
        }
    }

    private CharSequence handlerText(String date) {
        return date.substring(date.indexOf("年")+1);
    }
}
