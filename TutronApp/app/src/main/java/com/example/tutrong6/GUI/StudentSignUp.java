
package com.example.tutrong6.GUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.example.tutrong6.R;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;

public class StudentSignUp extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_sign_up);

		EditText studentFirstName = findViewById(R.id.first_name_student_input);
		EditText studentLastName = findViewById(R.id.last_name_student_input);
		EditText studentEmail = findViewById(R.id.email_student_input);
		EditText studentPassword = findViewById(R.id.password_student_input);
		EditText studentAddress = findViewById(R.id.address_student_input);
		EditText studentCity = findViewById(R.id.city_student_input);
		EditText studentRegion = findViewById(R.id.Region_student_input);
		EditText studentCountry = findViewById(R.id.Country_student_input);
		EditText studentPostal = findViewById(R.id.postal_student_input);
		Button studentContinue = findViewById(R.id.continue_student_button);
		TextView warningSign = findViewById(R.id.warning_sign_student);
		TextView warningSignEmail = findViewById(R.id.warning_sign_email_student);

		studentContinue.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String sFN = studentFirstName.getText().toString();
				String sLN = studentLastName.getText().toString();
				String sEM = studentEmail.getText().toString();
				String sPS = studentPassword.getText().toString();
				String sAD = studentAddress.getText().toString();
				String sSC = studentCity.getText().toString();
				String sSR = studentRegion.getText().toString();
				String sSY = studentCountry.getText().toString();
				String sPT = studentPostal.getText().toString();


				if (sFN.isEmpty()||sLN.isEmpty()||sEM.isEmpty()||sPS.isEmpty()||sAD.isEmpty()||sSC.isEmpty()||sSR.isEmpty()||sSY.isEmpty()||sPT.isEmpty()) {
					warningSign.setVisibility(View.VISIBLE);
				}else if (!sEM.contains("@")){
					warningSignEmail.setVisibility(View.VISIBLE);
				}   else {
					Intent intent = new Intent(StudentSignUp.this, StudentSignUpPayment.class);
					startActivity(intent);
				}
			}
		});
	}
	
	}

	
	
