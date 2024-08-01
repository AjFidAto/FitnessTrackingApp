package com.ZenFit;

//import java.util.Scanner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class FoodDataAPI {
    private static final String API_KEY = "rchuSJBXeApLmy1TXbab7QD3YdlvDNIyugGHVZKS\r\n";
    		
    private static final String BASE_URL = "https://api.nal.usda.gov/fdc/v1/foods/search"; 


    private OkHttpClient client;

    public FoodDataAPI() {
        this.client = new OkHttpClient();
    }

    public String getFoodData(String query) {
        //String url = BASE_URL + "?api_key=" + API_KEY + "&query=" + query;
        String url = BASE_URL + "?api_key=" + API_KEY + "&query=" + query.replace(" ", "%20");
        //System.out.println(url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response.code());
            }

            return parseFoodData(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            return "An error occurred while fetching food data.";
        }
     // In FoodDataAPI.getFoodData()
		/*
		 * Response response = client.newCall(request).execute(); String jsonResponse =
		 * response.body().string(); System.out.println(jsonResponse);
		 */ // Print raw JSON response

    }

    private String parseFoodData(String response) {
        JSONObject jsonResponse = new JSONObject(response);
        JSONArray foods = jsonResponse.optJSONArray("foods");
        StringBuilder result = new StringBuilder();

        if (foods != null) {
            for (int i = 0; i < foods.length(); i++) {
                JSONObject food = foods.getJSONObject(i);
                String foodName = food.optString("description", "No description");

                // Get nutrients and find calories
                JSONArray nutrients = food.optJSONArray("foodNutrients");
                int calories = 0;
                if (nutrients != null) {
                    for (int j = 0; j < nutrients.length(); j++) {
                        JSONObject nutrient = nutrients.getJSONObject(j);
                        String nutrientName = nutrient.optString("nutrientName");
                        if (nutrientName.equalsIgnoreCase("Energy") || nutrientName.equalsIgnoreCase("Calories")) {
                            calories = nutrient.optInt("value", 0);
                            break;
                        }
                    }
                }

                result.append(foodName).append(": ").append(calories).append(" calories\n");
            }
        } else {
            result.append("No food data found.");
        }

        return result.toString();
    }



    public static void main(String[] args) {
		/*
		 * FoodDataAPI api = new FoodDataAPI(); Scanner scanner = new
		 * Scanner(System.in);
		 * 
		 * System.out.print("Enter food item to get nutritional information: "); String
		 * foodQuery = scanner.nextLine();
		 * 
		 * String foodData = api.getFoodData(foodQuery); System.out.println(foodData);
		 * scanner.close();
		 */
    }
}
