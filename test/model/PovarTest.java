package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reusableMocks.UnitTestContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class PovarTest {

    UnitTestContext unitTestContext = new UnitTestContext();
    Restoran restoran;
    Zakaz zakaz1, zakaz2, zakaz3;
    Strava salatLeto, salatOsen, ovoshiGril;

    Povar povar;
    List<Zakaz> zakaziList = new ArrayList<>();

    {
        restoran = unitTestContext.restoran;
        zakaz1 = unitTestContext.zakaz1;
        zakaz2 = unitTestContext.zakaz2;
        zakaz3 = unitTestContext.zakaz3;

        povar = new Povar("povar", "pass", restoran);

        zakaziList.add(zakaz1);
        zakaziList.add(zakaz2);
        zakaziList.add(zakaz3);
    }

    @BeforeEach
    public void beforeEachSetup(){}

    @Test
    void sortirovatZakaziPoStatusu() {
        assertAll(
                ()->povar.sortirovatZakaziPoStatusu(StatusZakaza.RAZMESHEN).contains(zakaz1),
                ()->povar.sortirovatZakaziPoStatusu(StatusZakaza.VIPOLNIAETSIA).contains(zakaz2),
                ()->povar.sortirovatZakaziPoStatusu(StatusZakaza.VIPOLNEN).contains(zakaz3)
        );

    }

    @Test
    void sozdatStravu() {
        Strava result = povar.sozdatStravu("salat ogurechniy", 250);
        assertTrue(result instanceof  Strava);
        assertEquals("salat ogurechniy", result.getName());
    }

    @Test
    void dobavitStravuVMenu() {

    }

    @Test
    void posmotretZakaziSoStatusomRazmeshen() {
        assertTrue(povar.posmotretZakaziSoStatusomRazmeshen().contains(zakaz1));
    }
}