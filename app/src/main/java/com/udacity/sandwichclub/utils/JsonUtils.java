package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        JSONObject jsonObject=new JSONObject(json);
        JSONObject name=jsonObject.getJSONObject("name");
        String mainName=name.getString("mainName");

        JSONArray alsoKnownAs=name.getJSONArray("alsoKnownAs");
        ArrayList<String>alsoKnownAsList=new ArrayList<>();
        for (int i=0;i<alsoKnownAs.length();i++)
        {
            alsoKnownAsList.add(alsoKnownAs.getString(i));

        }
        String placeOfOrigin=jsonObject.getString("placeOfOrigin");
        String discription=jsonObject.getString("description");
        String image=jsonObject.getString("image");

        JSONArray ingredients=jsonObject.getJSONArray("ingredients");
        ArrayList<String>ingredientsList=new ArrayList<>();
        for (int i=0;i<ingredients.length();i++)
        {
            ingredientsList.add(ingredients.getString(i));
        }
        return new Sandwich(mainName,alsoKnownAsList,placeOfOrigin,discription,image,ingredientsList);
    }
}
