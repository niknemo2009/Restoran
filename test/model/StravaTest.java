package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import testMaintenanceClasses.IndicativeSentences;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(IndicativeSentences.class)
class StravaTest {

    private static Strava strava;

    @BeforeAll
    public static void setup(){
        strava = new Strava("Salat cezar", 250);
    }

    @Test
    public void test_izmenitIngredientIliDobavitNovij_dolgen_uvelichit_kolichestvo_ingredienta_v_Strave(){
        Ingredient ingredient = new Ingredient(10, 20);
        Ingredient ingredient2 = new Ingredient(20, 40);
        strava.izmenitIngredientIliDobavitNovij(ingredient, 250);
        strava.izmenitIngredientIliDobavitNovij(ingredient, 50);
        assertEquals(strava.pokazatIngredienti().get(ingredient), 50);
    }

    @Test
    public void test_PoschitatStoimostDliaKlienta_Should_return_correct_price_of_Strava(){
        Ingredient ingredient = new Ingredient(10, 20);
        strava.izmenitIngredientIliDobavitNovij(ingredient, 250);
        strava.izmenitIngredientIliDobavitNovij(ingredient, 300);

        assertEquals(strava.poschitatStoimostDliaKlienta(), 300*20);
    }

    @Test
    public void test_poschitatStoimistZakupki_Should_return_correct_price_of_Strava(){
        Ingredient ingredient = new Ingredient(10, 20);
        strava.izmenitIngredientIliDobavitNovij(ingredient, 250);
        strava.izmenitIngredientIliDobavitNovij(ingredient, 300);

        assertEquals(strava.poschitatStoimistZakupki(), 300*10);
    }
}