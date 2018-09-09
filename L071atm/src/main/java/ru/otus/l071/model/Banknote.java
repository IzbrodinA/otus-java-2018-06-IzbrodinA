package ru.otus.l071.model;

public enum Banknote {

    BANKNOTE5000(5000),
    BANKNOTE2000(2000),
    BANKNOTE1000(1000),
    BANKNOTE500(500),
    BANKNOTE100(100);

    private final int value;

    Banknote (int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
