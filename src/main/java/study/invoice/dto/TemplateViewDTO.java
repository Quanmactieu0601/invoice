package study.invoice.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TemplateViewDTO {
    private Long id;

    @Size(max = 200)
    private String templateName;

    @Size(max = 200)
    private String templateCode;

    @NotNull
    @Size(max = 200)
    private String viewName;

}
