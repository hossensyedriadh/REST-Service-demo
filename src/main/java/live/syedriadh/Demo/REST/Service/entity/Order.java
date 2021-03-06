package live.syedriadh.Demo.REST.Service.entity;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "orders")
public final class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "customer_id", referencedColumnName = "id", updatable = false)
    @RestResource(path = "attachedCustomer", rel = "customer")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "product_id", referencedColumnName = "id", updatable = false)
    @RestResource(path = "attachedProduct", rel = "product")
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "total_payable", nullable = false)
    private Double totalPayable;

    @Column(name = "is_paid", nullable = false)
    private Boolean paid;

    @Column(name = "order_placed_on", insertable = false, updatable = false, nullable = false)
    private LocalDateTime orderPlacedOn;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public Double getTotalPayable() {
        return totalPayable;
    }

    public void setTotalPayable(Double totalPayable) {
        this.totalPayable = totalPayable;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public LocalDateTime getOrderPlacedOn() {
        return orderPlacedOn;
    }

    public void setOrderPlacedOn(LocalDateTime orderPlacedOn) {
        this.orderPlacedOn = orderPlacedOn;
    }
}
