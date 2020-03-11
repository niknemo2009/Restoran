package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DirectorTestMockito {

    private static Restoran restoran = mock(Restoran.class);
    private static Director director;
    private static List<Zakaz> vseZakazi = new ArrayList<>();

    @BeforeAll
    public static void setup() {
        // restoran = new Restoran("Test Restoran");
        director = new Director("login", "pass");

        Povar povar = new Povar("login", "pass", restoran);
        povar.setRestoran(restoran);
        Oficiant oficiant1 = new Oficiant("login", "pass");

        Klient klient1 = new Klient("login", "pass");

        Ingredient maslo = (new Ingredient.Builder()).name("Maslo Rastitelnoe").priceRoznica(10).build();
        Ingredient kapusta = (new Ingredient.Builder()).name("kapusta belokachannaya").priceRoznica(4).build();
        Ingredient pomidori = (new Ingredient.Builder()).name("Pomidor Ukraina").priceRoznica(5).build();
        Ingredient morkovka = (new Ingredient.Builder()).name("morkva garna").priceRoznica(10).build();

        Strava salatLeto = new Strava("salat Leto", 10)
                .izmenitIngredientIliDobavitNovij(maslo, 20)
                .izmenitIngredientIliDobavitNovij(kapusta, 80)
                .izmenitIngredientIliDobavitNovij(morkovka, 30);
        Strava salatOsen = new Strava("salat Osen", 20)
                .izmenitIngredientIliDobavitNovij(kapusta, 45)
                .izmenitIngredientIliDobavitNovij(pomidori, 50)
                .izmenitIngredientIliDobavitNovij(maslo, 20);
        Strava ovoshiGril = new Strava("Ovoshi Gril", 30)
                .izmenitIngredientIliDobavitNovij(pomidori, 70)
                .izmenitIngredientIliDobavitNovij(kapusta, 40)
                .izmenitIngredientIliDobavitNovij(morkovka, 30);

        povar.dobavitStravuVMenu(salatLeto);
        povar.dobavitStravuVMenu(salatOsen);
        povar.dobavitStravuVMenu(ovoshiGril);

        klient1.dobavitStravuVkorzinu(salatOsen, salatOsen, salatOsen); // Price 60
        Zakaz zakaz1 = klient1.razmestitZakaz(restoran);

        klient1.dobavitStravuVkorzinu(salatLeto); // Price 10
        Zakaz zakaz2 = klient1.razmestitZakaz(restoran);

        klient1.dobavitStravuVkorzinu(ovoshiGril, salatLeto); // Price 40
        Zakaz zakaz3 = klient1.razmestitZakaz(restoran);

        klient1.dobavitStravuVkorzinu(salatOsen);
        Zakaz zakaz4 = klient1.razmestitZakaz(restoran);
        zakaz4.setDate(LocalDate.of(1980, 1, 1));

        vseZakazi = Arrays.asList(zakaz1, zakaz2, zakaz3, zakaz4);

        List<Zakaz> zakazList = Arrays.asList(zakaz1, zakaz2, zakaz3, zakaz4);
        when(restoran.getName()).thenReturn("Test Restoran");
        when(restoran.getZakazi()).thenReturn(zakazList);

        Set<Strava> menu = new HashSet<>();
        menu.add(ovoshiGril);
        when(restoran.dobavitStraviVMenu(ovoshiGril)).thenReturn(menu);
        List<Strava> naidennieStravi = new ArrayList<>();
        naidennieStravi.add(ovoshiGril);
        when(restoran.naitiStraviPoImeni("Ovoshi Gril")).thenReturn(naidennieStravi);

    }

    @Test
    void pokazatVseZakazi() {

        // List<Zakaz>  factResult=director.naitiZakaziZaPeriod(restoran, LocalDate.of(1990,1,1), LocalDate.of(2022,1,1));
        //assertIterableEquals(factResult,restoran.getZakazi());
    }

    @Test
    void naitiNaiboleePopularnuyuStravuZaPeriod() {
    }

    @Test
    void proveritKachestvoObslugivaniyaPoDate() {
    }

    @Test
    void naitiZakaziZaPeriod() {
    }

    @Test
    void skolkoZarabotanoZaPeriod() {
    }
}