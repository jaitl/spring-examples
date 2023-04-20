package pro.jaitl.spring.examples.validation.controller.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pro.jaitl.spring.examples.validation.dto.AttachmentDto;

@Data
public class CreateInputRequest {
    @Size(max = 20)
    private String name;
    @Min(0)
    private Integer inputId;
    @NotNull
    @Valid
    private AttachmentDto attachment;
}
