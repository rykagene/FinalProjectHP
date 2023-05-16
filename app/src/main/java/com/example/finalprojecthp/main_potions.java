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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class main_potions extends AppCompatActivity {

    TextView name, eff, chara, diff, ing;
    ImageButton BTNsearch;
    ImageView char_image;
    EditText ETsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreen.makeFullScreen(this);
        setContentView(R.layout.activity_main_potions);
        initialize();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initialize() {


        ETsearch = findViewById(R.id.ETsearch);
        BTNsearch = findViewById(R.id.BTNsearch);
//        BTNclear = findViewById(R.id.BTNclear);
        char_image = findViewById(R.id.char_image);
        name = findViewById(R.id.name);
        eff = findViewById(R.id.effect);
        chara = findViewById(R.id.characteristics);
        diff = findViewById(R.id.difficulty);
        ing = findViewById(R.id.ingredients);


        BTNsearch.setOnClickListener(v -> {
            String search = ETsearch.getText().toString().trim().toLowerCase();
            search = search.replace(" ", "-"); // Replace spaces with dashes

            if (search.isEmpty()) {
                Toast.makeText(main_potions.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                return;
            }

            String url = "https://api.potterdb.com/v1/potions/" + search;

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
                try {

                    JSONObject data = response.getJSONObject("data");
                    JSONObject attributes = data.getJSONObject("attributes");

                    String imageUrl = attributes.getString("image");
                    String nameStr = attributes.getString("name");
                    String effStr = attributes.getString("effect");
                    String charaStr = attributes.getString("characteristics");
                    String diffStr = attributes.getString("difficulty");
                    String ingStr = attributes.getString("ingredients");

                    // Sets
                    name.setText("Name: " + nameStr);
                    eff.setText("Effect: " + effStr);
                    chara.setText("Characteristic: " + charaStr);
                    diff.setText("Difficulty: " + diffStr);
                    ing.setText("Ingredients: " + ingStr);


//
//
//                    JSONArray ingArr = attributes.getJSONArray("ingredients");
//                    for (int i = 0; i < ingArr.length(); i++) {
//
//                        ing.append("\n" + ingArr.getString(i));
//                    }



                    //load image
                    Picasso.get().load(imageUrl).into(char_image);


                } catch (JSONException e) {
                    Toast.makeText(main_potions.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }, error -> Toast.makeText(main_potions.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show());

            queue.add(request);
        });
    }
}