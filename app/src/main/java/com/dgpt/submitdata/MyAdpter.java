package com.dgpt.submitdata;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdpter extends BaseAdapter {
    private Context context;
    private List<UserInfo>list;

    public MyAdpter(Context context,List<UserInfo>list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View item=View.inflate(context,R.layout.item_view,null);
        TextView tv_name=(TextView) item.findViewById(R.id.tv_name);
        TextView tv_password=(TextView) item.findViewById(R.id.tv_password);
        TextView tv_sex=(TextView) item.findViewById(R.id.tv_sex);
        UserInfo userInfo=list.get(i);
        tv_name.setText(userInfo.getUserName());
        tv_password.setText(userInfo.getPassword());
        tv_sex.setText(userInfo.getSex());
        return item;
    }
}
