package model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Povar extends Posetitel{
    Restoran restoran;

    public Povar(String login, String pass) {
        super(login, pass);
    }

    public List<Zakaz> sortirovatZakaziPoStatusu(StatusZakaza statusZakaza){
        return this.restoran.getZakazi()
                .stream()
                .filter(zakaz -> zakaz.getStatus() == statusZakaza)
                .collect(Collectors.toList());
    }

    public Strava sozdatStravu(String name, int price){
        return new Strava(name, price);
    }

    public Set<Strava> dobavitStravuVMenu(Strava strava){
        return restoran.dobavitStraviVMenu(strava);
    }

    public List<Zakaz> posmotretZakaziSoStatusomRazmeshen(Restoran restoran){
        return restoran.naitiZakaziPoStatusu(StatusZakaza.RAZMESHEN);
    }

    public Povar setRestoran(Restoran restoran) {
        this.restoran = restoran;
        return this;
    }
}
