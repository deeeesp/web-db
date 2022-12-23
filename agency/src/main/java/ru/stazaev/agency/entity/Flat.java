package ru.stazaev.agency.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "flats")
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_flat")
    private long id;

    private int floor;

    private int meters;

    private int rooms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_worker")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Worker worker;

    public Flat(int floor, int meters, int rooms, Worker worker) {
        this.floor = floor;
        this.meters = meters;
        this.rooms = rooms;
        this.worker = worker;
    }

    public Flat() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getMeters() {
        return meters;
    }

    public void setMeters(int meters) {
        this.meters = meters;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", floor=" + floor +
                ", meters=" + meters +
                ", rooms=" + rooms +
                ", worker=" + worker +
                '}';
    }
}
