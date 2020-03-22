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
//    public List<Zakaz> ocenitObslugivanie(Restoran restoran, int numZakaz, int ocenka) {
//        List<Zakaz>  result=new ArrayList<>();
//        Zakaz  temp = restoran.findZakazByNumber(numZakaz, this);
//        if (temp.getPosetitel() == this) {
//            return zakaz.setOcenkaPosetitelia(ocenka);
//        }
//        int i=0;
////      try{
////          while (true){
////              System.out.println(result.get(i));
////              i++;
////          }
////      }catch (IndexOutOfBoundsException e){
////
////      }
//
//        return result;
//    }

    public List<Zakaz> ocenitObslugivanie(Restoran restoran, String numZakaza, int ocenka){
        List<Zakaz> result = restoran.findZakazByNumber(numZakaza, this);
        for (Zakaz z : result) {
            if(z.getPosetitel() == this){
                z.setOcenkaPosetitelia(ocenka);
            }
        }
        return result;
    }
    /**
     * мне кажется, нужно явно создавать новый объект, иначе изменения ингредиентов затронут Страву в Меню
     */
    public Strava dobavitDopIngredienti(Strava strava, Map<Ingredient, Integer> dopolnitelnieIngredienti) {
        Strava result = new Strava(strava.getName(), strava.getPrice()).setIngredienti(strava.pokazatIngredienti());
        result.setDopolnitelnieIngredienti(dopolnitelnieIngredienti);
        return result;
    }

    /**
     * @Param String returns order number
     * */
    public String razmestitZakaz(Restoran restoran) {
        String result = "";
        if (this.korzina.size() > 0) {
            Zakaz zakaz = new Zakaz(restoran,this, korzina);
            if(restoran.dobavitZakaz(zakaz)){
                result = zakaz.getNomerZakaza();
            }
        }
        return result;
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
