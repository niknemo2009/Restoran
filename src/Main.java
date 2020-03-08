import model.*;
import tools.FilesProcessor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<Ingredient> dobavitIngredientiIzFaila(String filename) {
        List<Ingredient> result = new ArrayList<>();
        List<List<String>> listFromFile = FilesProcessor.readFileAsList(filename);
        for (List<String> list : listFromFile) {
            result.add((new Ingredient.Builder()).name(list.get(1)).priceRoznica(4).build());
        }
        return result;
    }

    public static void main(String[] args) {
        LocalDate from = LocalDate.of(1999, 1, 1);
        LocalDate to = LocalDate.of(2021, 1, 1);

        Restoran restoran = new Restoran("Restoranchik");
        Director director = new Director("login", "pass");
        Povar povar = new Povar("login", "pass");
        povar.setRestoran(restoran);
        Oficiant oficiantVasia = new Oficiant("login", "pass");
        Posetitel posetitel1 = new Posetitel("loginUser", "passUser");
        List<Strava> strava1 = new ArrayList<>();

        List<Ingredient> ingredienti = dobavitIngredientiIzFaila("src/resources/ingredienti");

        Strava salatCesar = new Strava("Салат Цезарь", 250)
                .izmenitIngredientIliDobavitNovij(ingredienti.get(0), 100)
                .izmenitIngredientIliDobavitNovij(ingredienti.get(1), 80)
                .izmenitIngredientIliDobavitNovij(ingredienti.get(2), 80);
        Strava salatLeto = new Strava("Салат Leto", 100)
                .izmenitIngredientIliDobavitNovij(ingredienti.get(0), 200).
                izmenitIngredientIliDobavitNovij(ingredienti.get(2), 100);

        restoran.dobavitStraviVMenu(salatCesar, salatLeto);

        posetitel1.posmotretMenu(restoran);

        strava1 = restoran.naitiStraviPoImeni("Салат");
        System.out.println(strava1);

        posetitel1.dobavitStravuVkorzinu(strava1.get(0), strava1.get(1));

        Zakaz zakaz = posetitel1.razmestitZakaz(restoran);
        System.out.println("первое размещение заказа posetitel1.razmestitZakaz " + zakaz);
        posetitel1.dobavitStravuVZakaz(zakaz, strava1.get(0));
        System.out.println(" Заказ после добавления стравы в заказ посетителем" + zakaz);

        zakaz.dobavitStravuVZakaz(strava1.get(0));
        System.out.println();

        int stoimostZakaza = zakaz.poschitatStoimostZakaza();
        System.out.println("Status zakaza " + zakaz.getStatus());

        System.out.println("zakaz = " + zakaz + "\n Stoimost zakaza = " + stoimostZakaza);

        System.out.println("Spisok vseh zakazov: "+ restoran.getZakazi());
        System.out.print("Список заказов со статусом размещен = ");
        System.out.println(povar.sortirovatZakaziPoStatusu(StatusZakaza.RAZMESHEN));

        System.out.println("Restoran getZakazi = " + restoran.getZakazi());

        System.out.println("сумма заказов = " + zakaz.poschitatStoimostZakaza());
        System.out.println("сумма заказов по статистике директора = " + director
                .skolkoZarabotanoZaPeriod(restoran, from, to));
        System.out.println("Список всех заказов director.pokazatVseZakazi(restoran) " + director.pokazatVseZakazi(restoran));
//        naitiNaiboleePopularnijZakazZaPeriod
        System.out.println("Наиболее популярное блюдо за период " +
                director.naitiNaiboleePopularnuyuStravuZaPeriod(restoran, from, to));
        System.out.print("Статистика обслуживания за период - " );
        director.proveritKachestvoObslugivaniyaPoDate(restoran, from, to);

    }
}
