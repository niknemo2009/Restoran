package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PovarTest {

    @Mock
    Restoran restoran;
    @Mock
    Zakaz zakaz1, zakaz2, zakaz3;
    @Mock
    Strava salatLeto, salatOsen, ovoshiGril;

    Povar povar;
    List<Zakaz> zakaziList = new ArrayList<>();

    {
        MockitoAnnotations.initMocks(this);
        povar = new Povar("povar", "pass");

        zakaziList.add(zakaz1);
        zakaziList.add(zakaz2);
        zakaziList.add(zakaz3);

        Mockito.when(restoran.getZakazi()).thenReturn(zakaziList);
    }

    @BeforeEach
    public void beforeEachSetup(){}


    @Test
    void sortirovatZakaziPoStatusu() {

        zakaz1.setStatus(StatusZakaza.RAZMESHEN);
        zakaz2.setStatus(StatusZakaza.VIPOLNIAETSIA);
        zakaz3.setStatus(StatusZakaza.VIPOLNEN);

        assertEquals(zakaz1, povar.sortirovatZakaziPoStatusu(StatusZakaza.RAZMESHEN));


//        return this.restoran.getZakazi()
//                .stream()
//                .filter(zakaz -> zakaz.getStatus() == statusZakaza)
//                .collect(Collectors.toList());

    }

    @Test
    void sozdatStravu() {
    }

    @Test
    void dobavitStravuVMenu() {
    }

    @Test
    void posmotretZakaziSoStatusomRazmeshen() {
    }
}