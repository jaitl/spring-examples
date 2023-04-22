package pro.jaitl.spring.examples.validation.service;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pro.jaitl.spring.examples.validation.dto.OutputDto;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OutputServiceTest {
    @Autowired
    private OutputService outputService;

    @Test
    public void testOutput_Create_Valid() {
        OutputDto outputDto = new OutputDto();
        outputDto.setName("test");
        outputDto.setOutputId(1);

        assertDoesNotThrow(() -> outputService.handleCreate(outputDto));
    }

    @Test
    public void testOutput_Create_Invalid() {
        OutputDto outputDto = new OutputDto();
        outputDto.setName(null);
        outputDto.setOutputId(-1);

        ConstraintViolationException e = assertThrows(ConstraintViolationException.class,
                () -> outputService.handleCreate(outputDto));

        Set<String> constraintViolations = e.getConstraintViolations().stream()
                .map(v -> String.format("%s: %s", v.getPropertyPath(), v.getMessage()))
                .collect(Collectors.toSet());

        assertTrue(constraintViolations.contains("handleCreate.outputDto.name: must not be null"));
        assertTrue(constraintViolations.contains("handleCreate.outputDto.outputId: must be greater than or equal to 0"));
    }

    @Test
    public void testOutput_Update_Valid() {
        OutputDto outputDto = new OutputDto();
        outputDto.setName(null);
        outputDto.setOutputId(null);

        assertDoesNotThrow(() -> outputService.handleUpdate(outputDto));
    }

    @Test
    public void testOutput_Update_Invalid() {
        OutputDto outputDto = new OutputDto();
        outputDto.setName("it's longer than 20 characters");
        outputDto.setOutputId(-1);

        ConstraintViolationException e = assertThrows(ConstraintViolationException.class,
                () -> outputService.handleCreate(outputDto));

        Set<String> constraintViolations = e.getConstraintViolations().stream()
                .map(v -> String.format("%s: %s", v.getPropertyPath(), v.getMessage()))
                .collect(Collectors.toSet());

        assertTrue(constraintViolations.contains("handleCreate.outputDto.name: size must be between 0 and 20"));
        assertTrue(constraintViolations.contains("handleCreate.outputDto.outputId: must be greater than or equal to 0"));
    }
}