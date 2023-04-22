package pro.jaitl.spring.examples.validation.service;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pro.jaitl.spring.examples.validation.repository.InputEntity;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InputServiceTest {
    @Autowired
    private InputService inputService;

    @Test
    public void testHandle_Success() {
        InputEntity entity = new InputEntity();
        entity.setId(1);
        entity.setInputId(1);
        entity.setName("test");

        assertDoesNotThrow(() -> inputService.handle(entity));
    }

    @Test
    public void testHandle_Failed_InvalidInput() {
        InputEntity entity = new InputEntity();
        entity.setId(2);
        entity.setInputId(-1);
        entity.setName(null);

        ConstraintViolationException e = assertThrows(ConstraintViolationException.class,
                () -> inputService.handle(entity));

        Set<String> constraintViolations = e.getConstraintViolations().stream()
                .map(v -> String.format("%s: %s", v.getPropertyPath(), v.getMessage()))
                .collect(Collectors.toSet());

        assertTrue(constraintViolations.contains("handle.inputEntity.name: must not be null"));
        assertTrue(constraintViolations.contains("handle.inputEntity.inputId: must be greater than or equal to 0"));
    }
}
