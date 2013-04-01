package com.example.polytech2;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
 
public class CustomListViewAdapter extends ArrayAdapter<RowItem> {
 
    Context context;
 
    public CustomListViewAdapter(Context context, int resourceId,
            List<RowItem> items) {
        super(context, resourceId, items);
        this.context = context;
    }
 
    /*private view holder class*/
    private class ViewHolder {
       
        TextView txtTitle;
        TextView txtDesc;
        CheckBox name;//-------------------
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        RowItem rowItem = getItem(position);
 
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.txtDesc = (TextView) convertView.findViewById(R.id.desc);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);//-----------------------
            
            convertView.setTag(holder);
            //--------------------------------------------------------------------------------
            holder.name.setOnClickListener( new View.OnClickListener() {  
                public void onClick(View v) {  
                 CheckBox cb = (CheckBox) v ;  
                 RowItem cours = (RowItem) cb.getTag();  
                 /*Toast.makeText(context, //ici j'ai modifié cette ligne parceque il y avait une erreur par rapport au code du site web
                  "Clicked on Checkbox: " + cb.getText() +
                  " is " + cb.isChecked(), 
                  Toast.LENGTH_LONG).show();//*/
                 cours.setSelected(cb.isChecked());
                }  
               });  
            //---------------------------------------------------------------------------------
        } else
            holder = (ViewHolder) convertView.getTag();
 
        holder.txtDesc.setText(rowItem.getDesc());
        holder.txtTitle.setText(rowItem.getTitle());
        holder.name.setChecked(rowItem.isSelected());
        holder.name.setTag(rowItem);
 
        return convertView;
    }
}