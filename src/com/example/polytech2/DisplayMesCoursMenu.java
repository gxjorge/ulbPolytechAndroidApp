package com.example.polytech2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressWarnings("unused")
public class DisplayMesCoursMenu extends Activity implements OnItemClickListener {

	//------------------WEBSITE FOR CUSTOM LISTVIEWS:
	//http://theopentutorials.com/tutorials/android/listview/android-custom-listview-with-image-and-text-using-arrayadapter/
	 private ListView mainListView1 ;  
	 CustomSelectedListViewAdapter adapter;
	 String[] listCours;
	 String[] listMnemomiques;
	 boolean[] listCheckboxes;
	 List<RowItemSelected> rowItems;
	 DataBaseHelper myDbHelper;
	//SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_mes_cours_menu);
		
	}
	public void onResume()
	{
		databaseCheck();
		displayListView();
		checkButtonClick();
	    super.onResume();
	}

	
	

	private void checkButtonClick() {
		Button settings=(Button) findViewById(R.id.bCours);
        settings.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent a;
				a=new Intent(DisplayMesCoursMenu.this, DisplayCoursMenu.class);
				startActivity(a);
				
			}
        	
        });
	}

	private void displayListView() {
		
		rowItems = new ArrayList<RowItemSelected>();
        for (int i = 0; i < listCours.length; i++) {
        	if(listCheckboxes[i]){
        		RowItemSelected item = new RowItemSelected( listCours[i], listMnemomiques[i]);// Pour le troisiemme il faudrer regarder si on peut prendre la list de checked
                rowItems.add(item);
        	}
            
        }
 
        mainListView1 = (ListView) findViewById(R.id.mainListView1);
        adapter = new CustomSelectedListViewAdapter(this, R.layout.selected_list_item, rowItems);
        mainListView1.setAdapter(adapter);
        mainListView1.setOnItemClickListener(this);
        
        
        
        //---------------
        mainListView1.setOnItemClickListener(new OnItemClickListener() {
        	   public void onItemClick(AdapterView<?> parent, View view,
        	     int position, long id) {
	        	    // When clicked, show a toast with the TextView text
	        	    RowItemSelected cours = (RowItemSelected) parent.getItemAtPosition(position);
	        	    Toast.makeText(getApplicationContext(),
	        	      "Clicked on Row: " + cours.getTitle(), 
	        	      Toast.LENGTH_LONG).show();
	        	    Intent i=new Intent(DisplayMesCoursMenu.this,DisplayCoursTwitterMenu.class);
	        	    i.putExtra("title", cours.getTitle());
	        	    i.putExtra("mnemomique", cours.getDesc());
	        	    startActivity(i);
        	   }
        	  });//*/
        
	}

	private void databaseCheck() {
		
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
        	listCheckboxes=myDbHelper.getCheckBoxStatus();
        	
        	
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
//---------------------EXAMPLE CHECKBOX :
//----------------------------------http://www.mysamplecode.com/2012/07/android-listview-checkbox-example.html
