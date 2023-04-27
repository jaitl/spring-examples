package pro.jaitl.spring.examples.validation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pro.jaitl.spring.examples.validation.custom.RusPhoneNumber;

@Data
public class UserDto {
    @NotNull
    @Size(min = 1, max = 20)
    private String name;
    @NotNull
    @RusPhoneNumber
    private String phone;
}
