package com.example.finalprojecthp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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


        //alertdialog for failed
        AlertDialog.Builder builder = new AlertDialog.Builder(main_potions.this);
        builder.setTitle("Potion Exploded!")
                .setMessage("Sorry, but you have failed to mix ingredients. Try again to discover potion!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Perform any action needed
                    }
                });
        AlertDialog failedDialog = builder.create();

        //alertdialog for success
        AlertDialog.Builder builder2 = new AlertDialog.Builder(main_potions.this);
        builder2.setTitle("Potion Discovered!")
                .setMessage("Congratulations! You have successfully discovered a potion!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Perform any action needed
                    }
                });
        AlertDialog successDialog = builder2.create();


        ETsearch = findViewById(R.id.ETsearch);
        BTNsearch = findViewById(R.id.BTNsearch);
//        BTNclear = findViewById(R.id.BTNclear);
        char_image = findViewById(R.id.char_image);
        name = findViewById(R.id.name);
        eff = findViewById(R.id.effect);
//        chara = findViewById(R.id.characteristics);
//        diff = findViewById(R.id.difficulty);
        ing = findViewById(R.id.ingredients);

        Spinner ingredientCountSpinner = findViewById(R.id.ingredientCountSpinner);


        Integer[] ingredientCounts = new Integer[5];
        for (int i = 0; i < ingredientCounts.length; i++) {
            ingredientCounts[i] = i + 1;
        }

        ArrayAdapter<Integer> ingredientCountAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, ingredientCounts);
        ingredientCountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ingredientCountSpinner.setAdapter(ingredientCountAdapter);

        int defaultIngredientCount =1 ;
        ingredientCountSpinner.setSelection(defaultIngredientCount - 1);

        final int[] spinnerCount = {ingredientCountSpinner.getSelectedItemPosition() + 1};

        ingredientCountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                spinnerCount[0] = position + 1; // Add 1 to match desired ingredient count
                updateSpinners(spinnerCount[0]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });

        updateSpinners(defaultIngredientCount);

        BTNsearch.setOnClickListener(v -> {
            LinearLayout spinnerContainer = findViewById(R.id.spinnerContainer);

            StringBuilder ingredientsBuilder = new StringBuilder();

            for (int i = 0; i < spinnerContainer.getChildCount(); i++) {
                Spinner spinner = (Spinner) spinnerContainer.getChildAt(i);
                String selectedIngredient = spinner.getSelectedItem().toString();

                if (!selectedIngredient.equals("Choose ingredient")) {
                    ingredientsBuilder.append(selectedIngredient);
                    if (i < spinnerContainer.getChildCount() - 1) {
                        ingredientsBuilder.append(",");
                    }
                }
            }

            String ingredients = ingredientsBuilder.toString();

            String url;

            if (ingredients.isEmpty()) {

                Toast.makeText(main_potions.this, "Please select at least one ingredient", Toast.LENGTH_SHORT).show();
                return;
            } else if (ingredients.contains(",")) {
                // Multiple ingredients selected
                url = "https://api.potterdb.com/v1/potions?filter[ingredients_cont_all]=" + ingredients;
            } else {
                // Single ingredient selected
                url = "https://api.potterdb.com/v1/potions?filter[ingredients_eq]=" + ingredients;
            }

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
                try {
                    JSONArray dataArray = response.getJSONArray("data");
                    if (dataArray.length() > 0) {

                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject data = dataArray.getJSONObject(i);
                            JSONObject attributes = data.getJSONObject("attributes");

                            String imageUrl = attributes.getString("image");
                            String nameStr = attributes.getString("name");
                            String effStr = attributes.getString("effect");
//                            String ingStr = attributes.getString("ingredients");

                            name.setText("Name: " + nameStr);
                            eff.setText("Effect: " + effStr);
//                            ing.setText("Ingredients: " + ingStr);
                            Picasso.get().load(imageUrl).into(char_image);

                            //alert success
                            successDialog.show();
                        }
                    } else {

                        //alert explode
                        failedDialog.show();

                    }


                } catch (JSONException e) {
                    Toast.makeText(main_potions.this, "Errora: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }, error -> Toast.makeText(main_potions.this, "Errors: " + error.getMessage(), Toast.LENGTH_SHORT).show());

            queue.add(request);
        });



//        BTNsearch.setOnClickListener(v -> {
//
//            LinearLayout spinnerContainer = findViewById(R.id.spinnerContainer);
//
//            StringBuilder ingredientsBuilder = new StringBuilder();
//
//            for (int i = 0; i < spinnerContainer.getChildCount(); i++) {
//                Spinner spinner = (Spinner) spinnerContainer.getChildAt(i);
//                String selectedIngredient = spinner.getSelectedItem().toString();
//
//                if (!selectedIngredient.equals("Choose ingredient")) {
//                    ingredientsBuilder.append(selectedIngredient);
//                    if (i < spinnerContainer.getChildCount() - 1) {
//                        ingredientsBuilder.append(",");
//                    }
//                }
//            }
//
//            String ingredients = ingredientsBuilder.toString();
//
//            Toast.makeText(this, ingredients, Toast.LENGTH_SHORT).show();
////
////            String selectedIngredient1 = ingredientSpinner1.getSelectedItem().toString();
////            String selectedIngredient2 = ingredientSpinner2.getSelectedItem().toString();
////            String selectedIngredient3 = ingredientSpinner3.getSelectedItem().toString();
////
////            String ingredients = selectedIngredient1 + "," + selectedIngredient2 + "," + selectedIngredient3;
//
//            String url = "https://api.potterdb.com/v1/potions?filter[ingredients_cont_all]=" + ingredients;
//
//
//            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
//                try {
//                    JSONArray dataArray = response.getJSONArray("data");
//                    if (dataArray.length() > 0) {
//
//                        for (int i = 0; i < dataArray.length(); i++) {
//                            JSONObject data = dataArray.getJSONObject(i);
//                            JSONObject attributes = data.getJSONObject("attributes");
//
//                            String imageUrl = attributes.getString("image");
//                            String nameStr = attributes.getString("name");
//                            String effStr = attributes.getString("effect");
//                            String ingStr = attributes.getString("ingredients");
//
//                            name.setText("Name: " + nameStr);
//                            eff.setText("Effect: " + effStr);
//                            ing.setText("Ingredients: " + ingStr);
//                            Picasso.get().load(imageUrl).into(char_image);
//                            successDialog.show();
//                        }
//                    } else {
//                        // Handle the case when no data is found
//
//                        failedDialog.show();
//
//                    }
//
//
//                } catch (JSONException e) {
//                    Toast.makeText(main_potions.this, "Errora: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }, error -> Toast.makeText(main_potions.this, "Errors: " + error.getMessage(), Toast.LENGTH_SHORT).show());
//
//            queue.add(request);
//
//        });
    }

    // Function to dynamically update the number of spinners based on the user's choice
    private void updateSpinners(int spinnerCount) {
        LinearLayout spinnerContainer = findViewById(R.id.spinnerContainer);
        spinnerContainer.removeAllViews();

        for (int i = 0; i < spinnerCount; i++) {
            Spinner spinner = new Spinner(this);
            spinner.setId(i);

            // Create an array with the hint text and ingredients
            String[] ingredientsArray = getResources().getStringArray(R.array.ingredients_array);
            String[] spinnerItems = new String[ingredientsArray.length + 1];
            spinnerItems[0] = "Choose ingredient";
            System.arraycopy(ingredientsArray, 0, spinnerItems, 1, ingredientsArray.length);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, spinnerItems);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinnerContainer.addView(spinner);
        }
    }
}

//                    JSONObject data = response.getJSONObject("data");
//                    JSONObject attributes = data.getJSONObject("attributes");
//
//                    String imageUrl = attributes.getString("image");
//                    String nameStr = attributes.getString("name");
//                    String effStr = attributes.getString("effect");
//                    String charaStr = attributes.getString("characteristics");
//                    String diffStr = attributes.getString("difficulty");
//
//// Sets
//                    name.setText("Name: " + nameStr);
//                    eff.setText("Effect: " + effStr);
//                    chara.setText("Characteristic: " + charaStr);
//                    diff.setText("Difficulty: " + diffStr);
//
//                    JSONArray ingArr = attributes.getJSONArray("ingredients");
//                    StringBuilder ingredientsBuilder = new StringBuilder();
//
//                    for (int i = 0; i < ingArr.length(); i++) {
//                        String ingredient = ingArr.getString(i);
//                        ingredientsBuilder.append("\n").append(ingredient);
//                    }
//
//                    String ingredientsStr = ingredientsBuilder.toString();
//                    ing.setText("Ingredients: " + ingredientsStr);
//
//// Load image
//                    Picasso.get().load(imageUrl).into(char_image);
