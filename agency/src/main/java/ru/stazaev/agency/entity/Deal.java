package ru.stazaev.agency.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "deals")
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_deal")
    private long id;

    @Column(name = "id_worker")
    private long workerId;

    @Column(name = "id_flat")
    private long flatId;

    @Column(name = "id_client")
    private long clientId;

    private String type;

    private int cost;

    public Deal(long workerId, long flatId, long clientId, String type, int cost) {
        this.workerId = workerId;
        this.flatId = flatId;
        this.clientId = clientId;
        this.type = type;
        this.cost = cost;
    }

    public Deal() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public long getFlatId() {
        return flatId;
    }

    public void setFlatId(long flatId) {
        this.flatId = flatId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
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

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", workerId=" + workerId +
                ", flatId=" + flatId +
                ", clientId=" + clientId +
                ", type='" + type + '\'' +
                ", cost=" + cost +
                '}';
    }
}
