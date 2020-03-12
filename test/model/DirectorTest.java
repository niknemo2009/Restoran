package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import testMaintenanceClasses.IndicativeSentences;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.LocalDate;
import java.util.*;


@DisplayNameGeneration(IndicativeSentences.class)
class DirectorTest {
    private LocalDate from = LocalDate.of(1999, 1, 1);
    private  LocalDate to = LocalDate.of(2021, 1, 1);

    private static Restoran restoran;
    private static Director director;
    private static Povar povar;
    private static Oficiant oficiant1;
    private static Klient klient1;

    private static Strava salatLeto, salatOsen, ovoshiGril;
    private static Ingredient maslo, kapusta, pomidori, morkovka;

    private static List<Strava> stravi;
    private static Zakaz zakaz1, zakaz2, zakaz3, zakaz4;


    @BeforeAll
    public static void setup() {
        restoran = new Restoran("Test Restoran");
        director = new Director("login", "pass");

        povar = new Povar("login", "pass");
        oficiant1 = new Oficiant("login", "pass");

        klient1 = new Klient("login", "pass");

        maslo = (new Ingredient.Builder()).name("Maslo Rastitelnoe").priceRoznica(10).build();
        kapusta = (new Ingredient.Builder()).name("kapusta belokachannaya").priceRoznica(4).build();
        pomidori = (new Ingredient.Builder()).name("Pomidor Ukraina").priceRoznica(5).build();
        morkovka = (new Ingredient.Builder()).name("morkva garna").priceRoznica(10).build();

        salatLeto = new Strava("salat Leto", 10)
                .izmenitIngredientIliDobavitNovij(maslo, 20)
                .izmenitIngredientIliDobavitNovij(kapusta, 80)
                .izmenitIngredientIliDobavitNovij(morkovka, 30);
        salatOsen = new Strava("salat Osen", 20)
                .izmenitIngredientIliDobavitNovij(kapusta, 45)
                .izmenitIngredientIliDobavitNovij(pomidori, 50)
                .izmenitIngredientIliDobavitNovij(maslo, 20);
        ovoshiGril = new Strava("Ovoshi Gril", 30)
                .izmenitIngredientIliDobavitNovij(pomidori, 70)
                .izmenitIngredientIliDobavitNovij(kapusta, 40)
                .izmenitIngredientIliDobavitNovij(morkovka, 30);

        povar.dobavitStravuVMenu(restoran, salatLeto);
        povar.dobavitStravuVMenu(restoran, salatOsen);
        povar.dobavitStravuVMenu(restoran, ovoshiGril);
    }

    @BeforeEach
    public void setupInitialDataForEachMethod(){
        restoran.getZakazi().clear();

        klient1.dobavitStravuVkorzinu(restoran, salatOsen, salatOsen, salatOsen); // Price 60
        zakaz1 = klient1.razmestitZakaz(restoran);

        klient1.dobavitStravuVkorzinu(restoran, salatLeto); // Price 10
        zakaz2 = klient1.razmestitZakaz(restoran);

        klient1.dobavitStravuVkorzinu(restoran, ovoshiGril, salatLeto); // Price 40
        zakaz3 = klient1.razmestitZakaz(restoran);

        klient1.dobavitStravuVkorzinu(restoran, salatOsen);
        zakaz4 = klient1.razmestitZakaz(restoran);
        zakaz4.setDate(LocalDate.of(1980, 1, 1));
    }

    /**
     * ToDo добавление в корзину никак не завязано на конкретном ресторане. Тут надо придумать разные варианты моделей и понять
     * как вообще будет завязана логика взаимодействия ресторанов.
     * сейас клиент может зайти в другой ресторан и оформить заказ в нем, даже если там нет таких блюд.
     */
    @Test
    public void test_pokazatVseZakazi_should_return_a_list_of_all_orders() {
        List<Zakaz> result = director.pokazatVseZakazi(restoran);
        assertAll(
                () -> assertTrue(result.contains(zakaz1)),
                () -> assertTrue(result.contains(zakaz2)),
                () -> assertTrue(result.contains(zakaz3))
        );
    }

