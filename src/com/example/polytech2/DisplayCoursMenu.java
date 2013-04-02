package com.example.polytech2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DisplayCoursMenu extends Activity implements OnItemClickListener {

	//------------------WEBSITE FOR CUSTOM LISTVIEWS:
	//http://theopentutorials.com/tutorials/android/listview/android-custom-listview-with-image-and-text-using-arrayadapter/
	 private ListView mainListView ;  
	 CustomListViewAdapter adapter;
	 String[] listCours;
	 String[] listMnemomiques;
	 boolean[] listCheckboxes;
	 List<RowItem> rowItems;
	 DataBaseHelper myDbHelper;
	//SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cours_list);

		databaseCheck();
		
		displayListView();
		checkButtonClick();

	}

	private void displayListView() {
		
		rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < listCours.length; i++) {
            RowItem item = new RowItem( listCours[i], listMnemomiques[i],listCheckboxes[i]);// Pour le troisiemme il faudrer regarder si on peut prendre la list de checked
            rowItems.add(item);
        }
 
        mainListView = (ListView) findViewById(R.id.mainListView);
        adapter = new CustomListViewAdapter(this,
                R.layout.list_item, rowItems);
        mainListView.setAdapter(adapter);
        mainListView.setOnItemClickListener(this);
        
        
        
        //---------------
        /*mainListView.setOnItemClickListener(new OnItemClickListener() {
        	   public void onItemClick(AdapterView<?> parent, View view,
        	     int position, long id) {
        	    // When clicked, show a toast with the TextView text
        	    RowItem cours = (RowItem) parent.getItemAtPosition(position);
        	    Toast.makeText(getApplicationContext(),
        	      "Clicked on Row: " + cours.getTitle(), 
        	      Toast.LENGTH_LONG).show();
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
	
	private void checkButtonClick() {
		Button myButton = (Button) findViewById(R.id.findSelected);
		myButton.setOnClickListener(new Button.OnClickListener() {
		 
				   public void onClick(View v) {
		 
				StringBuffer responseText = new StringBuffer();
				responseText.append("The following were selected...\n");
		 
				
				for(int i=0;i<rowItems.size();i++){
					RowItem cours = rowItems.get(i);
					listCheckboxes[i]=cours.isSelected();
					if(cours.isSelected()){
							responseText.append("\n" + cours.getTitle());
							
					}
				}
				myDbHelper.setElementsChecked(listCheckboxes);
		 
				Toast.makeText(getApplicationContext(),
		        responseText, Toast.LENGTH_LONG).show();
				//finishActivity(0);
				finish();

		   }
		  });
	}
	
}
//---------------------EXAMPLE CHECKBOX :
//----------------------------------http://www.mysamplecode.com/2012/07/android-listview-checkbox-example.html
