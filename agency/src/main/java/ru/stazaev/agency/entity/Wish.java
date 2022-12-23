package ru.stazaev.agency.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "wishes")
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_wish")
    private long id;

    private int meters;

    private int rooms;

    @Column(name = "cost")
    private int money;

    public Wish(int meters, int rooms, int money, Client client) {
        this.meters = meters;
        this.rooms = rooms;
        this.money = money;
        this.client = client;
    }

    public Wish() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client client;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Wish{" +
                "id=" + id +
                ", meters=" + meters +
                ", rooms=" + rooms +
                ", money=" + money +
                ", client=" + client +
                '}';
    }
}
