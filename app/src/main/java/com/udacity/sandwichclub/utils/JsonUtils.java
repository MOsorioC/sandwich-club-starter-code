package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {
    private static String TAG = "JsonUtils";

    public static Sandwich parseSandwichJson(String json) {
        try {
            Sandwich sandwich = new Sandwich();

            JSONObject sandwichJSON = new JSONObject(json);

            JSONObject nameJsonObject = sandwichJSON.getJSONObject("name");

            sandwich.setMainName(nameJsonObject.getString("mainName"));

            JSONArray alsoKnownAsArray = nameJsonObject.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = getListFromJSONArray(alsoKnownAsArray);
            sandwich.setAlsoKnownAs(alsoKnownAs);

            sandwich.setPlaceOfOrigin(sandwichJSON.getString("placeOfOrigin"));
            sandwich.setDescription(sandwichJSON.getString("description"));
            sandwich.setImage(sandwichJSON.getString("image"));

            JSONArray ingredientsArray = sandwichJSON.getJSONArray("ingredients");
            List<String> ingredients = getListFromJSONArray(ingredientsArray);
            sandwich.setIngredients(ingredients);

            return sandwich;
        } catch (JSONException ex) {
            return null;
        }

    }

    public static List<String> getListFromJSONArray(JSONArray jsonArray) {
        List<String> mList = new ArrayList<>();

        try {
            for (int i = 0 ; i < jsonArray.length() ; i++) {
                mList.add(jsonArray.getString(i));
            }
        } catch (JSONException ex) {
            Log.d(TAG, ex.getMessage());
        }

        return mList;
    }
}
