package io.github.ViniciusSantos01.domain.entity;

public class ItemClientOrder {

    private Integer id;
    private ClientOrder clientOrder;
    private Product product;
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClientOrder getClientOrder() {
        return clientOrder;
    }

    public void setClientOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
