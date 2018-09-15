package ru.otus.l081.department;


import java.util.Objects;

public class MoneyCell  {

    public static final int MAX_SIZE_CELL = 3000;
    private  int count;
    final private int size;
    final private Banknote banknote;

    public MoneyCell(Banknote banknote, int count, int size) {
        this.banknote = banknote;
        this.count = count;
        this.size = size;
    }

    public MoneyCell(Banknote banknote, int count) {
        this.banknote = banknote;
        this.count = count;
        this.size = MAX_SIZE_CELL;
    }

    public MoneyCell(MoneyCell copyMoneyCell){
        count = copyMoneyCell.getCount();
        size = copyMoneyCell.getSize();
        banknote = copyMoneyCell.getBanknote();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoneyCell)) return false;
        MoneyCell moneyCell = (MoneyCell) o;
        return count == moneyCell.count &&
                banknote == moneyCell.banknote;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, banknote);
    }

    public int getSize() {
        return size;
    }

    public Banknote getBanknote() {
        return banknote;
    }

    public int getCount() {
        return count;
    }

    public void addBanknote (int count){
        this.count += count;

    }


    public void takeBanknote(int countBanknote) {
        count = count - countBanknote;
    }
}
