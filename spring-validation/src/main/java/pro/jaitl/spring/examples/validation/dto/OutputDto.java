package pro.jaitl.spring.examples.validation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pro.jaitl.spring.examples.validation.dto.validation.OnCreateGroup;
import pro.jaitl.spring.examples.validation.dto.validation.OnUpdateGroup;

@Data
public class OutputDto {
    @NotNull(groups = OnCreateGroup.class)
    @Size(max = 20, groups = {OnCreateGroup.class, OnUpdateGroup.class})
    public String name;
    @NotNull(groups = OnCreateGroup.class)
    @Min(value = 0, groups = {OnCreateGroup.class, OnUpdateGroup.class})
    public Integer outputId;
}
