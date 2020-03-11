package model;

import java.util.List;
import java.util.Set;

public interface IRestoran {

    public List<Strava> naitiStraviPoImeni(String name);
    public Set<Strava> dobavitStraviVMenu(Povar povar, Strava... strava);
    public Zakaz dobavitZakaz(Zakaz zakaz);
    public List<Zakaz> naitiZakaziPoPolzovatelu(Klient klient);
    public List<Zakaz> naitiZakaziPoStatusu(Worker worker, StatusZakaza statusZakaza);

    public Set<Strava> getMenu();

    public List<Zakaz> getZakazi();

    public List<Zakaz> getZakazi(Klient klient);

    public String getName();
}
