package study.invoice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;
import study.invoice.enm.Feature;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProductDTO {

    private Long id;

    private Long comID;

    @Size(max = 50)
    private String code;

    @NotNull
    @Size(max = 200)
    private String name;

    @Size(max = 100)
    private String unit;

    private BigDecimal quantity;

    private BigDecimal price;

    private float vatrate;

    private BigDecimal vatrateOther;

    @Size(max = 100)
    private String groupCode;

    public ProductDTO() {
    }

    public ProductDTO(Long id, @NotNull Long comID,  @Size(max = 50) String code, @NotNull @Size(max = 200) String name,
                      @Size(max = 100) String unit, BigDecimal quantity, BigDecimal price, float vatrate,
                      BigDecimal vatrateOther, @Size(max = 100) String groupCode) {
        this.id = id;
        this.comID = comID;
        this.code = code;
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
        this.vatrate = vatrate;
        this.vatrateOther = vatrateOther;
        this.groupCode = groupCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getComID() {
        return comID;
    }

    public void setComID(long comID) {
        this.comID = comID;
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
}
