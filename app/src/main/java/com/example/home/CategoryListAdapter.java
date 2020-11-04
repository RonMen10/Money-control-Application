package com.example.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryListAdapter extends ArrayAdapter {

    //to reference the Activity
    private final Categories context;

    //to store the icons
    private final Integer[] imageIDarray;

    //to store the list of category_name
    private final String[] nameArray;


    public CategoryListAdapter(Categories context, String[] nameArrayParam, Integer[] imageIDArrayParam){

        super(context,R.layout.row_category_list , nameArrayParam);
        this.context=context;
        this.imageIDarray = imageIDArrayParam;
        this.nameArray = nameArrayParam;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.row_category_list, null,true);

        //this code gets references to objects in the row_category_list.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.textViewName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageViewIcon);

        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(nameArray[position]);
        imageView.setImageResource(imageIDarray[position]);

        return rowView;

    };
}
