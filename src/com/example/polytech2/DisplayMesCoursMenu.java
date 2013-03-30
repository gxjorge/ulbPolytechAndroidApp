package com.example.polytech2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class DisplayMesCoursMenu extends Activity {

	 private ListView mainListView ;  
	 private ArrayAdapter<String> listAdapter ;  
	 String[] list;
	//SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cours_list);

		databaseCheck();
		
		//---------------------------------http://windrealm.org/tutorials/android/android-listview.php
		mainListView = (ListView) findViewById( R.id.mainListView); 
		ArrayList<String> coursList = new ArrayList<String>();  
		coursList.addAll( Arrays.asList(list) ); 
		listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, coursList);  
		mainListView.setAdapter( listAdapter );

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
        	list=myDbHelper.getInformation();
        	
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
	
}
