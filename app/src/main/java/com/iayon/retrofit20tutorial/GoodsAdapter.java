package com.iayon.retrofit20tutorial;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import models.GoodsBean;

/**
 * Created by Ashiq Uz Zoha on 9/13/15.
 * Dhrubok Infotech Services Ltd.
 * ashiq.ayon@gmail.com
 */
public class GoodsAdapter extends BaseAdapter {

    private List<GoodsBean> users ;
    private Context context ;

    public GoodsAdapter(Context ctx, List<GoodsBean> items) {
        super();
        this.context = ctx ;
        this.users = items ;
    }

    @Override
    public int getCount() {
        return this.users.size();
    }

    @Override
    public long getItemId(int i) {
        return 0 ;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.cell, viewGroup, false);
        TextView tv = (TextView) v.findViewById(R.id.textview);
        GoodsBean user = users.get(i);
        tv.setText(user.getGoods_name());
        Log.d("Adapter", user.getGoods_name());
        return v;
    }
}
