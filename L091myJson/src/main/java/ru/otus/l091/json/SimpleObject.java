package ru.otus.l091.json;

public class SimpleObject {
    int anInt ;

    public void setAnInt(final int anInt) {
        this.anInt = anInt;
    }

    public void setString(final String string) {
        this.string = string;
    }

    public SimpleObject(final int anInt, final String string) {
        this.anInt = anInt;
        this.string = string;
    }

    String string ;
}
