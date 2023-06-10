package com.example.tutrong6.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tutrong6.R;
import com.squareup.picasso.Picasso;

import java.net.CookieHandler;
import java.util.Collections;

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




    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_signup);

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
        TextView warningSignEmail = findViewById(R.id.warning_sign_email_tutor);

        tutorSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tFN = tutorFirstName.getText().toString();
                String tLN = tutorLastName.getText().toString();
                String tEM = tutorEmail.getText().toString();
                String tPS = tutorPassword.getText().toString();
                String tDS = tutorDescription.getText().toString();
                String tNL = tutorNativeLanguages.getText().toString();
                String tEL = tutorEducationLevels.getText().toString();

                if (tFN.isEmpty() || tLN.isEmpty() || tEM.isEmpty() || tPS.isEmpty() || tDS.isEmpty() || tNL.isEmpty() || tEL.isEmpty()) {
                    warningSign.setVisibility(View.VISIBLE);
                }else if (tDS.length() > 600){
                    warningSignDesc.setVisibility(View.VISIBLE);
                }else if (!tEM.contains("@")){
                    warningSignEmail.setVisibility(View.VISIBLE);
                }   else {
                    Intent intent = new Intent(TutorSignUpActivity.this, WelcomePage.class);
                    startActivity(intent);
                }
            }
        });
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();


            ImageView profilePictureTutor = findViewById(R.id.profile_pic_tutor);
            Picasso.get().load(imageUri).into(profilePictureTutor);

        }
    }

}
