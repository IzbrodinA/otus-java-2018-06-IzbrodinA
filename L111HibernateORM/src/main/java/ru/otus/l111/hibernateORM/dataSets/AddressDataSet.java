package ru.otus.l111.hibernateORM.dataSets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class AddressDataSet extends DataSet {
    @Column(name = "street")
    private String street;


    public AddressDataSet(final String street) {
        this.street = street;
    }

    public AddressDataSet() {
    }

    @Override
    public String toString() {
        return "AddressDataSet{" +
                "street='" + street + '\'' +
                '}';
    }


}
