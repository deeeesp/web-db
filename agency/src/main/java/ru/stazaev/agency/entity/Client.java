package ru.stazaev.agency.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private long id;

    @Column(name = "client_name")
    private String name;


    private int telephone;

    @OneToMany(mappedBy = "client")
    private List<Wish> wishes;

    @OneToMany(mappedBy = "client")
    private List<Deal> deals;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public List<Wish> getWishes() {
        return wishes;
    }

    public void setWishes(List<Wish> wishes) {
        this.wishes = wishes;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telephone=" + telephone +
                ", wishes=" + wishes +
                '}';
    }
}
