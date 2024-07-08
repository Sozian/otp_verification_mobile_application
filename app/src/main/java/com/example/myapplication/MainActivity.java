package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    private EditText a1_mobile_num;
    private Switch s1_switch;
    private Button b1Click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a1_mobile_num = findViewById(R.id.a1_enter_no);
        s1_switch = findViewById(R.id.switch1);
        b1Click = findViewById(R.id.b1_click_here);

        b1Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileNumber = a1_mobile_num.getText().toString().trim();
                if (!mobileNumber.isEmpty()) {
                    requestOtp(mobileNumber);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void requestOtp(String mobileNumber) {
        String url = "http://devapiv4.dealsdray.com/api/v2/user/device/add" + mobileNumber;

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle OTP request success
                        Intent intent = new Intent(MainActivity.this, loginActivity.class);
                        intent.putExtra("mobile_number", mobileNumber);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle OTP request failure
                Toast.makeText(MainActivity.this, "Failed to request OTP", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
    }
}
