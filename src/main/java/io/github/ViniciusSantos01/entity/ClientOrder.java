package io.github.ViniciusSantos01.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "client_Order")
public class ClientOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "orderDate")
    private LocalDate orderDate;
    @Column(name = "total", precision = 20, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "clientOrder")
    private List<ItemClientOrder> itens;

    public List<ItemClientOrder> getItens() {
        return itens;
    }

    public void setItens(List<ItemClientOrder> itens) {
        this.itens = itens;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ClientOrder{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", total=" + total +
                '}';
    }
}
