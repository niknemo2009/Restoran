package model;

import java.time.LocalDate;
import java.util.*;

public class Posetitel {
    private String login, pass;
    private Map<Strava, Integer> korzina = new HashMap<>();

    public Posetitel(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public List<Zakaz> posmotretSvoiZakazi(Restoran restoran) {
        return restoran.naitiZakaziPoPolzovatelu(this);
    }

    public Zakaz ocenitObslugivanie(Zakaz zakaz, int ocenka) {
        if (zakaz.getPosetitel() == this) {
            return zakaz.setOcenkaPosetitelia(ocenka);
        } else {
            throw new UnsupportedOperationException("Заказ может быть ощенен только пользователем, который его разместил");
        }
    }

    /**
     * мне кажется, нужно явно создавать новый объект, иначе изменения ингредиентов затронут Страву в Меню
     */
    public Strava dobavitDopIngredienti(Strava strava, Map<Ingredient, Integer> dopolnitelnieIngredienti) {
        Strava result = new Strava(strava.getName(), strava.getPrice()).setIngredienti(strava.pokazatIngredienti());
        result.setDopolnitelnieIngredienti(dopolnitelnieIngredienti);
        return result;
    }

    public Zakaz razmestitZakaz(Restoran restoran) throws UnsupportedOperationException {
        if (this.korzina != null && this.korzina.size() > 0) {
            Zakaz result = new Zakaz(String.valueOf(restoran.getZakazi().size() + 1), this, korzina);
            result = restoran.dobavitZakaz(result);
            korzina.clear();
            return result;
        } else {
            throw new UnsupportedOperationException("Empty shopping cart");
        }
    }

    public Map<Strava, Integer> dobavitStravuVkorzinu(Strava... strava) {
        for (Strava s : strava) {
            if (korzina.containsKey(s)) {
                korzina.put(s, korzina.get(s) + 1);
            } else {
                korzina.put(s, 1);
            }
        }
        return korzina;
    }

    public Zakaz dobavitStravuVZakaz(Zakaz currentZakaz, Strava... stravai) {
        currentZakaz.dobavitStravuVZakaz(stravai);
        return currentZakaz;
    }

    public Set<Strava> posmotretMenu(Restoran restoran) {
        return restoran.getMenu();
    }

    public Map<Strava, Integer> getKorzina() {
        return korzina;
    }
}
