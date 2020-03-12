package model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Povar extends Supervisor {

    public Povar(String login, String pass) {
        super(login, pass);
    }

    public Strava sozdatStravu(String name, int price){
        return new Strava(name, price);
    }

    public Set<Strava> dobavitStravuVMenu(IRestoran restoran, Strava strava){
        return restoran.dobavitStraviVMenu(this, strava);
    }


}
