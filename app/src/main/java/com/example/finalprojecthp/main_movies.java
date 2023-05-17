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

public class main_movies extends AppCompatActivity {

    TextView TVtitle, TVrdate, TVrtime, TVbudget, TVboxoffice, TVdistributors, TVrating, TVorder, TVtrailer, TVwiki, TVdirectors, TVscreenwriters, TVproducers, TVeditors, TVmsccomp, TVsummary;
    ImageButton BTNsearch;
    ImageView char_image;
    EditText ETsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreen.makeFullScreen(this);
        setContentView(R.layout.activity_main_movies);
        initialize();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initialize() {
        char_image = findViewById(R.id.char_image);
        ETsearch = findViewById(R.id.ETsearch);
        BTNsearch = findViewById(R.id.BTNsearch);
        TVtitle = findViewById(R.id.TVtitle);
        TVrdate = findViewById(R.id.TVrdate);
        TVrtime = findViewById(R.id.TVrtime);
        TVbudget = findViewById(R.id.TVbudget);
        TVboxoffice = findViewById(R.id.TVboxoffice);
        TVrating = findViewById(R.id.TVrating);
        TVorder = findViewById(R.id.TVorder);
        TVtrailer = findViewById(R.id.TVtrailer);
        TVwiki = findViewById(R.id.TVwiki);
        TVdirectors = findViewById(R.id.TVdirectors);
        TVscreenwriters = findViewById(R.id.TVscreenwriters);
        TVproducers = findViewById(R.id.TVproducers);
        TVeditors = findViewById(R.id.TVeditors);
        TVdistributors = findViewById(R.id.TVdistributors);
        TVmsccomp = findViewById(R.id.TVmsccomp);
        TVsummary = findViewById(R.id.TVsummary);


        BTNsearch.setOnClickListener(v -> {
            String search = ETsearch.getText().toString().trim().toLowerCase();
            search = search.replace(" ", "-"); // Replace spaces with dashes

            if (search.isEmpty()) {
                Toast.makeText(main_movies.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                return;
            }

            String url = "https://api.potterdb.com/v1/characters/" + search;

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
                try {

                    JSONObject data = response.getJSONObject("data");
                    JSONObject attributes = data.getJSONObject("attributes");


                    String title = attributes.getString("title");
                    String imageUrl = attributes.getString("poster");
                    String rdate = attributes.getString("release_date");
                    String rtime = attributes.getString("runtime");
                    String budget = attributes.getString("budget");
                    String boxoffice = attributes.getString("box_office");
                    String rating = attributes.getString("rating");
                    String order = attributes.getString("order");
                    String trailer = attributes.getString("trailer");
                    String wiki = attributes.getString("wiki");
                    String summary = attributes.getString("summary");


                    // Sets
                    TVtitle.setText("Title: " + title);
                    TVrdate.setText("Release Date: " + rdate);
                    TVrtime.setText("Runtime: " + rtime);
                    TVbudget.setText("Budget: " + budget);
                    TVboxoffice.setText("Box Office: " + boxoffice);
                    TVrating.setText("Rating: " + rating);
                    TVorder.setText("Order: " + order);
                    TVtrailer.setText("Trailer: " + trailer);
                    TVwiki.setText("Wiki: " + wiki);
                    TVsummary.setText("Summary: " + summary);


                    JSONArray directors = attributes.getJSONArray("directors");
                    for (int i = 0; i < directors.length(); i++) {
                        String director = directors.getString(i);
                        TVdirectors.append("\n" + director);
                    }

                    JSONArray screenwriters = attributes.getJSONArray("screenwriters");
                    for (int i = 0; i < screenwriters.length(); i++) {
                        String screenwriter = screenwriters.getString(i);
                        TVscreenwriters.append("\n" + screenwriter);
                    }

                    JSONArray producers = attributes.getJSONArray("producers");
                    for (int i = 0; i < producers.length(); i++) {
                        String producer = producers.getString(i);
                        TVproducers.append("\n" + producer);
                    }

                    JSONArray editors = attributes.getJSONArray("editors");
                    for (int i = 0; i < editors.length(); i++) {
                        String editor = editors.getString(i);
                        TVeditors.append("\n" + editor);
                    }

                    JSONArray distributors = attributes.getJSONArray("distributors");
                    for (int i = 0; i < distributors.length(); i++) {
                        String distributor = distributors.getString(i);
                        TVdistributors.append("\n" + distributor);
                    }

                    JSONArray msccomp = attributes.getJSONArray("music_composers");
                    for (int i = 0; i < msccomp.length(); i++) {
                        String msccomps = msccomp.getString(i);
                        TVmsccomp.append("\n" + msccomps);
                    }

                    //load image
                    Picasso.get().load(imageUrl).into(char_image);

                } catch (JSONException e) {
                    Toast.makeText(main_movies.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }, error -> Toast.makeText(main_movies.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show());

            queue.add(request);
        });
    }
}