package pro.jaitl.spring.examples.validation.dto;

import jakarta.validation.constraints.AssertTrue;
import lombok.Data;

@Data
public class DoorDto {
    private String materialType;
    private String lockType;

    @AssertTrue(message = "Unreliable door")
    public boolean isValidDoor() {
        return materialType.contains("steel") && lockType.contains("digital");
    }
}
