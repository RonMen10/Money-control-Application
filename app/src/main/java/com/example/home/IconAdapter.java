package com.example.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class IconAdapter extends ArrayAdapter<Icon> {
    public IconAdapter(Context context, ArrayList<Icon> defaultIconList){
        super(context, 0, defaultIconList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        return initView(position, convertView, parent);
    }
    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.spinner_icon, parent,false);
        }
        ImageView imageViewIcon = convertView.findViewById(R.id.imageView2);
        Icon currentItem = getItem(position);
        if (currentItem!=null){
            imageViewIcon.setImageResource(currentItem.getIconItem());
        }
        return convertView;
    }

}
