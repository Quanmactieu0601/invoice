package study.invoice.domain;

import com.mysql.cj.xdevapi.Collection;
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

    @ManyToOne(optional=false)
    @JoinColumn(name="templateView",referencedColumnName="id")
    private TemplateView templateView;

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

    public TemplateView getTemplateView() {
        return templateView;
    }

    public void setTemplateView(TemplateView templateView) {
        this.templateView = templateView;
    }
}
