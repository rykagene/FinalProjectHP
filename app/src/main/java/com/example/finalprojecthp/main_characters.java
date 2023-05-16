package com.example.finalprojecthp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class main_characters extends AppCompatActivity {

    TextView TVname, TVbday, TVgender, TVhouse, TValias, TVfam, TVspecies, TVhaircolor, TVpatronus, TVeyecolor, TVskincolor, TVbloodstatus, TVmaritalstatus, TVnationality, TVanimagus, TVboggart;
    ImageButton BTNsearch;
    ImageView char_image;
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
        char_image = findViewById(R.id.char_image);
        ETsearch = findViewById(R.id.ETsearch);
        BTNsearch = findViewById(R.id.BTNsearch);
        TVbday = findViewById(R.id.TVbday);
        TVgender = findViewById(R.id.TVgender);
        TVhouse = findViewById(R.id.TVhouse);
        TValias = findViewById(R.id.TValias);
        TVfam = findViewById(R.id.TVfam);
        TVspecies = findViewById(R.id.TVspecies);
        TVhaircolor = findViewById(R.id.TVhaircolor);
        TVpatronus = findViewById(R.id.TVpatronus);
        TVeyecolor = findViewById(R.id.TVeyecolor);
        TVskincolor = findViewById(R.id.TVskincolor);
        TVbloodstatus = findViewById(R.id.TVbloodstatus);
        TVmaritalstatus = findViewById(R.id.TVmaritalstatus);
        TVnationality = findViewById(R.id.TVnationality);
        TVboggart = findViewById(R.id.TVboggart);

        BTNsearch.setOnClickListener(v -> {
            String search = ETsearch.getText().toString().trim().toLowerCase();
            search = search.replace(" ", "-"); // Replace spaces with dashes

            if (search.isEmpty()) {
                Toast.makeText(main_characters.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                return;
            }

            String url = "https://api.potterdb.com/v1/characters/" + search;

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
                try {

                    JSONObject data = response.getJSONObject("data");
                    JSONObject attributes = data.getJSONObject("attributes");


                    String name = attributes.getString("name");
                    String imageUrl = attributes.getString("image");
                    String born = attributes.getString("born");
                    String gender = attributes.getString("gender");
                    String house = attributes.getString("house");
                    String species = attributes.getString("species");
                    String hairColor = attributes.getString("hair_color");
                    String eyeColor = attributes.getString("eye_color");
                    String skinColor = attributes.getString("skin_color");
                    String bloodStatus = attributes.getString("blood_status");
                    String maritalStatus = attributes.getString("marital_status");
                    String nationality = attributes.getString("nationality");
                    String animagus = attributes.getString("animagus");
                    String boggart = attributes.getString("boggart");
                    String patronus = attributes.getString("patronus");


                    JSONArray aliasNames = attributes.getJSONArray("alias_names");
                    for (int i = 0; i < aliasNames.length(); i++) {
                        String aliasName = aliasNames.getString(i);
                        TValias.append("\n" + aliasName);
                    }

                    JSONArray family = attributes.getJSONArray("family_members");
                    for (int i = 0; i < family.length(); i++) {
                        String familyMembers = family.getString(i);
                        TVfam.append("\n" + familyMembers);
                    }

                    // Sets
                    TVname.setText("Name: " + name);
                    TVbday.setText("Born: " + born);
                    TVgender.setText("Gender: " + gender);
                    TVhouse.setText("House: " + house);
                    TVspecies.setText("Species: " + species);
                    TVhaircolor.setText("Hair Color: " + hairColor);
                    TVeyecolor.setText("Eye Color: " + eyeColor);
                    TVskincolor.setText("Skin Color: " + skinColor);
                    TVbloodstatus.setText("Blood Status: " + bloodStatus);
                    TVmaritalstatus.setText("Marital Status: " + maritalStatus);
                    TVnationality.setText("Nationality: " + nationality);
                    TVboggart.setText("Boggart: " + boggart);
                    TVpatronus.setText("Patronous: " + patronus);

                    //load image
                    Picasso.get().load(imageUrl).into(char_image);

                } catch (JSONException e) {
                    Toast.makeText(main_characters.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }, error -> Toast.makeText(main_characters.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show());

            queue.add(request);
        });
    }
}