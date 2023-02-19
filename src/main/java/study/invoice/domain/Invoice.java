package study.invoice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import study.invoice.enm.Currency;
import study.invoice.enm.InvoiceStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "invoice")
public class Invoice extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comID")
    @NotNull
    private Long comID;

    @Column(name = "name")
    @NotNull
    @Size(max = 200)
    private String name;

    @Column(name = "pattern")
    @NotNull
    private String pattern;

    @Column(name = "no")
    private Long no;

    @Column(name = "cusCode")
    @Size(max = 100)
    private String cusCode;

    @Column(name = "cusName")
    @Size(max = 200)
    private String cusName;

    @Column(name = "cusAddress")
    @Size(max = 500)
    private String cusAddress;

    @Column(name = "cusEmails")
    @Size(max = 200)
    private String cusEmails;

    @Column(name = "cusPhone")
    @Size(max = 20)
    private String cusPhone;

    @Column(name = "cusTaxCode")
    @Size(max = 20)
    private String cusTaxCode;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private InvoiceStatus status;

    @Column(name = "paymentMethod")
    @Size(max = 150)
    private String paymentMethod;

    @Column(name = "arisingDate", length = 50, updatable = false)
    @JsonIgnore
    private LocalDateTime arisingDate;

    @Column(name = "publishDate", length = 50, updatable = false)
    @JsonIgnore
    private LocalDateTime publishDate;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "vatrate")
    private int vatrate;

    @Column(name = "vatAmount")
    private BigDecimal vatAmount;

    @Column(name = "discount")
    private float discount;

    @Column(name = "discountAmount")
    private BigDecimal discountAmount;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "Ikey")
    @Size(max = 50)
    private String ikey;

    @Column(name = "note")
    private String note;

    @Column(name = "extra")
    @JsonIgnore
    private String extra;

    @Column(name = "currenUnit")
    @Enumerated(EnumType.STRING)
    @Size(max = 20)
    private Currency currenUnit;

    @Column(name = "exchangeRate")
    private Double exchangeRate;

    @Column(name = "xmlData")
    @JsonIgnore
    private String xmlData;

    @OneToMany(mappedBy = "invID")
    private Collection<ProductInv> productInvs;

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

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCusEmails() {
        return cusEmails;
    }

    public void setCusEmails(String cusEmails) {
        this.cusEmails = cusEmails;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusTaxCode() {
        return cusTaxCode;
    }

    public void setCusTaxCode(String cusTaxCode) {
        this.cusTaxCode = cusTaxCode;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getArisingDate() {
        return arisingDate;
    }

    public void setArisingDate(LocalDateTime arisingDate) {
        this.arisingDate = arisingDate;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getVatrate() {
        return vatrate;
    }

    public void setVatrate(int vatrate) {
        this.vatrate = vatrate;
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

    public String getIkey() {
        return ikey;
    }

    public void setIkey(String ikey) {
        this.ikey = ikey;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Currency getCurrenUnit() {
        return currenUnit;
    }

    public void setCurrenUnit(Currency currenUnit) {
        this.currenUnit = currenUnit;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getXmlData() {
        return xmlData;
    }

    public void setXmlData(String xmlData) {
        this.xmlData = xmlData;
    }

    public Long getComID() {
        return comID;
    }

    public void setComID(Long comID) {
        this.comID = comID;
    }

    public Collection<ProductInv> getProductInvs() {
        return productInvs;
    }

    public void setProductInvs(Collection<ProductInv> productInvs) {
        this.productInvs = productInvs;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pattern='" + pattern + '\'' +
                ", no=" + no +
                ", cusCode='" + cusCode + '\'' +
                ", cusName='" + cusName + '\'' +
                ", cusAddress='" + cusAddress + '\'' +
                ", cusEmails='" + cusEmails + '\'' +
                ", cusPhone='" + cusPhone + '\'' +
                ", cusTaxCode='" + cusTaxCode + '\'' +
                ", status=" + status +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", arisingDate=" + arisingDate +
                ", publishDate=" + publishDate +
                ", total=" + total +
                ", vatrate=" + vatrate +
                ", vatAmount=" + vatAmount +
                ", discount=" + discount +
                ", discountAmount=" + discountAmount +
                ", amount=" + amount +
                ", ikey='" + ikey + '\'' +
                ", note='" + note + '\'' +
                ", extra='" + extra + '\'' +
                ", currenUnit=" + currenUnit +
                ", exchangeRate=" + exchangeRate +
                ", xmlData='" + xmlData + '\'' +
                '}';
    }
}
