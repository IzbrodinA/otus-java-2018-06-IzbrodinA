package ru.otus.l071.model;

import org.junit.Before;
import org.junit.Test;
import ru.otus.l071.model.exceptions.InvalidAddBanknote;
import ru.otus.l071.model.exceptions.InvalidAddMoneyCell;
import ru.otus.l071.model.exceptions.InvalidGiveMoney;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AtmTest {
    Atm atm;

    @Before
    public void before() {
        atm = new Atm(6);
        atm.addCell(new MoneyCell(Banknote.BANKNOTE5000, 500));
        atm.addCell(new MoneyCell(Banknote.BANKNOTE100, 3000));
        atm.addCell(new MoneyCell(Banknote.BANKNOTE500, 2000));
        atm.addCell(new MoneyCell(Banknote.BANKNOTE1000, 3000));
        atm.addCell(new MoneyCell(Banknote.BANKNOTE100, 2000));
    }

    @Test
    public void addCell() {
        atm.addCell(new MoneyCell(Banknote.BANKNOTE5000, 2000));
        int expected = 6;
        assertEquals(6,atm.getCountCell() );
    }

    @Test(expected = InvalidAddMoneyCell.class)
    public void addMuchCell() {
        atm.addCell(new MoneyCell(Banknote.BANKNOTE5000, 2000));
        atm.addCell(new MoneyCell(Banknote.BANKNOTE5000, 2000));
        assertNotNull("Expected exception",atm);
    }

    @Test
    public void addOneBanknote() {
        long sumBeforeAddMoney  = atm.getAvailableMoney();
        try {
            atm.addBanknote(Banknote.BANKNOTE5000, 100);
        } catch (InvalidAddBanknote invalidAddBanknote) {
            invalidAddBanknote.printStackTrace();
        }
        assertNotEquals(sumBeforeAddMoney, atm.getAvailableMoney());
    }

    @Test
    public void andTwoBanknote() {
        long sumBeforeAddMoney  = atm.getAvailableMoney();
        try {
            atm.addBanknote(Banknote.BANKNOTE5000, 100);
            atm.addBanknote(Banknote.BANKNOTE5000, 300);
        } catch (InvalidAddBanknote invalidAddBanknote) {
            invalidAddBanknote.printStackTrace();
        }
        assertEquals(sumBeforeAddMoney, atm.getAvailableMoney() - 5000 * 400);
    }

    @Test
    public void addBanknote100AndReserveOneMoneySell() {
        try {
            atm.addBanknote(Banknote.BANKNOTE100, 1500);
        } catch (InvalidAddBanknote invalidAddBanknote) {
            invalidAddBanknote.printStackTrace();
        }
        assertEquals("Expected reserve cell",6,atm.getCountCell() );
    }

    @Test(expected = InvalidAddBanknote.class)
    public void addBanknote100AndBanknote1000AndReserveOneMoneySell() throws InvalidAddBanknote {
        atm.addBanknote(Banknote.BANKNOTE100, 1500);
        atm.addBanknote(Banknote.BANKNOTE1000, 1500);
        assertNotNull("Expected exception",atm);
    }

    @Test
    public void checkSum() {
        long result = atm.getAvailableMoney();
        long expected = 5000 * 500 + 100 * 3000 + 500 * 2000 + 1000 * 3000 + 2000 * 100;
        assertEquals("check showSum:", expected, result);
    }

    @Test
    public void give1100 () throws InvalidGiveMoney {
        Map<Banknote, Integer> result = atm.giveBanknotes(1100);
        Map<Banknote, Integer> expected = new HashMap<>();
        expected.put(Banknote.BANKNOTE1000, 1);
        expected.put(Banknote.BANKNOTE100, 1);
        assertEquals("check banknote:", expected, result);

    }

    @Test
    public void give15000AndTakeThreeBanknote () throws InvalidGiveMoney {
        atm = new Atm(3);
        atm.addCell(new MoneyCell(Banknote.BANKNOTE5000, 2));
        atm.addCell(new MoneyCell(Banknote.BANKNOTE1000, 4));
        atm.addCell(new MoneyCell(Banknote.BANKNOTE100, 10));

        Map<Banknote, Integer> result = atm.giveBanknotes(15000);
        Map<Banknote, Integer> expected = new HashMap<>();
        expected.put(Banknote.BANKNOTE5000, 2);
        expected.put(Banknote.BANKNOTE1000, 4);
        expected.put(Banknote.BANKNOTE100, 10);
        assertEquals("check banknote:", expected, result);

    }

    @Test (expected = InvalidGiveMoney.class)
    public void give15100ButNotEnoghMoney () throws InvalidGiveMoney {
        atm = new Atm(3);
        atm.addCell(new MoneyCell(Banknote.BANKNOTE5000, 2));
        atm.addCell(new MoneyCell(Banknote.BANKNOTE1000, 4));
        atm.addCell(new MoneyCell(Banknote.BANKNOTE100, 10));
        Map<Banknote, Integer> result = atm.giveBanknotes(15100);
        Map<Banknote, Integer> expected = new HashMap<>();
        expected.put(Banknote.BANKNOTE5000, 2);
        expected.put(Banknote.BANKNOTE1000, 4);
        expected.put(Banknote.BANKNOTE100, 10);
        assertEquals("check banknote:", expected, result);

    }


}