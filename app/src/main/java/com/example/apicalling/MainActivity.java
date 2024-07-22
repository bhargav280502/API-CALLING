package com.example.apicalling;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    TextView textView3, textView4,textView5;
    Button button;
    Button button2;
    private String url = "https://official-joke-api.appspot.com/random_joke";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button= findViewById(R.id.button);
        button2= findViewById(R.id.button2);
        textView3= findViewById(R.id.textView3);
        textView4= findViewById(R.id.textView4);
        textView5= findViewById(R.id.textView5);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                textView5.setVisibility(View.GONE);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button2.setVisibility(View.GONE);
                textView4.setVisibility(View.VISIBLE);
            }
        });
    }

    private void getData() {
        mRequestQueue = Volley.newRequestQueue(this);

        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "Response: " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String setup = jsonObject.getString("setup");
                    String punchline = jsonObject.getString("punchline");

                    textView3.setVisibility(View.VISIBLE);
                    textView4.setVisibility(View.GONE);

                    textView3.setText("Question : "+setup);
                    textView4.setText("Answer : "+punchline);

                    button2.setVisibility(View.VISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "JSON Parsing Error", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.toString());
                Toast.makeText(MainActivity.this, "Error occurred!", Toast.LENGTH_SHORT).show();
            }
        });

        mRequestQueue.add(mStringRequest);
    }
}
