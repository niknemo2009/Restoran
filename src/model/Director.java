package model;

import java.time.LocalDate;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class Director extends Posetitel {

    // Todo решить вопрос разграничения по ресторанам и наследования директора

    public List<Zakaz> pokazatVseZakazi2(Restoran restoran) {
        return restoran.getZakazi(this);
    }

    public Director(String login, String pass) {
        super(login, pass);
    }

    public List<Zakaz> pokazatVseZakazi(Restoran restoran) {
        return restoran.getZakazi();
    }

    public Map<Strava, Integer> naitiNaiboleePopularnuyuStravuZaPeriod(Restoran restoran, LocalDate from, LocalDate to) {
        Map<Strava, Integer> temp = new HashMap<>();
        Map<Strava, Integer> result = new HashMap<>();
        List<Zakaz> zakazi = naitiZakaziZaPeriod(restoran, from, to);
        zakazi.stream().map(zakaz -> zakaz.getListStrav().entrySet()).forEach(entry -> {
            entry.forEach(entryItem -> {
                if (temp.containsKey(entryItem.getKey())) {
                    temp.put(entryItem.getKey(), temp.get(entryItem.getKey()) + entryItem.getValue());
                } else {
                    temp.put(entryItem.getKey(), entryItem.getValue());
                }
            });
        });
        Optional<Map.Entry<Strava, Integer>> optionalZakaz = temp.entrySet().stream().max(Map.Entry.comparingByValue());
        optionalZakaz.ifPresent(entry -> result.put(entry.getKey(), entry.getValue()));
        return result;
    }

    // return a Map of Orders with the corresponding croes
    public Map<Zakaz, Integer> proveritKachestvoObslugivaniyaPoDate(Restoran restoran, LocalDate from, LocalDate to) {
        Map<Zakaz, Integer> result = new TreeMap<>();
        List<Zakaz> zakazi = naitiZakaziZaPeriod(restoran, from, to);
        zakazi.forEach(zakaz -> {
            System.out.print("  Заказ номер - " + zakaz.getNomerZakaza() + "   Оценка - " + zakaz.getOcenkaPosetitelia());
            result.put(zakaz, zakaz.getOcenkaPosetitelia());
        });
        return result;
    }

    public List<Zakaz> naitiZakaziZaPeriod(Restoran restoran, LocalDate from, LocalDate to) {
//        return naitiZakaziPoUsloviyu(restoran, z -> z.getDate().isAfter(from) && z.getDate().isBefore(to));
        return naitiZakaziPoUsloviyu(restoran, getPredicatePoDate(from, to));
    }

    public int skolkoZarabotanoZaPeriod(Restoran restoran, LocalDate from, LocalDate to) {
        int[] result = new int[1];
        List<Zakaz> zakazi = naitiZakaziZaPeriod(restoran, from, to);
        zakazi.stream().map(Zakaz::poschitatStoimostZakaza).forEach(i -> result[0] += i);
        return result[0];
    }


    private Predicate<Zakaz> getPredicatePoDate(LocalDate from, LocalDate to) {
        return (z) -> z.getDate().isAfter(from) && z.getDate().isBefore(to);
    }

    private List<Zakaz> naitiZakaziPoUsloviyu(Restoran restoran, Predicate<Zakaz> predicate) {
        return restoran.getZakazi().stream().
                filter(predicate).
                collect(Collectors.toList());
    }
}
