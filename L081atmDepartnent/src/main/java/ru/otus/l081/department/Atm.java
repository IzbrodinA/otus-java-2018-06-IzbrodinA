package ru.otus.l081.department;


import ru.otus.l081.department.exception.InvalidAddBanknote;
import ru.otus.l081.department.exception.InvalidAddMoneyCell;
import ru.otus.l081.department.exception.InvalidGiveMoney;

import java.util.*;

public class Atm {

    final private int countCell;
    private List<MoneyCell> moneyCell = new ArrayList<>();
    private String serialNumber;
    private MementoAtm initialSetting;

    public Atm(int countCell, String serialNumber) {
        this.countCell = countCell;
        this.serialNumber = serialNumber;
    }

    public Atm(int countCell) {
        this.countCell = countCell;
        this.serialNumber = "ATM";
    }

    public Atm(Atm copyAtm){
        this(copyAtm.getCountCell(),copyAtm.getSerialNumber());
        for (int i = 0; i < copyAtm.getMoneyCell().size(); i++){
            this.addCell(new MoneyCell(copyAtm.moneyCell.get(i)));
        }
    }

    public void addCell(MoneyCell moneyCell) throws InvalidAddMoneyCell {
        if (countCell > this.moneyCell.size()) {
            this.moneyCell.add(moneyCell);
        } else {
            throw new InvalidAddMoneyCell();
        }
    }

    public void addBanknote(Banknote banknote, int countBanknote) throws InvalidAddBanknote {
        for (MoneyCell cell : moneyCell) {
            if (banknote.equals(cell.getBanknote())) {
                countBanknote = addBanknoteInCell(cell, countBanknote);
            }
        }
        if (countBanknote != 0) {
            while (moneyCell.size() < countCell) {
                if (countBanknote >= MoneyCell.MAX_SIZE_CELL) {
                    addCell(new MoneyCell(banknote, MoneyCell.MAX_SIZE_CELL));
                    countBanknote = countBanknote - MoneyCell.MAX_SIZE_CELL;
                } else {
                    addCell(new MoneyCell(banknote, countBanknote));
                    countBanknote = 0;
                }
            }
        }
        if (countBanknote != 0) {
            throw new InvalidAddBanknote();
        }
    }

    public Map<Banknote, Integer> giveBanknotes(long sum) throws InvalidGiveMoney {
        Map<Banknote, Integer> banknotes = new HashMap<>();
        moneyCell.sort(Comparator.comparingInt((MoneyCell o) -> -o.getBanknote().getValue()));
        for (MoneyCell cell : moneyCell) {
            int valueBanknote = cell.getBanknote().getValue();
            int availableCountBanknote = cell.getCount();
            if (availableCountBanknote > 0 && valueBanknote <= sum) {
                int countBanknote = (int) sum / valueBanknote;
                if (countBanknote <= availableCountBanknote) {
                    cell.takeBanknote(countBanknote);
                    banknotes.put(cell.getBanknote(), countBanknote);
                    sum -= countBanknote * valueBanknote;
                } else {
                    cell.takeBanknote(availableCountBanknote);
                    banknotes.put(cell.getBanknote(), availableCountBanknote);
                    sum -= availableCountBanknote * valueBanknote;
                }
            }
        }

        if (sum != 0) {
            throw new InvalidGiveMoney();
        }

        return banknotes;
    }

    public long getAvailableMoney() {
        long sum = 0;
        for (MoneyCell cell : moneyCell) {
            sum += cell.getBanknote().getValue() * cell.getCount();
        }
        return sum;
    }

    public void setInitialSetting() {
        initialSetting = new MementoAtm(this);
    }

    public void resetInitialSetting() {
        if (initialSetting == null){
            System.out.println("ATM don't have initial setting");
            return;
        }
        Atm savedAtm = initialSetting.getSavedAtm();
        this.moneyCell = savedAtm.getMoneyCell();
        this.serialNumber = savedAtm.getSerialNumber();

    }

    public List<MoneyCell> getMoneyCell() {
        return moneyCell;
    }

    public int getCountCell() {
        return countCell;
    }

    public String getSerialNumber() {
        return serialNumber;
    }



    private int addBanknoteInCell(MoneyCell cell, int countBanknote) {
        int availableCountAddBanknote = cell.getSize() - cell.getCount();
        if (availableCountAddBanknote >= countBanknote) {
            cell.addBanknote(countBanknote);
            countBanknote = 0;
        } else {
            countBanknote = countBanknote - availableCountAddBanknote;
            cell.addBanknote(availableCountAddBanknote);
        }
        return countBanknote;
    }


}
