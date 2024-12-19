package com.hospital.visitorapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewAllActivity extends AppCompatActivity {
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_all);
        t1=(TextView) findViewById(R.id.text1);

        callApi();

    }

    private void callApi() {
        String apiUrl="https://log-app-demo-api.onrender.com/getvistors";

        JsonArrayRequest request=new JsonArrayRequest(
                Request.Method.GET,
                apiUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        StringBuilder result=new StringBuilder();

                            for(int i=0;i<response.length();i++){
                                try{
                                JSONObject ob=response.getJSONObject(i);
                                String getFName=ob.getString("firstname");
                                String getLName=ob.getString("lastname");
                                String getPurpose=ob.getString("purpose");
                                String getWhomToMeet=ob.getString("whomToMeet");

                                result.append("First Name : ").append(getFName).append("\n");
                                result.append("Last Name : ").append(getLName).append("\n");
                                result.append("Purpose : ").append(getPurpose).append("\n");
                                result.append("Whom to meet : ").append(getWhomToMeet).append("\n");
                                result.append("\n----------------------------------------\n");

                            } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                        }
                            t1.setText(result.toString());


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
                    }
                }
        );

        RequestQueue requestQ= Volley.newRequestQueue(getApplicationContext());
        requestQ.add(request);


    }
}