
package com.example.tutrong6.GUI;

import android.app.Activity;
import android.os.Bundle;
import com.example.tutrong6.R;

import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

public class StudentSignUpPayment extends Activity {

	
	private View _bg__android_small___1_ek2;
	private TextView payment_method;
	private View rectangle_15;
	private View rectangle_12;
	private View rectangle_13;
	private TextView card_holder_name;
	private TextView credit_debit_card_numbers;
	private TextView cvs_number;
	private View rectangle_14;
	private TextView expiration_number;
	private TextView set_up_your_details;
	private TextView we_assure_you_that_your_personal_and_credit_card_information_will_not_be_used_without_your_explicit_consent_;
	private ImageView backbutton;
	private View rectangle_11;
	private TextView submit;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_sign_up_payement);

		
		_bg__android_small___1_ek2 = (View) findViewById(R.id._bg__android_small___1_ek2);
		payment_method = (TextView) findViewById(R.id.payment_method);
		rectangle_15 = (View) findViewById(R.id.rectangle_15);
		rectangle_12 = (View) findViewById(R.id.rectangle_12);
		rectangle_13 = (View) findViewById(R.id.rectangle_13);
		card_holder_name = (TextView) findViewById(R.id.card_holder_name);
		credit_debit_card_numbers = (TextView) findViewById(R.id.credit_debit_card_numbers);
		cvs_number = (TextView) findViewById(R.id.cvs_number);
		rectangle_14 = (View) findViewById(R.id.rectangle_14);
		expiration_number = (TextView) findViewById(R.id.expiration_number);
		set_up_your_details = (TextView) findViewById(R.id.set_up_your_details);
		we_assure_you_that_your_personal_and_credit_card_information_will_not_be_used_without_your_explicit_consent_ = (TextView) findViewById(R.id.we_assure_you_that_your_personal_and_credit_card_information_will_not_be_used_without_your_explicit_consent_);
		backbutton = (ImageView) findViewById(R.id.backbutton);
		rectangle_11 = (View) findViewById(R.id.rectangle_11);
		submit = (TextView) findViewById(R.id.submit);
	
		
		//custom code goes here
	
	}
}
	
	