    @Test
    public void test_naitiNaiboleePopularnuyuStravuZaPeriod_dolgen_naiti_stravu_salatOsen() {
        Map<Strava, Integer> stravaIntegerMap = director.naitiNaiboleePopularnuyuStravuZaPeriod(restoran, from, to);
        assertTrue(stravaIntegerMap.containsKey(salatOsen));
    }

    @Test
    public void test_proveritKachestvoObslugivaniyaPoDate_vivodit_spisok_zakazov_s_ocenkami_za_ukazanniy_period_zakazi_eshe_ne_bili_oceneni() {
        Map<Zakaz, Integer> result = director.proveritKachestvoObslugivaniyaPoDate(restoran, from, to);
        Map<Zakaz, Integer> expectedMapZakazovSOcenkami = new TreeMap<>();
        expectedMapZakazovSOcenkami.put(zakaz1, 0);
        expectedMapZakazovSOcenkami.put(zakaz2, 0);
        expectedMapZakazovSOcenkami.put(zakaz3, 0);
        assertEquals(expectedMapZakazovSOcenkami, result);
    }

    @Test
    public void test_proveritKachestvoObslugivaniyaPoDate_vivodit_spisok_zakazov_s_ocenkami_za_ukazanniy_period_zakazi_bili_oceneni() {
        klient1.ocenitObslugivanie(zakaz1, 5);
        klient1.ocenitObslugivanie(zakaz2, 4);
        klient1.ocenitObslugivanie(zakaz3, 3);
        Map<Zakaz, Integer> result = director.proveritKachestvoObslugivaniyaPoDate(restoran, from, to);
        Map<Zakaz, Integer> expected = new TreeMap<>();
        expected.put(zakaz1, 5);
        expected.put(zakaz2, 4);
        expected.put(zakaz3, 3);
        assertEquals(expected, result);
    }


    @Test
    public void test_naitiZakaziZaPeriod_dolgen_naiti_zakaz4() {
        assertEquals(zakaz4, director
                .naitiZakaziZaPeriod(restoran, LocalDate.of(1979, 1, 1), LocalDate.of(1981, 1, 1))
                .get(0));
    }

    @Test
    public void test_skolkoZarabotanoZaPeriod_dolgen_poschitat_summu_vseh_zakazov_s_dopolnitelnimi_ingredientami() {
        int result = director.skolkoZarabotanoZaPeriod(restoran, from, to);
        assertEquals(110, result);
    }

    @Test
    public void test_skolkoZarabotanoZaPeriod_dolgen_poschitat_dopolnitelnie_ingredienti(){
        Zakaz zakazTMP = zakazSDopIngredientami(klient1);
        int result = director.skolkoZarabotanoZaPeriod(restoran, from, to);
        // итого должно выйти доп ингредиентов 500 + 2000 + предыдуцие заказы 110 + заказ из ф-ии  3*30
        assertEquals(500+2000+110+90, result);
    }



    public Zakaz zakazSDopIngredientami(Posetitel podopitnij){
        Map<Ingredient, Integer> dopIngredientiVStravu = new HashMap<>();
        dopIngredientiVStravu.put(pomidori, 100); // 50 * 100 = 500
        dopIngredientiVStravu.put(morkovka, 200); // 10 *200 = 2000 цены кошмар конечно
        // всего заказов продано ранее на 110 + заказ на 30 + доп ингредиенты
        podopitnij.dobavitStravuVkorzinu(restoran, podopitnij.dobavitDopIngredienti(ovoshiGril, dopIngredientiVStravu));
        // добавили еще 2 блюда на 60 в сумме
        podopitnij.dobavitStravuVkorzinu(restoran, ovoshiGril, ovoshiGril);
        return podopitnij.razmestitZakaz(restoran);
    }


    /**
     * Тесты ф-й, который Директор унаследовал от Posetitel
     * */

    @Test
    public void test_Posmotret_menu_dolgen_sodergat_3_Stravi(){
        Set<Strava> result = director.posmotretMenu(restoran);
        assertAll(
                ()->assertTrue(result.contains(salatLeto)),
                ()->assertTrue(result.contains(salatOsen)),
                ()->assertTrue(result.contains(ovoshiGril))
        );
        System.out.println(result);
    }

    
}