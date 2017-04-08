package com.example.maniac.codestrikeslack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void post(View v){
        EditText message = (EditText)findViewById(R.id.editText);

        final String msg = message.getText().toString();

        String url = "https://hooks.slack.com/services/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";
        //Volley decalaration:
        // Instantiate the RequestQueue.

        RequestQueue queue = Volley.newRequestQueue(this);

        //Create JSON Object
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("text", msg);
        }catch (Exception e){
            e.printStackTrace();
        }

        //Create request
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );

        //Add to queue:
        queue.add(jsonRequest);

        //clearing the message in the textview:
        message.setText(" ");

    }
}
