
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

import java.util.Calendar;

public class StudentSignUpPayment extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_sign_up_payement);

		EditText studentCardHolderName = findViewById(R.id.cardholder_name_input);
		EditText studentCardNumber = findViewById(R.id.card_number_input);
		EditText studentCVV = findViewById(R.id.CVV_number_input);
		EditText studentExDate = findViewById(R.id.card_exdate_input);
		Button studentSubmit = findViewById(R.id.submit_student_button);
		TextView warningSign = findViewById(R.id.warning_sign_student);
		TextView warningSignExDate = findViewById(R.id.warning_sign_exdate);

		studentSubmit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String sCHN = studentCardHolderName.getText().toString();
				String sCN = studentCardNumber.getText().toString();
				String sCVV = studentCVV.getText().toString();
				String sED = studentExDate.getText().toString();

				Calendar calendar = Calendar.getInstance();
				int currentMonth = calendar.get(Calendar.MONTH) + 1;
				int currentYear = calendar.get(Calendar.YEAR) % 100;

				int cardMonth = Integer.parseInt(sED.substring(0, 2));
				int cardYear = Integer.parseInt(sED.substring(2));

				/*if (sCHN.isEmpty()||sCN.isEmpty()||sCVV.isEmpty()||sED.isEmpty()) {
					warningSign.setVisibility(View.VISIBLE);
				}else if (cardYear < currentYear || (cardYear == currentYear && cardMonth <= currentMonth)){
					warningSignExDate.setVisibility(View.VISIBLE);
				}else {
					Intent intent = new Intent(StudentSignUpPayment.this, WelcomePage.class);
					startActivity(intent);
				}*/
			}
		});
	}

}
	


	
	