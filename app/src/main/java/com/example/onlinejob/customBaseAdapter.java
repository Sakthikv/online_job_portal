package com.example.onlinejob;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class customBaseAdapter extends BaseAdapter {
    Context context;
    List<String> companyname=new ArrayList<>();
    List<String> jobName=new ArrayList<>();
    List<String> salary=new ArrayList<>();
    LayoutInflater inflater;
    public customBaseAdapter(Context ctx, List companyNamelist, List jobnameList, List salarylist){
        this.context=ctx;
        this.companyname=companyNamelist;
        this.jobName=jobnameList;
        this.salary=salarylist;
        this.inflater=LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return companyname.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.activity_custom_list_view,null);
        TextView txtview1=(TextView) convertView.findViewById(R.id.companyname);
        TextView txtview2=(TextView) convertView.findViewById(R.id.jobName);
        TextView txtview3=(TextView) convertView.findViewById(R.id.salary);
        txtview1.setText("Company name: "+companyname.get(position));
        txtview2.setText("Job: "+jobName.get(position));
        txtview3.setText("Salary: "+salary.get(position));

        return convertView;
    }
}
