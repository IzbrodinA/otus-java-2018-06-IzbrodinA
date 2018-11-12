package ru.otus.dataSets;


import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UsersDataSet extends DataSet {
    @Column(name = "name",  nullable = false)
    private String name;


    //Important for Hibernate
    public UsersDataSet() {
    }

    public UsersDataSet(String name) {
        this.setId(-1);
        this.name = name;

    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersDataSet)) return false;
        final UsersDataSet that = (UsersDataSet) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "name='" + name + '\'' +
                '}';
    }
}
