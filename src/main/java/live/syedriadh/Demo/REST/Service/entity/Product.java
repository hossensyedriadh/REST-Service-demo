package live.syedriadh.Demo.REST.Service.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "products")
public final class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private int id;

    @Pattern(regexp = "^[A-Za-z0-9\\s\\-.()]+$", message = "Invalid product name [Only letters, numbers, whitespaces, hyphen (-), " +
            "period (.) and parentheses ( () ) allowed]")
    @Column(name = "product_name", updatable = false, nullable = false)
    private String productName;

    @Pattern(regexp = "^[A-Za-z0-9\\s\\-.()]+$", message = "Invalid product type [Only letters, numbers, whitespaces, hyphen (-), " +
            "period (.) and parentheses ( () ) allowed]")
    @Column(name = "product_type", updatable = false, nullable = false)
    private String productType;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "price_per_unit", nullable = false)
    private Double pricePerUnit;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
