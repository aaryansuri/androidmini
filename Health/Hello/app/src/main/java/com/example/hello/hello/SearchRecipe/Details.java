package com.example.hello.hello.SearchRecipe;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class Details {
    static private String websiteurl,ingredients,energy,fat,sugar,carbs,protein,cholestrol,sodium,calcium,mangnesium,
            iron,zinc,vitaminA,vitaminC,
            vitaminB6,vitaminD,vitaminB12,vitaminK,calorie,carblevel,weight;

    public static String getWeight() {
        return weight;
    }

    public static void setWeight(String weight) {
        Details.weight = weight;
    }

    public static String getCalorie() {

        return calorie;
    }

    public static void setCalorie(String calorie) {
        Details.calorie = calorie;
    }

    public static String getCarblevel() {
        return carblevel;
    }

    public static void setCarblevel(String carblevel) {
        Details.carblevel = carblevel;
    }

    public static String getVitaminK() {
        return vitaminK;
    }

    public static void setVitaminK(String vitaminK) {
        Details.vitaminK = vitaminK;
    }

    public static String getVitaminB12() {
        return vitaminB12;
    }

    public static void setVitaminB12(String vitaminB12) {
        Details.vitaminB12 = vitaminB12;
    }

    public static String getIron() {
        return iron;

    }

    public static void setIron(String iron) {
        Details.iron = iron;
    }

    public static String getSugar() {
        return sugar;
    }

    public static void setSugar(String sugar) {
        Details.sugar = sugar;
    }

    public static String getEnergy() {
        return energy;
    }

    public static void setEnergy(String energy) {
        Details.energy = energy;
    }

    public static String getWebsiteurl() {
        return websiteurl;

    }

    public static void setWebsiteurl(String websiteurl) {
        Details.websiteurl = websiteurl;
    }

    public static String getIngredients() {
        return ingredients;
    }

    public static void setIngredients(String ingredients) {
        Details.ingredients = ingredients;
    }

    public static String getFat() {
        return fat;
    }

    public static void setFat(String fat) {
        Details.fat = fat;
    }

    public static String getCarbs() {
        return carbs;
    }

    public static void setCarbs(String carbs) {
        Details.carbs = carbs;
    }

    public static String getProtein() {
        return protein;
    }

    public static void setProtein(String protein) {
        Details.protein = protein;
    }

    public static String getCholestrol() {
        return cholestrol;
    }

    public static void setCholestrol(String cholestrol) {
        Details.cholestrol = cholestrol;
    }

    public static String getSodium() {
        return sodium;
    }

    public static void setSodium(String sodium) {
        Details.sodium = sodium;
    }

    public static String getCalcium() {
        return calcium;
    }

    public static void setCalcium(String calcium) {
        Details.calcium = calcium;
    }

    public static String getMangnesium() {
        return mangnesium;
    }

    public static void setMangnesium(String mangnesium) {
        Details.mangnesium = mangnesium;
    }

    public static String getZinc() {
        return zinc;
    }

    public static void setZinc(String zinc) {
        Details.zinc = zinc;
    }

    public static String getVitaminA() {
        return vitaminA;
    }

    public static void setVitaminA(String vitaminA) {
        Details.vitaminA = vitaminA;
    }

    public static String getVitaminC() {
        return vitaminC;
    }

    public static void setVitaminC(String vitaminC) {
        Details.vitaminC = vitaminC;
    }

    public static String getVitaminB6() {
        return vitaminB6;
    }

    public static void setVitaminB6(String vitaminB6) {
        Details.vitaminB6 = vitaminB6;
    }

    public static String getVitaminD() {
        return vitaminD;
    }

    public static void setVitaminD(String vitaminD) {
        Details.vitaminD = vitaminD;
    }

    public static void loadData(String name, String lim0, String lim1 , final String pos, final Context context){

        String url = "https://api.edamam.com/search?q="+name
                +"&app_id="+APIKEY.getApiId()+"&app_key="+APIKEY.getAPI_key()+"&from="+lim0+"&to="+lim1;


        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("hits");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        if(i==(Integer.parseInt(pos)%10)){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            JSONObject jsonObject2 = jsonObject1.getJSONObject("recipe");
                            String url = jsonObject2.getString("url");
                            setWebsiteurl(url);

                            JSONArray jsonArray1 = jsonObject2.getJSONArray("ingredientLines");
                            StringBuilder ingredients = new StringBuilder();
                            for (int j=0;j<jsonArray1.length();j++) {
                                ingredients = ingredients.append(jsonArray1.getString(j));
                                ingredients.append("\n\n");
                            }
                            setIngredients(ingredients.toString());
                            String quantity = "quantity";
                            String g = " g";
                            String kcal = " kcal";
                            String mg = " mg";
                            String ug = " ug";
                            String name;
                            JSONArray jsonArray2 = jsonObject2.getJSONArray("dietLabels");
                            StringBuilder carblevel =  new StringBuilder();
                            for (int j=0;j<jsonArray2.length();j++) {
                                carblevel = carblevel.append(jsonArray2.getString(j));
                                carblevel.append("\n");
                            }

                            setCarblevel(carblevel.toString());

                            String calories = jsonObject2.getString("calories");
                            String[] split = calories.split("\\.");

                            setCalorie(split[0]);
                            String weight = jsonObject2.getString("totalWeight");
                            setWeight(weight.substring(0,6).concat(" g"));

                            JSONObject jsonObject3 = jsonObject2.getJSONObject("totalNutrients");
                            JSONObject jsonObject4 = jsonObject3.getJSONObject("ENERC_KCAL");
                            name = jsonObject4.getString(quantity);
                            setEnergy(name.substring(0,4).concat(kcal));
                            JSONObject jsonObject5 = jsonObject3.getJSONObject("FAT");
                            name = jsonObject5.getString(quantity);
                            setFat(name.substring(0,4).concat(g));
                            JSONObject jsonObject6 = jsonObject3.getJSONObject("CHOCDF");
                            name = jsonObject6.getString(quantity);
                            setCarbs(name.substring(0,4).concat(g));
                            JSONObject jsonObject7 = jsonObject3.getJSONObject("SUGAR");
                            name = jsonObject7.getString(quantity);
                            setSugar(name.substring(0,4).concat(g));
                            JSONObject jsonObject8 = jsonObject3.getJSONObject("PROCNT");
                            name = jsonObject8.getString(quantity);
                            setProtein(name.substring(0,4).concat(g));
                            JSONObject jsonObject9 = jsonObject3.getJSONObject("CHOLE");
                            name = jsonObject9.getString(quantity);
                            setCholestrol(name.substring(0,4).concat(mg));
                            JSONObject jsonObject10 = jsonObject3.getJSONObject("NA");
                            name = jsonObject10.getString(quantity);
                            setSodium(name.substring(0,4).concat(mg));
                            JSONObject jsonObject11 = jsonObject3.getJSONObject("CA");
                            name = jsonObject11.getString(quantity);
                            setCalcium(name.substring(0,4).concat(mg));
                            JSONObject jsonObject12 = jsonObject3.getJSONObject("MG");
                            name = jsonObject12.getString(quantity);
                            setMangnesium(name.substring(0,4).concat(mg));
                            JSONObject jsonObject13 = jsonObject3.getJSONObject("FE");
                            name = jsonObject13.getString(quantity);
                            setIron(name.substring(0,4).concat(mg));
                            JSONObject jsonObject14 = jsonObject3.getJSONObject("ZN");
                            name = jsonObject14.getString(quantity);
                            setZinc(name.substring(0,4).concat(mg));
                            JSONObject jsonObject15 = jsonObject3.getJSONObject("VITA_RAE");
                            name = jsonObject15.getString(quantity);
                            setVitaminA(name.substring(0,4).concat(ug));
                            JSONObject jsonObject16 = jsonObject3.getJSONObject("VITC");
                            name = jsonObject16.getString(quantity);
                            setVitaminC(name.substring(0,4).concat(mg));
                            JSONObject jsonObject17 = jsonObject3.getJSONObject("VITB6A");
                            name = jsonObject17.getString(quantity);
                            setVitaminB6(name.substring(0,4).concat(mg));
                            JSONObject jsonObject18 = jsonObject3.getJSONObject("VITD");
                            name = jsonObject18.getString(quantity);
                            setVitaminD(name.substring(0,4).concat(ug));
                            JSONObject jsonObject19 = jsonObject3.getJSONObject("VITB12");
                            name = jsonObject19.getString(quantity);
                            setVitaminB12(name.substring(0,4).concat(ug));
                            JSONObject jsonObject20 = jsonObject3.getJSONObject("VITK1");
                            name = jsonObject20.getString(quantity);
                            setVitaminK(name.substring(0,4).concat(ug));

                            break;
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error"+error.toString(),Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);


    }
}
