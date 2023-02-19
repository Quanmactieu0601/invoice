package study.invoice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CompanyDTO {
    private Long id;

    @NotNull
    @Size(max = 500)
    private String name;

    @Size(max = 20)
    @NotNull
    private String comTaxCode;

    @Email
    @Size(max = 254)
    private String email;

    @Size(max = 50)
    private String phone;

    @Size(max = 500)
    private String address;

    private Long parentComID;

    public CompanyDTO() {
    }

    public CompanyDTO(Long id,@NotNull @Size(max = 500) String name, @NotNull @Size(max = 20) String comTaxCode,
                      @Email @Size(max = 254) String email, @Size(max = 50) String phone, @Size(max = 500) String address, Long parentComID) {
        this.id = id;
        this.name = name;
        this.comTaxCode = comTaxCode;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.parentComID = parentComID;
    }

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
