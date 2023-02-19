package study.invoice.dto;

import study.invoice.enm.Currency;
import study.invoice.enm.InvoiceStatus;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class InvoiceDTO {
    private Long id;

    private Long comID;

    private String name;

    @NotNull
    private String pattern;

    private Long no;

    @Size(max = 100)
    private String cusCode;

    @Size(max = 200)
    private String cusName;

    @Size(max = 500)
    private String cusAddress;

    @Size(max = 200)
    private String cusEmails;

    @Size(max = 20)
    private String cusPhone;

    @Size(max = 20)
    private String cusTaxCode;

    private InvoiceStatus status;

    @Size(max = 150)
    private String paymentMethod;

    private LocalDateTime arisingDate;

    private LocalDateTime publishDate;

    private BigDecimal total;

    private int vatrate;

    private BigDecimal vatAmount;

    private float discount;

    private BigDecimal discountAmount;

    private BigDecimal amount;

    @Size(max = 50)
    private String ikey;

    private String note;

    private String extra;

    private Currency currenUnit;

    private Double exchangeRate;

    private String xmlData;

    private List<ProductInvDTO> productInvs;

    public InvoiceDTO() {
    }

    public InvoiceDTO(Long id, @NotNull String pattern, Long no, @Size(max = 100) String cusCode, @Size(max = 200) String cusName,
                      @Size(max = 500) String cusAddress, @Size(max = 200) String cusEmails, @Size(max = 20) String cusPhone,
                      @Size(max = 20) String cusTaxCode, InvoiceStatus status, @Size(max = 150) String paymentMethod,
                      LocalDateTime arisingDate, LocalDateTime publishDate, BigDecimal total, int vatrate, BigDecimal vatAmount,
                      float discount, BigDecimal discountAmount, BigDecimal amount, @Size(max = 50) String ikey, String note,
                      String extra, Currency currenUnit, Double exchangeRate) {
        this.id = id;
        this.pattern = pattern;
        this.no = no;
        this.cusCode = cusCode;
        this.cusName = cusName;
        this.cusAddress = cusAddress;
        this.cusEmails = cusEmails;
        this.cusPhone = cusPhone;
        this.cusTaxCode = cusTaxCode;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.arisingDate = arisingDate;
        this.publishDate = publishDate;
        this.total = total;
        this.vatrate = vatrate;
        this.vatAmount = vatAmount;
        this.discount = discount;
        this.discountAmount = discountAmount;
        this.amount = amount;
        this.ikey = ikey;
        this.note = note;
        this.extra = extra;
        this.currenUnit = currenUnit;
        this.exchangeRate = exchangeRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getComID() {
        return comID;
    }

    public void setComID(Long comID) {
        this.comID = comID;
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

    public List<ProductInvDTO> getProductInvs() {
        return productInvs;
    }

    public void setProductInvs(List<ProductInvDTO> productInvs) {
        this.productInvs = productInvs;
    }
}
