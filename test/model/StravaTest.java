package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import testMaintenanceClasses.IndicativeSentences;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(IndicativeSentences.class)
class StravaTest {


    private static Strava strava;
    private static Ingredient cheese, pomidori, kapusta;

    @BeforeAll
    public static void setup() {

        cheese = Mockito.mock(Ingredient.class);
        pomidori = Mockito.mock(Ingredient.class);
        kapusta = Mockito.mock(Ingredient.class);

        when(cheese.getPriceRoznica()).thenReturn(50);
        when(cheese.getName()).thenReturn("Сыр Российский");

        when(pomidori.getPriceRoznica()).thenReturn(10);
        when(pomidori.getName()).thenReturn("Помидор Зерсонский");

        when(kapusta.getPriceRoznica()).thenReturn(5);
        when(kapusta.getName()).thenReturn("Капуста белокаченная");

        strava = new Strava("Salat cezar", 250);
    }

    @BeforeEach
    public void setupEach() {
        Map<Ingredient, Integer> ingredienti = new HashMap<>();
        ingredienti.put(cheese, 20);
        ingredienti.put(pomidori, 100);
        ingredienti.put(kapusta, 50);
        strava.setIngredienti(ingredienti);
    }


    @Test
    void izmenitIngredientIliDobavitNovij() {
        strava.izmenitIngredientIliDobavitNovij(pomidori, 5);
        strava.izmenitIngredientIliDobavitNovij(cheese, 555);
        strava.izmenitIngredientIliDobavitNovij(kapusta, 333);
        assertTrue(strava.pokazatIngredienti().get(pomidori) == 5);
        assertTrue(strava.pokazatIngredienti().get(cheese) == 555);
        assertTrue(strava.pokazatIngredienti().get(kapusta) == 333);
    }

    @Test
    public void izmenitIngredientIliDobavitNovij_vibrasivaet_iskluchenit() {
        assertThrows(IllegalArgumentException.class, () -> strava.izmenitIngredientIliDobavitNovij(pomidori, -1));
        assertThrows(IllegalArgumentException.class, () -> strava.izmenitIngredientIliDobavitNovij(null, 999));
    }

    @Test
    void poschitatStoimostDliaKlienta() {
        assertEquals(250, strava.poschitatStoimostDliaKlienta());
        Map<Ingredient, Integer> dopIngredient = new HashMap<>();
        dopIngredient.put(pomidori, 3); // 3 * 10
        strava.setDopolnitelnieIngredienti(dopIngredient);
        assertEquals(280, strava.poschitatStoimostDliaKlienta());
        dopIngredient.put(pomidori, -3); // 3 * 10



    }

    @Test
    void getDopolnitelnieIngredienti() {
    }

    @Test
    void setDopolnitelnieIngredienti() {
    }

    @Test
    void setIngredienti() {
    }

    @Test
    void pokazatIngredienti() {
        assertTrue(strava.pokazatIngredienti().containsKey(cheese));
        assertTrue(strava.pokazatIngredienti().containsKey(kapusta));
        assertTrue(strava.pokazatIngredienti().containsKey(pomidori));
    }
}