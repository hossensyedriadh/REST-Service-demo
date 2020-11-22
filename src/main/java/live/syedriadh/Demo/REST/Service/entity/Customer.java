package live.syedriadh.Demo.REST.Service.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@Getter
@Setter
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "customers")
public final class Customer {
    @Id
    @Setter(AccessLevel.NONE)
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
}