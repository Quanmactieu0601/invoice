package study.invoice.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "templateView")
public class TemplateView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "pattern")
//    @NotNull
//    @Size(max = 50)
//    private String pattern;
//
//    @Column(name = "comID")
//    @NotNull
//    private Long comID;

    @Column(name = "templateName")
    @Size(max = 200)
    private String templateName;

    @Column(name = "templateCode")
    @Size(max = 200)
    private String templateCode;

    @Column(name = "xmlFile")
    private String xmlFile;

    @Column(name = "xsltFile")
    private String xsltFile;

    @Column(name = "viewName")
    @NotNull
    @Size(max = 200)
    private String viewName;

    @Column(name = "cssData")
    private String cssData;

    @Column(name = "cssLogo")
    private String cssLogo;

    @Column(name = "cssBackground")
    private String cssBackground;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getXmlFile() {
        return xmlFile;
    }

    public void setXmlFile(String xmlFile) {
        this.xmlFile = xmlFile;
    }

    public String getXsltFile() {
        return xsltFile;
    }

    public void setXsltFile(String xsltFile) {
        this.xsltFile = xsltFile;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getCssData() {
        return cssData;
    }

    public void setCssData(String cssData) {
        this.cssData = cssData;
    }

    public String getCssLogo() {
        return cssLogo;
    }

    public void setCssLogo(String cssLogo) {
        this.cssLogo = cssLogo;
    }

    public String getCssBackground() {
        return cssBackground;
    }

    public void setCssBackground(String cssBackground) {
        this.cssBackground = cssBackground;
    }
}
