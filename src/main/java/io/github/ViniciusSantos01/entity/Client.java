package io.github.ViniciusSantos01.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", length = 100)
    private String name;

    @OneToMany(mappedBy = "client")
    private Set<ClientOrder> orders;
    public Client() {
    }

    public Set<ClientOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<ClientOrder> orders) {
        this.orders = orders;
    }

    public Client(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Client(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
