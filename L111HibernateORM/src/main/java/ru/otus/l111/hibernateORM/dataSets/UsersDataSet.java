package ru.otus.l111.hibernateORM.dataSets;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UsersDataSet extends DataSet {
    @Column(name = "name",  nullable = false)
    private String name;

    @Column(name = "age",  nullable = false)
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet address;


    public AddressDataSet getAddress() {
        return address;
    }

    public void setAddress(final AddressDataSet address) {
        this.address = address;
    }

    //Important for Hibernate
    public UsersDataSet() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public UsersDataSet(String name, int age, AddressDataSet address) {
        this.setId(-1);
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "UsersDataSet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
