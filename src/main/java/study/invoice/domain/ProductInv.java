package study.invoice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;
import study.invoice.enm.Feature;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "productInv")
public class ProductInv implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invID")
    @NaturalId
    private Long invID;

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

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "vatrate")
    private float vatrate;

    @Column(name = "vatrateOther")
    private BigDecimal vatrateOther;

    @Column(name = "vatAmount")
    private BigDecimal vatAmount;

    @Column(name = "discount")
    private float discount;

    @Column(name = "discountAmount")
    private BigDecimal discountAmount;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "feature")
    @Enumerated(EnumType.ORDINAL)
    private Feature feature;

    @Column(name = "no")
    private int no;

    @JsonIgnore
    @Column(name = "extra")
    @Size(max = 500)
    private String extra;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public BigDecimal getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(BigDecimal vatAmount) {
        this.vatAmount = vatAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Long getInvID() {
        return invID;
    }

    public void setInvID(Long invID) {
        this.invID = invID;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
