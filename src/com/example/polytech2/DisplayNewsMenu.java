package com.example.polytech2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class DisplayNewsMenu extends Activity {

	//field to update with retrieved tweets
	private TextView tweetDisplay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_news_menu);
		tweetDisplay = (TextView)findViewById(R.id.tweets);
		//searchTwitter(tweetDisplay);//added line so searchTwitter method is called from the opening of the activity directly
		TwitterSearch ts=new TwitterSearch(tweetDisplay,"ulb");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_news_menu, menu);
		return true;
	}
	


}
