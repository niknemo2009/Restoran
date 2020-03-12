package model;

import java.util.*;
import java.util.stream.Collectors;

public class Restoran implements IRestoran{
    private String name;
    private List<Klient> workers = new ArrayList<>();
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

    public Set<Strava> dobavitStraviVMenu(Worker povar, Strava... strava) {
        if(this.workers.contains(povar) && povar instanceof Povar) {
            this.menu.addAll(Arrays.asList(strava));
            return this.menu;
        }else{
            throw new UnsupportedOperationException("Заказы могут добавлять только повара");
        }
    }

    public Zakaz dobavitZakaz(Zakaz zakaz) {
        this.zakazi.add(zakaz);
        return zakaz;
    }

    public List<Zakaz> naitiZakaziPoPolzovatelu(Klient klient){
        return zakazi.stream().filter(zakaz -> zakaz.getKlient().equals(klient)).collect(Collectors.toList());
    }

    public List<Zakaz> naitiZakaziPoStatusu(Worker worker, StatusZakaza statusZakaza){
        return zakazi.stream().filter(zakaz->zakaz.getStatus().equals(statusZakaza)).collect(Collectors.toList());
    }

    public int getNomerPoslednegoZakaza(){
        return this.zakazi.size()+1;
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

    public List<Zakaz> getZakazi(Worker worker) {
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
