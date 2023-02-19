package study.invoice.dto;

import study.invoice.enm.Feature;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProductInvDTO {
    private Long id;

    private Long invID;

    @Size(max = 50)
    private String code;

    @NotNull
    @Size(max = 200)
    private String name;

    @Size(max = 100)
    private String unit;

    private BigDecimal quantity;

    private BigDecimal price;

    private BigDecimal total;

    private float vatrate;

    private BigDecimal vatrateOther;

    private BigDecimal vatAmount;

    private float discount;

    private BigDecimal discountAmount;

    private BigDecimal amount;

    private Feature feature;

    private int no;

    @Size(max = 500)
    private String extra;

    public ProductInvDTO() {
    }

    public ProductInvDTO(Long id, Long invID, @Size(max = 50) String code, @NotNull @Size(max = 200)String name,
                         @Size(max = 100) String unit, BigDecimal quantity, BigDecimal price, BigDecimal total,
                         float vatrate, BigDecimal vatrateOther, BigDecimal vatAmount, float discount,
                         BigDecimal discountAmount, BigDecimal amount, Feature feature, int no, @Size(max = 500) String extra) {
        this.id = id;
        this.invID = invID;
        this.code = code;
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.vatrate = vatrate;
        this.vatrateOther = vatrateOther;
        this.vatAmount = vatAmount;
        this.discount = discount;
        this.discountAmount = discountAmount;
        this.amount = amount;
        this.feature = feature;
        this.no = no;
        this.extra = extra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvID() {
        return invID;
    }

    public void setInvID(Long invID) {
        this.invID = invID;
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

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
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
}
