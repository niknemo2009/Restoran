package model;

import java.time.LocalDate;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class Director extends Supervisor {

    public Director(String login, String pass) {
        super(login, pass);
    }

    public List<Zakaz> pokazatVseZakazi(Restoran restoran) {
        return restoran.getZakazi();
    }

}
