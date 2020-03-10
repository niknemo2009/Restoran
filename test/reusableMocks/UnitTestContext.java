package reusableMocks;

import model.Restoran;
import model.StatusZakaza;
import model.Strava;
import model.Zakaz;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;

import java.util.ArrayList;
import java.util.List;

public class UnitTestContext
{

    @Mock
    public     Restoran restoran;
    @Mock
    public Zakaz zakaz1, zakaz2, zakaz3;
    @Mock
    public Strava salatLeto, salatOsen, ovoshiGril;

    public UnitTestContext()
    {
        MockitoAnnotations.initMocks(this);

        Mockito.when(zakaz1.getStatus()).thenReturn(StatusZakaza.RAZMESHEN);
        Mockito.when(zakaz2.getStatus()).thenReturn(StatusZakaza.VIPOLNIAETSIA);
        Mockito.when(zakaz3.getStatus()).thenReturn(StatusZakaza.VIPOLNEN);

        List<Zakaz> razmeshennieZakazi = new ArrayList<>();
        razmeshennieZakazi.add(zakaz1);
        List<Zakaz> zakaziVipolniayutsia = new ArrayList<>();
        zakaziVipolniayutsia.add(zakaz2);
        List<Zakaz> vipolnennieZakazi = new ArrayList<>();
        vipolnennieZakazi.add(zakaz3);

        Mockito.when(restoran.naitiZakaziPoStatusu(StatusZakaza.RAZMESHEN)).thenReturn(razmeshennieZakazi);
        Mockito.when(restoran.naitiZakaziPoStatusu(StatusZakaza.VIPOLNIAETSIA)).thenReturn(zakaziVipolniayutsia);
        Mockito.when(restoran.naitiZakaziPoStatusu(StatusZakaza.VIPOLNEN)).thenReturn(vipolnennieZakazi);

        List<Zakaz> vseZakazi = new ArrayList<>();
        vseZakazi.add(zakaz1);
        vseZakazi.add(zakaz2);
        vseZakazi.add(zakaz3);
        Mockito.when(restoran.getZakazi()).thenReturn(vseZakazi);
    }

    public Restoran getRestoran() {
        return restoran;
    }
}
