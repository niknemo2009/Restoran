package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ZakazTest {

    private static Zakaz zakaz;

    //    private  Posetitel posetitel = Mockito.mock(Posetitel.class);
//    private Ingredient ingredient = Mockito.mock(Ingredient.class);
//    private Strava salatLeto = Mockito.mock(Strava.class);
    @Mock
    private Klient klient;
    @Mock
    private Ingredient ingredient;
    @Mock
    private Strava salatLeto;
    private Map<Strava, Integer> korzina;

    {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    static void setup() {

    }

    @BeforeEach
    public void setupBeforeEach() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        korzina = new HashMap<>();
        korzina.put(salatLeto, 1);

        when(salatLeto.getPrice()).thenReturn(50);
        when(salatLeto.poschitatStoimostDliaKlienta()).thenReturn(50);
        when(salatLeto.getDopolnitelnieIngredienti()).thenReturn(new HashMap<>());
        when(klient.getKorzina()).thenReturn(korzina);

        zakaz = new Zakaz("777", klient, klient.getKorzina());
    }

    @Test
    void poschitatStoimostZakaza() {
        assertEquals(50, zakaz.poschitatStoimostZakaza());
        Mockito.verify(salatLeto, Mockito.atLeast(1)).poschitatStoimostDliaKlienta();
    }

    @Test
    void dobavitStravuVZakaz_dolgen_izmenit_kolichestvo_stravi() {
        zakaz.dobavitStravuVZakaz(salatLeto);
        zakaz.dobavitStravuVZakaz(salatLeto);
        assertEquals(3, zakaz.getListStrav().get(salatLeto));
    }

    @Test
    void getOcenkaPosetitelia() {
    }

    @Test
    void setOcenkaPosetitelia() {
    }

    @Test
    void setDate() {
    }

    @Test
    void compareTo() {
    }

    @Test
    void testEquals() {
    }
}