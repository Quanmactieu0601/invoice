package study.invoice.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "comID")
    @NotNull
    private long comID;

    @Column(name = "code")
    @Size(max = 50)
    private String code;

    @NotNull
    @Column(name = "name")
    @Size(max = 200)
    private String name;

    @Column(name = "unit")
    @Size(max = 100)
    private String unit;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "vatrate")
    private float vatrate;

    @Column(name = "vatrateOther")
    private BigDecimal vatrateOther;

    @Column(name = "groupCode")
    @Size(max = 100)
    private String groupCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public float getVatrate() {
        return vatrate;
    }

    public void setVatrate(float vatrate) {
        this.vatrate = vatrate;
    }

    public BigDecimal getVatrateOther() {
        return vatrateOther;
    }

    public void setVatrateOther(BigDecimal vatrateOther) {
        this.vatrateOther = vatrateOther;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public long getComID() {
        return comID;
    }

    public void setComID(long comID) {
        this.comID = comID;
    }
}
