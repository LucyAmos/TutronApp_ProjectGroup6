
	 

package com.example.tutrong6.GUI;
import com.example.tutrong6.BEANS.*;
import com.example.tutrong6.DAO.*;
import com.example.tutrong6.GUI.ADMIN_interfaces.AdminInboxActivity;
import com.example.tutrong6.GUI.TUTOR_interfaces.TutorHubActivity;
import com.example.tutrong6.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class WelcomePage extends Activity {
	String tutorToastStart = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_page);

		DBHelper DataBase = new DBHelper(this);

		TextView userName = findViewById(R.id.user_name);
		TextView user_role = findViewById(R.id.user_role);
		Button logout_btn = findViewById(R.id.Logout_btn);
		Button start_btn = findViewById(R.id.start_btn);
		TextView welcome_TV = findViewById(R.id.welcomeTextView);
		TextView login_status_text = findViewById(R.id.login_status_text);


		//get the ID of the logged user
		SessionManagement sessionManagement = new SessionManagement(WelcomePage.this);
		int userID = sessionManagement.getSession();
		Log.i("USER ID LOGGED", "ICI "+ userID);
		User session_user= DataBase.getUserbyID(userID);
		Log.i("USER_session(WELPAGE)", "ici "+ session_user);

		//display the name and role of the logged user
		userName.setText(session_user.getFirst_name().toString()+ " "+ session_user.getLast_name().toString());

		String role = DataBase.getRoleNameByRoleID(session_user.getRoleID());
		//user_role.setText();


		if (session_user.getRoleID() == Tutor.getStaticRoleID())
		{
			Map<String, Date> suspension_state = DataBase.infoEtatSsuspension(userID);
			String suspensionType = "";
			Date suspension_end_date = null;
			// dateStr = new SimpleDateFormat(Plainte.getDATE_FORMAT()).format(etat_de_suspension.get(i));
			for (String i : suspension_state.keySet()) {
				suspensionType = i;
				suspension_end_date = suspension_state.get(i);
				Log.e("MAP", "key: " + i + " value: " + suspension_state.get(i));
				// System.out.println("key: " + i + " value: " + etat_de_suspension.get(i));
			}
			Timestamp now_timestap = new Timestamp(System.currentTimeMillis());
			int compareDate = suspension_end_date== null?-404:now_timestap.compareTo(suspension_end_date);
			Log.e("COMPARE_DATE", ""+compareDate );
			if(session_user.getIs_suspended() && compareDate <= 0)
			{
				//start_btn.setEnabled(false);
				welcome_TV.setText("SORRY");
				String dateStr = suspension_end_date ==null?"": new SimpleDateFormat(Complaint.getDATE_FORMAT()).format(suspension_end_date);
				String msg_susp= "You got a "+ suspensionType;
				tutorToastStart = suspensionType.equalsIgnoreCase("TEMPORARILY SUSPENDED")? "functionality available at the end of your suspension":"you can no longer use the application";
				String return_date = suspensionType.equalsIgnoreCase("TEMPORARILY SUSPENDED")? " until "+dateStr:"";
				login_status_text.setText(msg_susp);
				user_role.setText(return_date);
			}
			else
			{
				user_role.setText(role);
			}

		}
		else {
			user_role.setText(role);

		}


		start_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Handle button click

				if(session_user.getRoleID() == Administrator.getStaticRoleID()){


						Intent intent = new Intent(com.example.tutrong6.GUI.WelcomePage.this, AdminInboxActivity.class);
						startActivity(intent);


				}else if(session_user.getRoleID() == Tutor.getStaticRoleID()){
						if(session_user.getIs_suspended())
						{
							Toast.makeText(WelcomePage.this, tutorToastStart, Toast.LENGTH_SHORT).show();
						}
						else
						{
							Intent intent = new Intent(com.example.tutrong6.GUI.WelcomePage.this, TutorHubActivity.class);
							startActivity(intent);
						}

				}else{
					Toast.makeText(WelcomePage.this, "Not implement yet for Students and Tutors", Toast.LENGTH_SHORT).show();
				}

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
	
	