package com.example.finalprojecthp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class main_wands extends AppCompatActivity {

    TextView TVname, TVwood, TVcore, TVlength, TVmade, TVchar, TVowner, TVmanu;
    ImageButton BTNsearch;
    ImageView IVwand;
    EditText ETsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreen.makeFullScreen(this);
        setContentView(R.layout.activity_main_wands);
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
        TVname = findViewById(R.id.TVname);
        TVwood = findViewById(R.id.TVwood);
        IVwand = findViewById(R.id.IVwand);
        TVcore = findViewById(R.id.TVcore);
        TVlength = findViewById(R.id.TVlength);
        TVmade = findViewById(R.id.TVmade);
        TVchar = findViewById(R.id.TVchar);
        TVmanu = findViewById(R.id.TVmanu);




        BTNsearch.setOnClickListener(v -> {
            String search = ETsearch.getText().toString().trim();

            if (search.isEmpty()) {
                Toast.makeText(main_wands.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                return;
            }

            String url = "https://legacy--api.herokuapp.com/api/v1/wands/" + search;

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
                try {
                    JSONObject master = response.getJSONObject("master");
                    String ownerName = master.getString("name");

                    String imageUrl = response.getString("image_url");
                    String woodStr = response.getString("wood");
                    String coreStr = response.getString("core");
                    String lengthStr = response.getString("length");
                    String madeStr = response.getString("made");
                    String charStr = response.getString("characteristics");

                    JSONArray ownersArray = response.getJSONArray("owners");
                    for (int i = 0; i < ownersArray.length(); i++) {
                        String owners = ownersArray.getString(i);
                        TVowner.append("\n" + owners);
                    }

                    String manuStr = response.getString("manufacturer");

                    // Sets
                    TVname.setText(ownerName + "'s Wand");
                    TVwood.setText("Wood: " + woodStr);
                    TVcore.setText("Core: " + coreStr);
                    TVlength.setText("Length: " + lengthStr);
                    TVmade.setText("Made: " + madeStr);
                    TVchar.setText("Characteristics: " + charStr);
                    TVmanu.setText("Manufacturer: " + manuStr);

                    // Load image
                    Picasso.get().load(imageUrl).into(IVwand);

                } catch (JSONException e) {
                    Toast.makeText(main_wands.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }, error -> Toast.makeText(main_wands.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show());

            queue.add(request);
        });
    }
}