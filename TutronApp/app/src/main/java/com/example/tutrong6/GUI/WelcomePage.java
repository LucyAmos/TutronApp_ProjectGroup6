
	 

package com.example.tutrong6.GUI;
import com.example.tutrong6.R;
import android.app.Activity;
import android.os.Bundle;


import android.view.View;
import android.widget.TextView;

public class WelcomePage extends Activity {


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_page);

		
		TextView userName = findViewById(R.id.user_name);
		TextView accountStatus = findViewById(R.id.account_status);

	
	}
}
	
	