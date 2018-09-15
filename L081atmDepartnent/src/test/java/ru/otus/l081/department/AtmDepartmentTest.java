package ru.otus.l081.department;

import org.junit.Before;
import org.junit.Test;
import ru.otus.l081.department.exception.InvalidAddBanknote;

import static org.junit.Assert.*;

public class AtmDepartmentTest {

    Atm atm1;
    Atm atm2;
    Atm atm3;
    AtmDepartment atmDepartment;

    @Before
    public void before() {
        atmDepartment = new AtmDepartment();
        atm1 = new Atm(1);
        atm1.addCell(new MoneyCell(Banknote.BANKNOTE5000, 5));

        atm2 = new Atm(2, "ATM2");
        atm2.addCell(new MoneyCell(Banknote.BANKNOTE500, 10));
        atm2.addCell(new MoneyCell(Banknote.BANKNOTE1000, 5));

        atm3 = new Atm(3, "ATM3");
        atm3.addCell(new MoneyCell(Banknote.BANKNOTE100, 5));
    }

    @Test
    public void checkGetSums() {
        atmDepartment.addAtm(atm1);
        atmDepartment.addAtm(atm2);
        long expected = 5000 * 5 + 500 * 10 + 1000 * 5;
        assertEquals(expected, atmDepartment.getAllSums());
    }

    @Test
    public void checkGetSumsAndChangeSumAtm1() throws InvalidAddBanknote {
        atmDepartment.addAtm(atm1);
        atm1.addBanknote(Banknote.BANKNOTE5000, 5);
        atmDepartment.addAtm(atm2);
        long expected = 5000 * 5 + 5000 * 5 + 500 * 10 + 1000 * 5;
        assertEquals(expected, atmDepartment.getAllSums());
    }

    @Test
    public void checkupsetInitialSettings() throws InvalidAddBanknote {
        atmDepartment.addAtm(atm1);
        atm1.setInitialSetting();
        atm1.addBanknote(Banknote.BANKNOTE5000, 5);

        atmDepartment.addAtm(atm2);
        atmDepartment.upsetInitialSettings();
        long expected = 5000 * 5 + 500 * 10 + 1000 * 5;
        assertEquals(expected, atmDepartment.getAllSums());

    }


}