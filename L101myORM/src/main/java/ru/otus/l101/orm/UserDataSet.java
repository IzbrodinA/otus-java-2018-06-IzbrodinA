package ru.otus.l101.orm;

import java.util.Objects;

public class UserDataSet extends DataSet{
    private final String name;
    private final int age;



    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public UserDataSet(final String name, final Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDataSet)) return false;
        final UserDataSet that = (UserDataSet) o;
        return age == that.age &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
