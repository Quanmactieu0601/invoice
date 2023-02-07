package study.invoice.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull
    @Size(max = 500)
    private String name;

    @Column(name = "comTaxCode")
    @Size(max = 20)
    @NotNull
    private String comTaxCode;

    @Column(name = "email")
    @Email
    @Size(max = 254)
    private String email;

    @Column(name = "phone")
    @Size(max = 50)
    private String phone;

    @Column(name = "address")
    @Size(max = 500)
    private String address;

    @Column(name = "parentComID")
    private Long parentComID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComTaxCode() {
        return comTaxCode;
    }

    public void setComTaxCode(String comTaxCode) {
        this.comTaxCode = comTaxCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getParentComID() {
        return parentComID;
    }

    public void setParentComID(Long parentComID) {
        this.parentComID = parentComID;
    }
}
