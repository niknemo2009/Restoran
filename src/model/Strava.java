package model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Strava {
    private String name;
    private int price;
    private Map<Ingredient, Integer> ingredienti = new HashMap<>();
    private Map<Ingredient, Integer> dopolnitelnieIngredienti = new HashMap<>();

    public Strava(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Strava izmenitIngredientIliDobavitNovij(Ingredient ingredient, int kolichestvo) {
        ingredienti.put(ingredient, kolichestvo);
        return this;
    }

    private int calculatePrice(Map<Ingredient, Integer> ingredients, Function<Ingredient, Integer> function) {
        int[] result = new int[1];
        ingredients.entrySet().forEach((map) -> {
            Ingredient ingredient = map.getKey();
            int kolichestvo = map.getValue();
            int ingredientPrice = function.apply(ingredient);
            result[0] += kolichestvo * ingredientPrice;
        });
        return result[0];
    }

    public int poschitatStoimostDliaKlienta() {
        if(dopolnitelnieIngredienti.size() > 0){
            return calculatePrice(dopolnitelnieIngredienti, Ingredient::getPriceRoznica) + price;
        }
        return price;
    }

    public int poschitatStoimistZakupki() {
        return calculatePrice(ingredienti, Ingredient::getPriceZakupka);
    }


    public Map<Ingredient, Integer> getDopolnitelnieIngredienti() {
        return dopolnitelnieIngredienti;
    }

    public void setDopolnitelnieIngredienti(Map<Ingredient, Integer> dopolnitelnieIngredienti) {
        this.dopolnitelnieIngredienti = dopolnitelnieIngredienti;
    }

    public Strava setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Strava setName(String name) {
        this.name = name;
        return this;
    }

    public Strava setIngredienti(Map<Ingredient, Integer> ingredienti) {
        this.ingredienti = ingredienti;
        return this;
    }

    public String getName() {
        return name;
    }

    public Map<Ingredient, Integer> pokazatIngredienti() {
        return ingredienti;
    }

    @Override
    public String toString() {
        return "Strava{" + name + '\'' +
                ", цена с доп ингр = " + this.poschitatStoimostDliaKlienta() + "} \n";
    }
}
