package com.example.polytech2;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class DisplayCoursTwitterMenu extends Activity {

	//field to update with retrieved tweets
	private TextView tweetDisplay;
	private TextView tvCours;
	private TextView tvWebsite;
	private String nomCours;
	private String website;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cours_twitter);
		tweetDisplay = (TextView)findViewById(R.id.tweets);
		tvCours = (TextView)findViewById(R.id.tvCours);
		tvWebsite = (TextView)findViewById(R.id.tvWeb);
		Intent intent = getIntent();
		nomCours = intent.getStringExtra("title");
		//String mnemomique = intent.getStringExtra("mnemomique");
		tvCours.setText(nomCours);
		databaseCheckForWebsite();
		tvWebsite.setText(website);
		//searchTwitter(tweetDisplay);//added line so searchTwitter method is called from the opening of the activity directly
		TwitterSearch ts=new TwitterSearch(tweetDisplay,"#"+intent.getStringExtra("mnemomique"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_news_menu, menu);
		return true;
	}
	private void databaseCheckForWebsite() {
		
        DataBaseHelper myDbHelper = new DataBaseHelper(this);
 
        try {
        	myDbHelper.createDataBase();
        } catch (IOException ioe) {
        	throw new Error("Unable to create database");
        }
 
        try {
        	myDbHelper.openDataBase();
        	website=myDbHelper.getWebsiteFromCours(nomCours);
        	
        }catch(SQLException sqle){
        	throw sqle;
        }
		
	}
}