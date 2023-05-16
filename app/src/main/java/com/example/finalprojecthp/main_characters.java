package com.example.finalprojecthp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class main_characters extends AppCompatActivity {

    TextView TVname;
    ImageButton BTNsearch;
    EditText ETsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreen.makeFullScreen(this);
        setContentView(R.layout.activity_main_characters);
        initialize();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void initialize() {
        TVname = findViewById(R.id.TVname);
        ETsearch = findViewById(R.id.ETsearch);
        BTNsearch = findViewById(R.id.BTNsearch);

        BTNsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search =  ETsearch.getText().toString().trim();
                if (search.isEmpty()) {
                    Toast.makeText(main_characters.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                    return;
            }
                String url = "https://api.potterdb.com/" + search;

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            JSONObject attributes = data.getJSONObject("attributes");

                            String name = attributes.getString("name");
                            /*
                            String born = attributes.getString("born");
                            String gender = attributes.getString("gender");
                            String house = attributes.getString("house");

                            JSONArray aliasNames = attributes.getJSONArray("alias_names");
                            JSONArray familyMembers = attributes.getJSONArray("family_members");
                            */

                            // Set the name in the TVname TextView
                            TVname.setText(name);
                        } catch (JSONException e) {
                            Toast.makeText(main_characters.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(main_characters.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(request);
            }
        });
    }
}