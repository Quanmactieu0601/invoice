package study.invoice.domain;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "invoiceTemplate")
public class InvoiceTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comID")
    @NotNull
    private Long comID;

    @Column(name = "pattern")
    @NotNull
    private String pattern;

    @Column(name = "templateViewID")
    @NotNull
    private Long templateViewID;

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

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Long getTemplateViewID() {
        return templateViewID;
    }

    public void setTemplateViewID(Long templateViewID) {
        this.templateViewID = templateViewID;
    }
}
