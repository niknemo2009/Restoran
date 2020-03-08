package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.internal.matchers.Null;
import org.w3c.dom.ls.LSOutput;
import testMaintenanceClasses.IndicativeSentences;
import testMaintenanceClasses.IngredientArgumentsProvider;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(IndicativeSentences.class)
class IngredientTest {

    private static Ingredient subject, ingredient2;

    @BeforeAll
    public static void setup(){
        subject = new Ingredient.Builder().name("Сыр тертый").priceRoznica(20).build();
        ingredient2 = new Ingredient.Builder().name("Грибы свежие").priceRoznica(15).build();
    }

    @Test
    public void comareTo_equal_objects(){
        assertEquals(0, subject.compareTo(subject), "Comparing to itself");
        assertEquals(0, subject.compareTo(new Ingredient.Builder().name("Сыр тертый").priceRoznica(20).build()), "Comparing to new equal object");
    }
    
    @ParameterizedTest
    @MethodSource("testMaintenanceClasses.IngredientArgumentsProvider#smallerIngredients")
    public void compareTo_comparing_to_smaller_objects(Ingredient ingredient){
        assertTrue(subject.compareTo(ingredient) > 0);
    }

    @ParameterizedTest
    @MethodSource("testMaintenanceClasses.IngredientArgumentsProvider#greaterIngredients")
    public void compareTo_comparing_to_greater_objects(Ingredient ingredient){
        assertTrue(subject.compareTo(ingredient) < 0);
    }

    @Test
    void compareTo_comparing_to_Ingredient_with_empty_name(){
        assertTrue(subject.compareTo((new Ingredient.Builder()).name("").build()) > 0);
    }

    @Test
    void compareTo_should_throw_NullPointerException(){
        assertThrows(NullPointerException.class, ()->subject.compareTo((new Ingredient.Builder()).build()));
    }

    @Test
    void testEquals_testing_equal_objects() {
        assertTrue(subject.equals(new Ingredient.Builder().name("Сыр тертый").priceRoznica(20).build()));
        assertTrue(subject.equals(subject));
        
    }
    
    @Test
    void testEquls_should_return_false(){
        assertFalse(subject.equals(ingredient2));
        assertFalse(subject.equals(new Object()));
        assertFalse(subject.equals(new Integer(654)));
        assertFalse(subject.equals(new String("Сыр тертый")));
    }
}