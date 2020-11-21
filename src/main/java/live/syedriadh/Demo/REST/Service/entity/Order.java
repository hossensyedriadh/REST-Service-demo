package live.syedriadh.Demo.REST.Service.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public final class Order {
    @Id
    @Setter(AccessLevel.NONE)
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
}
