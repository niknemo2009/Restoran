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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(IndicativeSentences.class)
class StravaTest {

    private static Strava strava;
    private Ingredient cheese = Mockito.mock(Ingredient.class);
    private Ingredient pomidori = Mockito.mock(Ingredient.class);
    private Ingredient kapusta = Mockito.mock(Ingredient.class);

    @BeforeAll
    public static void setup() {
        System.out.println("Before all called");
        strava = new Strava("Salat cezar", 250);
    }

    @BeforeEach
    public void setupEach() {
        System.out.println("Before each called");

        when(cheese.getPriceRoznica()).thenReturn(50);
        when(cheese.getName()).thenReturn("Сыр Российский");

        when(pomidori.getPriceRoznica()).thenReturn(10);
        when(pomidori.getName()).thenReturn("Помидор Херсонский");

        when(kapusta.getPriceRoznica()).thenReturn(5);
        when(kapusta.getName()).thenReturn("Капуста белокаченная");

        Map<Ingredient, Integer> ingredienti = new HashMap<>();
        ingredienti.put(cheese, 20);
        ingredienti.put(pomidori, 100);
        ingredienti.put(kapusta, 50);
        strava.setIngredienti(ingredienti);
    }


    // Todo сделать этот тест параметризированым и проверить на граничные значения
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
        dopIngredient.put(kapusta, 10); // 10 * 5
        strava.setDopolnitelnieIngredienti(dopIngredient);
        assertEquals(330, strava.poschitatStoimostDliaKlienta());

//        dopIngredient.put(pomidori, -25); // 3 * 10
//        System.out.println(strava.poschitatStoimostDliaKlienta());
        verify(pomidori).getPriceRoznica();
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
        assertTrue(strava.pokazatIngredienti().containsKey(pomidori)
                && strava.pokazatIngredienti().get(pomidori) == 100);
    }
}