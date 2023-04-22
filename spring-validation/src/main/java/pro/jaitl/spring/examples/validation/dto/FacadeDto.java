package pro.jaitl.spring.examples.validation.dto;

import lombok.Data;
import pro.jaitl.spring.examples.validation.custom.FacadeHasPattern;

@FacadeHasPattern(stringPattern = "aaa")
@Data
public class FacadeDto {
    private String name;
    private String description;
}
