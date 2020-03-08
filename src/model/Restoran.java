package model;

import java.util.*;
import java.util.stream.Collectors;

public class Restoran {
    private String name;
    private List<Posetitel> worker = new ArrayList<>();
    private Set<Strava> menu = new HashSet<>();
    private List<Zakaz> zakazi = new ArrayList<>();

    public Restoran(String name) {
        this.name = name;
    }

    public List<Strava> naitiStraviPoImeni(String name) {
        return menu.stream()
                .filter(w -> w.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Set<Strava> dobavitStraviVMenu(Strava... strava) {
        this.menu.addAll(Arrays.asList(strava));
        return this.menu;
    }

    public Zakaz dobavitZakaz(Zakaz zakaz) {
        this.zakazi.add(zakaz);
        return zakaz;
    }

    public List<Zakaz> naitiZakaziPoPolzovatelu(Posetitel posetitel){
        return zakazi.stream().filter(zakaz -> zakaz.getPosetitel().equals(posetitel)).collect(Collectors.toList());
    }

    public List<Zakaz> naitiZakaziPoStatusu(StatusZakaza statusZakaza){
        return zakazi.stream().filter(zakaz->zakaz.getStatus().equals(statusZakaza)).collect(Collectors.toList());
    }



    public Restoran setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Strava> getMenu() {
        return menu;
    }

    public List<Zakaz> getZakazi() {
        return zakazi;
    }

    public List<Zakaz> getZakazi(Posetitel worker) {
        if(worker instanceof Director)
            return zakazi;
        else {
            System.out.println("go away");
            return null;
        }
    }

    public String getName() {
        return name;
    }


}
