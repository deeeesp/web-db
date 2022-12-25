package ru.stazaev.agency.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "deals")
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_deal")
    private long id;

//    @Column(name = "id_worker")
//    private long workerId;

//    @Column(name = "id_flat")
//    private long flatId;

//    @Column(name = "id_client")
//    private long clientId;

    private String type;

    private int cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_worker")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Worker worker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_flat")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Flat flat;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", cost=" + cost +
                ", worker=" + worker +
                ", flat=" + flat +
                ", client=" + client +
                '}';
    }
}
