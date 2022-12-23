package ru.stazaev.agency.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "string")
    private String string;

    public Test(long id, String string) {
        this.id = id;
        this.string = string;
    }

    public Test() {

    }

    public long getId() {
        return id;
    }

    public String getString() {
        return string;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", string='" + string + '\'' +
                '}';
    }
}
