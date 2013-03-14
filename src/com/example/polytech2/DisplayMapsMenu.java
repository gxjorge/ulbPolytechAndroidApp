package com.example.polytech2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class DisplayMapsMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_maps_menu);
		
		TabHost th = (TabHost) findViewById(R.id.tabhost);
		th.setup();
		TabSpec specs = th.newTabSpec("tag1");
		specs.setContent(R.id.Solbosch);
		specs.setIndicator("Solbosch");
		th.addTab(specs);
		specs = th.newTabSpec("tag2");
		specs.setContent(R.id.Plaine);
		specs.setIndicator("Plaine");
		th.addTab(specs);
		
		WebView wvSolbosch = new WebView(this);
		wvSolbosch=(WebView)findViewById(R.id.webViewPlanSolbosch);
		//wvSolbosch.setWebViewClient(new WebViewClient());
		wvSolbosch.getSettings().setJavaScriptEnabled(true);
		wvSolbosch.setWebChromeClient(new WebChromeClient());
		wvSolbosch.loadUrl("file:///android_asset/www/solbosch.html");
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_maps_menu, menu);
		return true;
	}

}
