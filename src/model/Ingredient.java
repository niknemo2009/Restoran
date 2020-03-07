package model;

public class Ingredient implements Comparable<Ingredient>{
    String name, id;
    EDINICA_IZMERENIYA edinicaIzmereniya;
    int priceZakupka;
    int priceRoznica;

    // todo убрать возвр значение из сеттеров и реалиховать билдер
    // niknemo2009
    public Ingredient(int priceZakupka, int priceRoznica) {
        this.priceZakupka = priceZakupka;
        this.priceRoznica = priceRoznica;
    }

    public int getPriceZakupka() {
        return priceZakupka;
    }

    public int getPriceRoznica() {
        return priceRoznica;
    }

    public Ingredient setPriceZakupka(int priceZakupka) {
        this.priceZakupka = priceZakupka;
        return this;
    }

    public Ingredient setPriceRoznica(int priceRoznica) {
        this.priceRoznica = priceRoznica;
        return this;
    }

    public Ingredient setName(String name) {
        this.name = name;
        return this;
    }

    public Ingredient setId(String id) {
        this.id = id;
        return this;
    }

    public Ingredient setEdinicaIzmereniya(EDINICA_IZMERENIYA edinicaIzmereniya) {
        this.edinicaIzmereniya = edinicaIzmereniya;
        return this;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Ingredient ingredient) {

        return this.name.compareTo(((Ingredient)ingredient).getName());
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Ingredient)) {
            return false;
        }

        Ingredient ingredient = (Ingredient) o;
        return ingredient.getName().equals(this.name);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode() + id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                '}';
    }
}
