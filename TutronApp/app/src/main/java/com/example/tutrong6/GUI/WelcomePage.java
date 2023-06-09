
	 

package com.example.tutrong6.GUI;
import com.example.tutrong6.R;
import android.app.Activity;
import android.os.Bundle;


import android.view.View;
import android.widget.TextView;

public class WelcomePage extends Activity {

	
	private View _bg__android_small___2_ek2;
	private TextView welcome_;
	private TextView you_are_logged_in_as_a;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_page);

		
		_bg__android_small___2_ek2 = (View) findViewById(R.id._bg__android_small___2_ek2);
		welcome_ = (TextView) findViewById(R.id.welcome_);
		you_are_logged_in_as_a = (TextView) findViewById(R.id.you_are_logged_in_as_a);
	
		
		//custom code goes here
	
	}
}
	
	