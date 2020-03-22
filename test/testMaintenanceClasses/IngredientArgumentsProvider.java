package testMaintenanceClasses;

import model.Ingredient;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IngredientArgumentsProvider {


    public static Stream<Arguments> smallerIngredients() {

        List<Ingredient> ingredientList = Arrays.asList(
                (new Ingredient.Builder().name("rСыр тертый").build()),
                (new Ingredient.Builder().name("TСыр тертый").build()),
                (new Ingredient.Builder().name("123Сыр тертый").build()),
                (new Ingredient.Builder().name(".,,.Сыр тертый").build()),
                (new Ingredient.Builder().name(" Сыр тертый").build()),
                (new Ingredient.Builder().name("Сыр  тертый").build()),

                (new Ingredient.Builder().name("Рыр тертый").build()),
                (new Ingredient.Builder().name("4ыр тертый").build()),
                (new Ingredient.Builder().name("Sыр тертый").build()),

                (new Ingredient.Builder().name("Сыр терты").build()),
                (new Ingredient.Builder().name("Сыр терты123").build()),
                (new Ingredient.Builder().name("Сыр тертыqwe").build()),

                (new Ingredient.Builder().name("qwe").build()),
                (new Ingredient.Builder().name("QWE").build()),
                (new Ingredient.Builder().name("123").build()),
                (new Ingredient.Builder().name(" ").build()));

        return ingredientList.stream().map((line) -> {
            return Arguments.arguments(line);
        });
    }

    public static Stream<Arguments> greaterIngredients() {
        List<Ingredient> ingredientList = Arrays.asList(
                (new Ingredient.Builder().name("Сыр тертыйй").build()),
                (new Ingredient.Builder().name("Сыр тертый1").build()),
                (new Ingredient.Builder().name("Сыр тертыйq").build()),
                (new Ingredient.Builder().name("Сыр тертый./").build()),
                (new Ingredient.Builder().name("Сыр тертый ").build()),
                (new Ingredient.Builder().name("Сыр тертый фвыа").build()),
                (new Ingredient.Builder().name("Тыр тертый").build())
        );


        return ingredientList.stream().map((line) -> {
            return Arguments.arguments(line);
        });
    }


}
