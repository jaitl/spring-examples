package pro.jaitl.spring.examples.validation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AttachmentDto {
    @NotNull
    private String content;
}
