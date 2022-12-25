package ru.stazaev.agency.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workers")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_worker")
    private long id;

    @Column(name = "worker_name")
    private String name;

    @Column(name = "telephone")
    private int telephone;

    @OneToMany(mappedBy = "worker")
    private List<Flat> flats;

    @OneToMany(mappedBy = "worker")
    private List<Deal> deals;

    public Worker(String name, int telephone, List<Flat> flats) {
        this.name = name;
        this.telephone = telephone;
        this.flats = flats;
    }

    public Worker(String name, int telephone) {
        this.name = name;
        this.telephone = telephone;
        this.flats = new ArrayList<>();
    }

    public Worker() {
        this.flats = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTelephone() {
        return telephone;
    }

    public List<Flat> getFlats() {
        return flats;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setFlats(List<Flat> flats) {
        this.flats = flats;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telephone=" + telephone +
                '}';
    }
}
