package com.example.finalprojecthp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class main_spells extends AppCompatActivity {

    TextView NAME, INCANTATION,EFFECT,LIGHT,CATEGORY;
    ImageButton BTNsearch;
    ImageView IVwand;
    EditText ETsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreen.makeFullScreen(this);
        setContentView(R.layout.activity_main_spells);
        initialize();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initialize() {


        ETsearch = findViewById(R.id.ETsearch);
        BTNsearch = findViewById(R.id.BTNsearch);
//      BTNclear = findViewById(R.id.BTNclear);
        IVwand = findViewById(R.id.char_image);
        NAME = findViewById(R.id.NAME);
        INCANTATION = findViewById(R.id.INCANTATION);
        EFFECT	 = findViewById(R.id.EFFECT	);
        CATEGORY	 = findViewById(R.id.CATEGORY);
        LIGHT	 = findViewById(R.id.LIGHT	);


        BTNsearch.setOnClickListener(v -> {
            String search = ETsearch.getText().toString().trim().toLowerCase();
            search = search.replace(" ", "-"); // Replace spaces with dashes



            if (search.isEmpty()) {
                Toast.makeText(main_spells.this, "Please enter spell", Toast.LENGTH_SHORT).show();
                return;
            }

            String url = "https://api.potterdb.com/v1/spells/" + search;

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
                try {
                    JSONObject data = response.getJSONObject("data");
                    JSONObject attributes = data.getJSONObject("attributes");


                    String name = attributes.getString("slug");
                    String image = attributes.getString("image");

                    String incantation = attributes.getString("incantation");
                    String effect = attributes.getString("effect");
                    String category = attributes.getString("category");
                    String light = attributes.getString("light");


                    // Sets
                    NAME.setText("Name: "+ name);
                    INCANTATION.setText("Incantation: " + incantation);
                    EFFECT.setText("Effect: " + effect);
                    CATEGORY.setText("Category: " + category);
                    LIGHT.setText("Light: " + light);


                    // Load image
                    Picasso.get().load(image).into(IVwand);

                } catch (JSONException e) {
                    Toast.makeText(main_spells.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }, error -> Toast.makeText(main_spells.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show());

            queue.add(request);
        });
    }
}