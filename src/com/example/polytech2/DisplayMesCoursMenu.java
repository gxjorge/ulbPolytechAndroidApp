package com.example.polytech2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class DisplayMesCoursMenu extends Activity implements OnItemClickListener {

	//------------------WEBSITE FOR CUSTOM LISTVIEWS:
	//http://theopentutorials.com/tutorials/android/listview/android-custom-listview-with-image-and-text-using-arrayadapter/
	 private ListView mainListView ;  
	 private ArrayAdapter<String> listAdapter ;  
	 String[] listCours;
	 
	 String[] listMnemomiques;
	 //ListView listView;
	 List<RowItem> rowItems;
	//SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cours_list);

		databaseCheck();
		
		//---------------------------------http://windrealm.org/tutorials/android/android-listview.php
		/*mainListView = (ListView) findViewById( R.id.mainListView); 
		ArrayList<String> coursList = new ArrayList<String>();  
		coursList.addAll( Arrays.asList(listCours) ); 
		listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, coursList);  
		mainListView.setAdapter( listAdapter );//*/
		String[] titles=listCours;
		String[] descriptions= listMnemomiques;
		
		rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < titles.length; i++) {
            RowItem item = new RowItem( titles[i], descriptions[i]);
            rowItems.add(item);
        }
 
        mainListView = (ListView) findViewById(R.id.mainListView);
        CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                R.layout.list_item, rowItems);
        mainListView.setAdapter(adapter);
        mainListView.setOnItemClickListener(this);

	}

	private void databaseCheck() {
		DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(this);
 
        try {
        	myDbHelper.createDataBase();
        } catch (IOException ioe) {
        	throw new Error("Unable to create database");
        }
 
        try {
        	myDbHelper.openDataBase();
        	listCours=myDbHelper.getCours();
        	listMnemomiques=myDbHelper.getMnemomique();
        	
        }catch(SQLException sqle){
        	throw sqle;
        }
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_mes_cours_menu, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	
}
