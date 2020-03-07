package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.DirectoryStream;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
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

        Povar povar = new Povar("login", "pass");
        povar.setRestoran(restoran);
        Oficiant oficiant1 = new Oficiant("login", "pass");

        Posetitel posetitel1 = new Posetitel("login", "pass");

        Ingredient maslo = new Ingredient(5, 10).setId("maslo123").setName("Maslo Rastitelnoe");
        Ingredient kapusta = new Ingredient(2, 4).setId("kapustaF654").setName("kapusta belokachannaya");
        Ingredient pomidori = new Ingredient(3, 5).setId("pomi_FX_087").setName("Pomidor Ukraina");
        Ingredient morkovka = new Ingredient(5, 10).setId("1234FFF").setName("morkva garna");

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

        posetitel1.dobavitStravuVkorzinu(salatOsen, salatOsen, salatOsen); // Price 60
        Zakaz zakaz1 = posetitel1.razmestitZakaz(restoran);

        posetitel1.dobavitStravuVkorzinu(salatLeto); // Price 10
        Zakaz zakaz2 = posetitel1.razmestitZakaz(restoran);

        posetitel1.dobavitStravuVkorzinu(ovoshiGril, salatLeto); // Price 40
        Zakaz zakaz3 = posetitel1.razmestitZakaz(restoran);

        posetitel1.dobavitStravuVkorzinu(salatOsen);
        Zakaz zakaz4 = posetitel1.razmestitZakaz(restoran);
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