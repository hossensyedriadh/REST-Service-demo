package live.syedriadh.Demo.REST.Service.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "customers")
public final class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private int id;

    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Invalid name [Only letters and whitespaces allowed]")
    @Column(name = "name", updatable = false, nullable = false)
    private String name;

    @Pattern(regexp = "^[0-9+]+$", message = "Invalid phone [Only numbers and plus (+) allowed]")
    @Column(name = "phone", nullable = false)
    private String phone;

    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email")
    @Column(name = "email", nullable = false)
    private String email;

    @Pattern(regexp = "^[A-Za-z0-9\\-\\s:,;]+$", message = "Only letters, numbers, hyphens (-), " +
            "whitespaces, colon (:), comma (,) and semi-colon (;) allowed")
    @Column(name = "address", nullable = false)
    private String address;

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}