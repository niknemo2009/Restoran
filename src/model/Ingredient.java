package model;

public class Ingredient implements Comparable<Ingredient>{
    private final String name;
    private final int priceRoznica;

    // niknemo2009
    public static class Builder{
        private String name, id;
        private int priceRoznica;

        public Builder() {
        }

        public Ingredient build() {
            return new Ingredient(this);
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder priceRoznica(int priceRoznica){
            this.priceRoznica = priceRoznica;
            return this;
        }
    }

    private Ingredient(Builder builder){
        this.name = builder.name;
        this.priceRoznica = builder.priceRoznica;
    }

    public int getPriceRoznica() {
        return priceRoznica;
    }

    public String getName() {
        return name;
    }

    // todo может проверяться с ингредиетом, у которого имя null, нужно ли в методе compareTo делать проверку на Null параметра name?
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
        result = 31 * result + name.hashCode() + priceRoznica;
        return result;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                '}';
    }
}
