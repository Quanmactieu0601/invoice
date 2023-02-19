package study.invoice.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class InvTemplateDTO {
    private Long id;

    private Long comID;

    @NotNull
    private String pattern;

    @NotNull
    private Long templateViewID;

    private TemplateViewDTO templateViewDTO;

    public InvTemplateDTO() {
    }

    public InvTemplateDTO(Long id, Long comID, @NotNull String pattern, @NotNull Long templateViewID) {
        this.id = id;
        this.comID = comID;
        this.pattern = pattern;
        this.templateViewID = templateViewID;
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

    public TemplateViewDTO getTemplateViewDTO() {
        return templateViewDTO;
    }

    public void setTemplateViewDTO(TemplateViewDTO templateViewDTO) {
        this.templateViewDTO = templateViewDTO;
    }
}
