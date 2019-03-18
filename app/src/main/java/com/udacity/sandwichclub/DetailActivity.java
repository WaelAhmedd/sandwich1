package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView mainName=(TextView)findViewById(R.id.main_name_tv);
        mainName.setText(sandwich.getMainName());

        TextView description=(TextView)findViewById(R.id.description_tv);
        description.setText(sandwich.getDescription());

        TextView placeOfOrign=(TextView)findViewById(R.id.origin_tv);
        placeOfOrign.setText(sandwich.getPlaceOfOrigin());

        TextView alsoKnwn=(TextView)findViewById(R.id.also_known_tv);
        if(sandwich.getAlsoKnownAs().size()==0)
        {
            alsoKnwn.setText("-");
        }
        else{
                for(int i=0;i<sandwich.getAlsoKnownAs().size();i++)
             {
                 if(i!=sandwich.getAlsoKnownAs().size()-1) {
                     alsoKnwn.append(sandwich.getAlsoKnownAs().get(i) + " , ");
                 }
                 else
                 {
                     alsoKnwn.append(sandwich.getAlsoKnownAs().get(i));
                 }
             }

            }
        TextView ingredients=(TextView)findViewById(R.id.ingredients_tv);
        if (sandwich.getIngredients().size()==0)
        {
            ingredients.setText("-");
        }
        else {
            for (int i = 0; i < sandwich.getIngredients().size(); i++) {
                if(i!=sandwich.getIngredients().size()-1) {
                    ingredients.append(sandwich.getIngredients().get(i) + " , ");
                }
                else {
                    ingredients.append(sandwich.getIngredients().get(i));
                }
            }
        }
    }
}
