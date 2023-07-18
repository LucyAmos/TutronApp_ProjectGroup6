package com.example.tutrong6.GUI.TUTOR_interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutrong6.BEANS.*;
import com.example.tutrong6.DAO.*;
import com.example.tutrong6.GUI.LoginPageActivity;
import com.example.tutrong6.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class TutorSignUpActivity extends AppCompatActivity {

    String[] nativeLanguagesArray = {
            "Abkhaz", "Adyghe", "Afar", "Afrikaans", "Ainu", "Akan", "Albanian", "Alsatian", "Amharic", "Arabic", "Aragonese", "Armenian", "Aromanian", "Assamese",
            "Asturian", "Avar", "Aymara", "Azerbaijani", "Bashkir", "Basque", "Belarusian", "Bengali", "Bislama", "Bosnian", "Breton", "Bulgarian", "Burmese",
            "Buryat", "Catalan", "Cebuano", "Chamorro", "Chechen", "Cherokee", "Chewa", "Cheyenne", "Chichewa", "Chinese", "Chuvash", "Cornish", "Corsican",
            "Cree", "Croatian", "Czech", "Danish", "Dhivehi", "Dutch", "Dzongkha", "English", "Esperanto", "Estonian", "Ewe", "Faroese", "Fijian", "Finnish",
            "French", "Fula", "Galician", "Georgian", "German", "Greek", "Greenlandic", "Guarani", "Gujarati", "Haitian Creole", "Hausa", "Hawaiian", "Hebrew",
            "Herero", "Hindi", "Hiri Motu", "Hungarian", "Icelandic", "Ido", "Igbo", "Iloko", "Indonesian", "Interlingua", "Interlingue", "Inuktitut", "Inupiaq",
            "Irish", "Italian", "Japanese", "Javanese", "Kabardian", "Kabiye", "Kachin", "Kalaallisut", "Kannada", "Kanuri", "Kapampangan", "Karen", "Kashmiri",
            "Kashubian", "Kazakh", "Khmer", "Kikuyu", "Kinyarwanda", "Kirundi", "Komi", "Kongo", "Korean", "Kurdish", "Kwangali", "Kyrgyz", "Lao", "Latin",
            "Latvian", "Limburgish", "Lingala", "Lithuanian", "Lojban", "Lombard", "Low German", "Lower Sorbian", "Luganda", "Luxembourgish", "Macedonian",
            "Malagasy", "Malay", "Malayalam", "Maldivian", "Maltese", "Manchu", "Manx", "Maori", "Marathi", "Marshallese", "Moldovan", "Mongolian",
            "Montenegrin", "Nahuatl", "Nauruan", "Navajo", "Ndonga", "Neapolitan", "Nenets", "Nepali", "Nias", "Niuean", "Northern Sami", "Norwegian",
            "Norwegian Bokmål", "Norwegian Nynorsk", "Novial", "Occitan", "Ojibwe", "Old Church Slavonic", "Oriya", "Oromo", "Ossetian", "Pali", "Pangasinan",
            "Papiamento", "Pashto", "Pennsylvania German", "Persian", "Polish", "Portuguese", "Punjabi", "Quechua", "Rapanui", "Romanian", "Romansh", "Russian",
            "Rusyn", "Sardinian", "Scots", "Scottish Gaelic", "Serbian", "Serbo-Croatian", "Sesotho", "Shona", "Sicilian", "Sindhi", "Sinhalese", "Slovak",
            "Slovenian", "Somali", "Sorbian", "Sotho", "South Azerbaijani", "Spanish", "Sranan Tongo", "Sundanese", "Swahili", "Swazi", "Swedish", "Tagalog",
            "Tahitian", "Tajik", "Tamil", "Tatar", "Telugu", "Tetum", "Thai", "Tibetan", "Tigrinya", "Tok Pisin", "Tokelauan", "Tongan", "Tswana", "Turkish",
            "Turkmen", "Tuvaluan", "Udmurt", "Uighur", "Ukrainian", "Urdu", "Uzbek", "Venda", "Venetian", "Vietnamese", "Volapük", "Walloon", "Waray", "Welsh",
            "Wolof", "Xhosa", "Yiddish", "Yoruba", "Zhuang", "Zulu"
    };

    String[] educationLevelsArray = {
            "Preschool",
            "Primary School",
            "Secondary School",
            "High School",
            "Vocational School",
            "Diploma",
            "Associate's Degree",
            "Bachelor's Degree",
            "Master's Degree",
            "Doctoral Degree"
    };


    ImageView profilePictureTutor;

    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_signup);

        DBHelper DataBase = new DBHelper(this);

        ImageView profilePictureTutor = findViewById(R.id.profile_pic_tutor);
        profilePictureTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to open the gallery or camera roll
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        AutoCompleteTextView tutorNativeLanguages = findViewById(R.id.native_language_tutor_input);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.dropdown, nativeLanguagesArray);
        tutorNativeLanguages.setAdapter(adapter);

        AutoCompleteTextView tutorEducationLevels = findViewById(R.id.education_level_tutor_input);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                R.layout.dropdown, educationLevelsArray);
        tutorEducationLevels.setAdapter(adapter2);

        EditText tutorFirstName = findViewById(R.id.first_name_tutor_input);
        EditText tutorLastName = findViewById(R.id.last_name_tutor_input);
        EditText tutorEmail = findViewById(R.id.email_tutor_input);
        EditText tutorPassword = findViewById(R.id.password_tutor_input);
        EditText tutorDescription = findViewById(R.id.description_tutor_input);
        Button tutorSubmit = findViewById(R.id.submit_tutor_button);
        TextView warningSign = findViewById(R.id.warning_sign_tutor);
        TextView warningSignDesc = findViewById(R.id.warning_sign_desc_tutor);


        profilePictureTutor = findViewById(R.id.profile_pic_tutor);

        ImageView finalProfilePictureTutor = profilePictureTutor;
        tutorSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String first_name = tutorFirstName.getText().toString().trim();
                String last_name = tutorLastName.getText().toString().trim();
                String email = tutorEmail.getText().toString().trim();
                String password = tutorPassword.getText().toString().trim();

                String description = tutorDescription.getText().toString().trim();
                String native_language = tutorNativeLanguages.getText().toString().trim();
                String education_level = tutorEducationLevels.getText().toString().trim();
                byte[] profile_picture = convertToByte(finalProfilePictureTutor);


                boolean validLanguage = false;
                for (String language : nativeLanguagesArray) {
                    if (language.equals(native_language)) {
                        validLanguage = true;
                        break;
                    }
                }

                boolean validEducationLevel = false;
                for (String educationLevel : educationLevelsArray) {
                    if (educationLevel.equals(education_level)) {
                        validEducationLevel = true;
                        break;
                    }
                }


                if(first_name.isEmpty() || last_name.isEmpty() || email.isEmpty() || password.isEmpty() ||
                        education_level.isEmpty() || native_language.isEmpty() || description.isEmpty()
                )
                {
                    Toast.makeText(TutorSignUpActivity.this, "Please, fill in ALL the fields", Toast.LENGTH_SHORT).show();
                } else
                {
                    if(!User.isValidEmailAddressFormat(email))
                    {
                        Toast.makeText(TutorSignUpActivity.this, "Invalid Email Format", Toast.LENGTH_SHORT).show();
                    }
                    else if (!validLanguage) {
                        Toast.makeText(TutorSignUpActivity.this, "Invalid Language", Toast.LENGTH_SHORT).show();
                        tutorNativeLanguages.setText("");
                    }

                    else if (!validEducationLevel) {
                        Toast.makeText(TutorSignUpActivity.this, "Invalid Education Level", Toast.LENGTH_SHORT).show();
                        tutorEducationLevels.setText("");
                    }

                    else
                    {
                        int checkUser = DataBase.checkEmail(email)==false?0:1;

                        switch(checkUser)
                        {
                            case 0:

                                Tutor tutor = new Tutor(first_name,last_name,email,password,education_level,native_language,description,profile_picture);
                                Log.i("  PROFILE PICTURE", " HUMM" + profile_picture);

                                Boolean insert = DataBase.addTutor(tutor);
                                if(insert==true){
                                    /*boolean insert_avaibilities = DataBase.CreatedDefaultAvaibilities();
                                    if(insert_avaibilities)
                                    {
                                        Toast.makeText(TutorSignUpActivity.this, "avaibilities sucessfully added", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(TutorSignUpActivity.this, "avaibilities FAiled to be added", Toast.LENGTH_SHORT).show();

                                    }*/
                                    Toast.makeText(TutorSignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(TutorSignUpActivity.this, LoginPageActivity.class));

                                }else{
                                    Toast.makeText(TutorSignUpActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case 1:
                                Toast.makeText(TutorSignUpActivity.this, "You ALREADY HAVE an Account, please LOG IN", Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }

                }

                /*Intent intent = new Intent(TutorSignUpActivity.this, LoginPageActivity.class);
                startActivity(intent);*/
            }
        });
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();


             profilePictureTutor = findViewById(R.id.profile_pic_tutor);
            Picasso.get().load(imageUri).into(profilePictureTutor);

        }
    }

    public static byte[] convertToByte(ImageView imageView) {
        try {
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] bytesData = stream.toByteArray();
            stream.close();
            return bytesData;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
