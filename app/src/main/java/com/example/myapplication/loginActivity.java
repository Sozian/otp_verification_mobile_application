package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get the mobile number from the intent
        String mobileNumber = getIntent().getStringExtra("mobile_number");

        // Set the mobile number in a TextView (optional)
        TextView textView = findViewById(R.id.mobile_number_text_view);
        textView.setText(mobileNumber);
    }
}
