package com.example.finalprojecthp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class main_movies extends AppCompatActivity {

    TextView TVtitle, TVsmovie, TVrdate, TVrtime, TVbudget, TVboxoffice, TVdistributors, TVrating, TVorder, TVtrailer, TVwiki, TVdirectors, TVscreenwriters, TVproducers, TVeditors, TVmsccomp, TVsummary;
    ImageButton BTNsearch;
    ImageView char_image;
    EditText ETsearch;
    Spinner moviespinner;


    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreen.makeFullScreen(this);
        setContentView(R.layout.activity_main_movies);
        initialize();



        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        fetchApiData();
    }

    private void fetchApiData() {
        String url = "https://api.potterdb.com/v1/movies/";

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // API response received successfully
                generateDataAndSetupRecyclerView(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error in fetching the API response
                error.printStackTrace();
                Toast.makeText(main_movies.this, "Error fetching API data", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }
    private void generateDataAndSetupRecyclerView(String apiResponse) {
        List<ItemData> data = new ArrayList<>();

        try {
            JSONObject jsonResponse = new JSONObject(apiResponse);
            JSONArray jsonArray = jsonResponse.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject movieObject = jsonArray.getJSONObject(i);
                JSONObject attributesObject = movieObject.getJSONObject("attributes");

                String id = movieObject.getString("id");
                String imageUrl = attributesObject.getString("poster");
                String title = attributesObject.getString("title");
                String release = attributesObject.getString("release_date");
                String time = attributesObject.getString("running_time");

                ItemData itemData = new ItemData(id,imageUrl, title, release, time);
                data.add(itemData);
            }

            adapter = new CustomAdapter(data);
            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(main_movies.this, "Error parsing API data", Toast.LENGTH_SHORT).show();
        }
    }










    @Override
    protected void onStart() {
        super.onStart();
    }


    private void initialize() {





//        char_image = findViewById(R.id.char_image);
//        ETsearch = findViewById(R.id.ETsearch);
//        BTNsearch = findViewById(R.id.BTNsearch);
//        TVtitle = findViewById(R.id.TVtitle);
//        TVrdate = findViewById(R.id.TVrdate);
//        TVrtime = findViewById(R.id.TVrtime);
//        TVbudget = findViewById(R.id.TVbudget);
//        TVboxoffice = findViewById(R.id.TVboxoffice);
//        TVrating = findViewById(R.id.TVrating);
//        TVorder = findViewById(R.id.TVorder);
//        TVtrailer = findViewById(R.id.TVtrailer);
//        TVwiki = findViewById(R.id.TVwiki);
//        TVdirectors = findViewById(R.id.TVdirectors);
//        TVscreenwriters = findViewById(R.id.TVscreenwriters);
//        TVproducers = findViewById(R.id.TVproducers);
//        TVeditors = findViewById(R.id.TVeditors);
//        TVdistributors = findViewById(R.id.TVdistributors);
//        TVmsccomp = findViewById(R.id.TVmsccomp);
//        TVsummary = findViewById(R.id.TVsummary);
//        TVsmovie = findViewById(R.id.TVsmovie);
//        moviespinner = findViewById(R.id.moviespinner);


        /*
        BTNsearch.setOnClickListener(v -> {
            String search = ETsearch.getText().toString().trim().toLowerCase();
            search = search.replace(" ", "-"); // Replace spaces with dashes

            if (search.isEmpty()) {
                Toast.makeText(main_movies.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                return;
            }

            String url = "https://api.potterdb.com/v1/movies";

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String finalSearch = search;
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
                try {
                    JSONArray movies = response.getJSONArray("data");

                    String slug = "";
                    for (int i = 0; i < movies.length(); i++) {
                        JSONObject movie = movies.getJSONObject(i);
                        String title = movie.optString("title", "").toLowerCase();

                        if (title.equals(finalSearch)) {
                            slug = movie.optString("slug", "");
                            break;
                        }
                    }

                    if (!slug.isEmpty()) {
                        // Perform a separate request to retrieve detailed information based on the obtained slug
                        String movieUrl = "https://api.potterdb.com/v1/movies/" + slug;
                        JsonObjectRequest movieRequest = new JsonObjectRequest(Request.Method.GET, movieUrl, null, movieResponse -> {
                            JSONObject movieData = movieResponse.optJSONObject("data");
                            if (movieData != null) {
                                JSONObject attributes = movieData.optJSONObject("attributes");
                                if (attributes != null) {
                                    String title = attributes.optString("title", "");
                                    String imageUrl = attributes.optString("poster", "");
                                    String rdate = attributes.optString("release_date", "");
                                    String rtime = attributes.optString("runtime", "");
                                    String budget = attributes.optString("budget", "");
                                    String boxoffice = attributes.optString("box_office", "");
                                    String rating = attributes.optString("rating", "");
                                    String order = attributes.optString("order", "");
                                    String trailer = attributes.optString("trailer", "");
                                    String wiki = attributes.optString("wiki", "");
                                    String summary = attributes.optString("summary", "");

                                    // Update the UI with the retrieved movie information
                                    TVtitle.setText("Title: " + title);
                                    TVrdate.setText("Release Date: " + rdate);
                                    // Set other TextViews as needed

                                    // Load the image using Picasso
                                    Picasso.get().load(imageUrl).into(char_image);
                                } else {
                                    Toast.makeText(main_movies.this, "Movie details not available", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(main_movies.this, "Invalid movie data", Toast.LENGTH_SHORT).show();
                            }
                        }, movieError -> {
                            Toast.makeText(main_movies.this, "Error: " + movieError.getMessage(), Toast.LENGTH_SHORT).show();
                        });

                        queue.add(movieRequest);
                    } else {
                        Toast.makeText(main_movies.this, "Movie not found", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(main_movies.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }, error -> {
                Toast.makeText(main_movies.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            });

            queue.add(request);
        });

        */


        /*

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


         */
    }
}