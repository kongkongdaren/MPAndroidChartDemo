package com.wen.asyl.mpandroidchartdemo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Description：xx <br/>
 * Copyright (c) 2018<br/>
 * This program is protected by copyright laws <br/>
 * Date:2018-07-11 14:35
 *
 * @author 姜文莒
 * @version : 1.0
 */
public class MonthBean implements Parcelable {
    public  String date;
    public ArrayList<PieBean> obj;



    @Override
    public String toString() {
        return "MonthBean{" +
                "date='" + date + '\'' +
                ", obj=" + obj +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(date);
    }

    public float getSum() {
        float sum=0;
        for (PieBean pieBean:obj){
            sum+=pieBean.value;
        }
        return sum;
    }
    public float getSum(int index) {
        float sum=0;
        for (int i=0;i<index;i++) {
            sum+=obj.get(i).value;
        }
        return sum;
    }

    class PieBean {
         public  String title;
         public int value;

        @Override
        public String toString() {
            return "PieBean{" +
                    "title='" + title + '\'' +
                    ", value=" + value +
                    '}';
        }
    }
}
