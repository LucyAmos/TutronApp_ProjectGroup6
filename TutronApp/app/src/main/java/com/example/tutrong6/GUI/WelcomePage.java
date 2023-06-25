
	 

package com.example.tutrong6.GUI;
import com.example.tutrong6.BEANS.*;
import com.example.tutrong6.DAO.*;
import com.example.tutrong6.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomePage extends Activity {


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_page);

		DBHelper DataBase = new DBHelper(this);

		TextView userName = findViewById(R.id.user_name);
		TextView user_role = findViewById(R.id.user_role);
		Button logout_btn = findViewById(R.id.Logout_btn);
		Button complaintsInboxBtn = findViewById(R.id.complaints_inbox_btn);


		//get the ID of the logged user
		SessionManagement sessionManagement = new SessionManagement(WelcomePage.this);
		int userID = sessionManagement.getSession();
		Log.i("USER ID LOGGED", "ICI "+ userID);
		User session_user= DataBase.getUserbyID(userID);
		Log.i("USER_session(WELPAGE)", "ici "+ session_user);

		//display the name and role of the logged user
		userName.setText(session_user.getFirst_name().toString()+ " "+ session_user.getLast_name().toString());
		user_role.setText(DataBase.getRoleNameByRoleID(session_user.getRoleID()));

		if (user_role.getText().equals("ADMINISTRATOR")){
			complaintsInboxBtn.setVisibility(View.VISIBLE);
		}

	/*	String role = DataBase.getRoleNameByRoleID(session_user.getRoleID()) ;
		switch (session_user.getRoleID())
		{
			case 1:
				Administrator admin = (Administrator) session_user;
				userName.setText(admin.getFirst_name()+ " " + admin.getLast_name());
				user_role.setText(admin.getRole());
				break;
			case 2:
				Student student = (Student) session_user;
				userName.setText(student.getFirst_name() + " " + student.getLast_name() +" "+ student.getAddress().getStreet_address() );
				user_role.setText(student.getRole());
				complaintsInboxBtn.setVisible("false");
				break;
			case 3:
				Tutor tutor = (Tutor) session_user;
				userName.setText(tutor.getFirst_name() + " " + tutor.getLast_name() +" "+ tutor.getDescription());
				user_role.setText(tutor.getRole());
				complaintsInboxBtn.setVisible("false");
				break;
		}*/

		complaintsInboxBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Handle button click
				Intent intent = new Intent(com.example.tutrong6.GUI.WelcomePage.this, AdminInboxActivity.class);
				startActivity(intent);
			}


		});

		logout_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				//logout
				sessionManagement.removeSession();
				//aller a la page d accueil.
				startActivity(new Intent(WelcomePage.this, LandingPageActivity.class));

			}
		});
	
	}


}
	
	