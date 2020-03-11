import model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MainTest {
    static Restoran restoran;
    static Director director;
    static Povar povar;
     static Oficiant oficiant1;
    static Oficiant oficiant2;
    static Klient klient1;
    static Klient klient2;


    @BeforeAll
    public static void setup() {
        restoran = new Restoran("Restoran 1");
        director = new Director("login", "pass");
        povar = new Povar("login", "pass", restoran);
 //        oficiant1 = new Oficiant();
//        oficiant2 = new Oficiant();
//        posetitel1 = new Posetitel(restoran, oficiant1);
//        posetitel2 = new Posetitel(restoran, oficiant2);
    }

    @Test
    public void setter_and_getter_tests_for_Restoran() {
//        restoran.setDirector(director).setPovar(povar).setMenu(menu).dobavitOficianta(oficiant1).dobavitOficianta(oficiant2);
//        assertEquals(director, restoran.getDirector());
//        assertEquals(restoran.getName(), "Restoran 1");
//        assertSame(povar, restoran.getPovar());
//        assertSame(menu, restoran.getMenu());
//        System.out.println(restoran);
    }


}